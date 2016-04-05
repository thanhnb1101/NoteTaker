package thanhnotes.com.notetaker.modules.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import thanhnotes.com.notetaker.R;
import thanhnotes.com.notetaker.utils.ConstantUtil;

/**
 * Created by nguyenbathanh on 4/4/16.
 */
public class WebviewModuleActivity extends Activity implements View.OnClickListener {
    private Button btnURLDirectBrowser, btnURLInternalWebview, btnDisplayDataInternalWebview, btnDisplayFileLocalWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_webview_layout);

        btnURLDirectBrowser = (Button) findViewById(R.id.btn_url_direct_browser);
        btnURLInternalWebview = (Button) findViewById(R.id.btn_url_internal_webview);
        btnDisplayDataInternalWebview = (Button) findViewById(R.id.btn_display_data_html_webview);
        btnDisplayFileLocalWebview = (Button) findViewById(R.id.btn_display_local_file_webview);

        btnURLDirectBrowser.setOnClickListener(this);
        btnURLInternalWebview.setOnClickListener(this);
        btnDisplayDataInternalWebview.setOnClickListener(this);
        btnDisplayFileLocalWebview.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_url_direct_browser:
                Uri uri = Uri.parse("http://www.thanhnotes.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.btn_url_internal_webview:
                Intent intentURLInternalWebview = new Intent(WebviewModuleActivity.this, WebviewModuleDetailActivity.class);
                intentURLInternalWebview.putExtra(ConstantUtil.PARAM_INTENT_URL_INTERNAL_WEBVIEW, "http://www.thanhnotes.com");
                startActivity(intentURLInternalWebview);
                break;
            case R.id.btn_display_data_html_webview:
                Intent intentDataHtmlWebview = new Intent(WebviewModuleActivity.this, WebviewModuleDetailActivity.class);
                String dataHTML = "<html><body>You scored <b>192</b> points.</body></html>";
                intentDataHtmlWebview.putExtra(ConstantUtil.PARAM_INTENT_DATA_INTERNAL_WEBVIEW, dataHTML);
                startActivity(intentDataHtmlWebview);
                break;
            case R.id.btn_display_local_file_webview:
                Intent intentLocalFileWebview = new Intent(WebviewModuleActivity.this, WebviewModuleDetailActivity.class);
                String filePath = "file:///android_asset/internal_file_1.html";
                intentLocalFileWebview.putExtra(ConstantUtil.PARAM_INTENT_FILE_PATH_WEBVIEW, filePath);
                startActivity(intentLocalFileWebview);
                break;

        }
    }
}
