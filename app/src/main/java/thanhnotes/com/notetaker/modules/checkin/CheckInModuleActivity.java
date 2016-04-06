package thanhnotes.com.notetaker.modules.checkin;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import thanhnotes.com.notetaker.R;
import thanhnotes.com.notetaker.utils.PermissionUtils;

/**
 * Created by Jen's on 4/6/2016.
 */
public class CheckInModuleActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        PlaceSelectionListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private boolean mPermissionDenied = false;
    private PlaceAutocompleteFragment autocompleteFragment;
    private Marker marker;
    private Button btnCheckIn, btnListCheckIn;
    private EditText edtLocationNote;
    private String checkInNote, checkInAddress;
    private Double checkInLng, checkInLat;
    DatabaseHandlerCheckin db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_checkin_layout);
        btnCheckIn = (Button)findViewById(R.id.btn_check_in);
        btnListCheckIn = (Button)findViewById(R.id.btn_check_in_list);
        edtLocationNote = (EditText)findViewById(R.id.edt_location_note);
        db = new DatabaseHandlerCheckin(this);
        db.addCheckIn(new CheckIn("tòa nhà VTC online", "18 Tam Trinh", 105.862, 10.9954));
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(this);
        btnListCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentList = new Intent(getApplicationContext(), ListViewCheckInActivity.class);
                startActivity(intentList);
            }
        });

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkInNote = edtLocationNote.getText().toString();
                // Inserting checkin
                Log.d("Insert: ", "Inserting ..");
                db.addCheckIn(new CheckIn(checkInNote, checkInAddress, checkInLng, checkInLat));
                Toast.makeText(CheckInModuleActivity.this, "Check In thành công!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onMapReady(GoogleMap map){
        mMap = map;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }



    @Override
    public void onPlaceSelected(Place place) {

        checkInAddress = place.getAddress().toString();
        autocompleteFragment.setText(checkInAddress);
        checkInLng = place.getLatLng().longitude;
        checkInLat = place.getLatLng().latitude;
        if(marker!=null){
            marker.remove();
            mMap.clear();
        }

        marker=mMap.addMarker(new MarkerOptions()
                .position(place.getLatLng())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                .title(place.getAddress().toString()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 13));

    }

    @Override
    public void onError(Status status) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }
}
