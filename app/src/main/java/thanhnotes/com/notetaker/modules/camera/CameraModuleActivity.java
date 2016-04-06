package thanhnotes.com.notetaker.modules.camera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import thanhnotes.com.notetaker.R;

/**
 * Created by nguyenbathanh on 3/21/16.
 */
public class CameraModuleActivity extends Activity {
    private static final int VIDEO_CAPTURE = 101;
    private static final int IMAGE_CAPTURE = 102;
    private Button btnRecordVideo, btnCapture, btnStartRecordAudio, btnStopRecordAudio;
    private Uri fileUri;
    private MediaRecorder mRecorder = null;

    private String LOG_TAG = "CameraModuleActivity";//testtt this is hai


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_camera_layout);

        btnRecordVideo = (Button) findViewById(R.id.btn_record_video);
        btnRecordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording(view);
            }
        });

        btnCapture = (Button) findViewById(R.id.btn_capture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCapture();
            }
        });

        btnStartRecordAudio = (Button) findViewById(R.id.btn_start_record_audio);
        btnStartRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myaudio1.mp3";
                startRecordingAudio(fileName);
            }
        });

        btnStopRecordAudio = (Button) findViewById(R.id.btn_stop_record_audio);
        btnStopRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecordingAudio();
            }
        });

        hideButtonsRecordAudio(false);

    }

    /**
     * Start record video
     *
     * @param view
     */
    public void startRecording(View view) {
        File mediaFile = new
                File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/myvideo1.mp4");

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = Uri.fromFile(mediaFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, VIDEO_CAPTURE);
    }

    /**
     * Start capture photo
     */
    public void startCapture() {
        File mediaFile = new
                File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/mycapture.jpg");
        Uri fileUriPhoto = Uri.fromFile(mediaFile);

        Intent intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT, fileUriPhoto);
        startActivityForResult(intentPhoto, IMAGE_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Captute cancelled.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to capture",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Start record audio
     *
     * @param mFileName: file name and path storage
     */
    private void startRecordingAudio(String mFileName) {
        hideButtonsRecordAudio(true);

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    /**
     * Stop record audio file
     */
    private void stopRecordingAudio() {
        hideButtonsRecordAudio(false);
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    private void hideButtonsRecordAudio(boolean isStartRecordingAudio) {
        if (isStartRecordingAudio) {
            btnStopRecordAudio.setVisibility(View.VISIBLE);
            btnStartRecordAudio.setVisibility(View.GONE);
        } else {
            btnStopRecordAudio.setVisibility(View.GONE);
            btnStartRecordAudio.setVisibility(View.VISIBLE);
        }
    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FRONT);
    }
}
