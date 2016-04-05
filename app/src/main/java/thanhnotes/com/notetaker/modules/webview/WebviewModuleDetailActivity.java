package thanhnotes.com.notetaker.modules.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import thanhnotes.com.notetaker.R;
import thanhnotes.com.notetaker.utils.ConstantUtil;

/**
 * Created by nguyenbathanh on 4/5/16.
 */
public class WebviewModuleDetailActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_webview_detail_layout);
        webView = (WebView) findViewById(R.id.wv_module_detail);

        Intent intent = getIntent();

        //Hứng giá trị url truyền từ activity WebviewModuleActivity sang
        String url = intent.getStringExtra(ConstantUtil.PARAM_INTENT_URL_INTERNAL_WEBVIEW);
        if (url != null && url.length() > 0) { //check nếu có giá trị thì load content html qua url này vào trong webview
            webView.loadUrl(url);
        }


        String dataHTML = intent.getStringExtra(ConstantUtil.PARAM_INTENT_DATA_INTERNAL_WEBVIEW);
        if (dataHTML != null && dataHTML.length() > 0) {
            webView.loadData(dataHTML, "text/html", null);
            // ... although note that there are restrictions on what this HTML can do.
            // See the JavaDocs for loadData() and loadDataWithBaseURL() for more info.
        }


        String filePath = intent.getStringExtra(ConstantUtil.PARAM_INTENT_FILE_PATH_WEBVIEW);
        if (filePath != null && filePath.length() > 0) {
            webView.loadUrl(filePath);
        }

    }
}
