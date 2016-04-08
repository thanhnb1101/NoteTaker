package thanhnotes.com.notetaker.modules.webservice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import thanhnotes.com.notetaker.R;
import thanhnotes.com.notetaker.adapters.ListNewsAdapter;
import thanhnotes.com.notetaker.models.NewsItem;

/**
 * Created by nguyenbathanh on 4/5/16.
 */
public class WebserviceModuleActivity extends Activity {
    private final String URL_JSON = "http://www.enutriapp.com/api/get_posts/";
    private List<NewsItem> listData;
    private ListView lvNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_webservice_layout);

        listData = NewsParser.getNews(getApplicationContext(), URL_JSON);

        lvNews = (ListView) findViewById(R.id.lv_news);
        ListNewsAdapter listNewsAdapter = new ListNewsAdapter(listData, this);
        lvNews.setAdapter(listNewsAdapter);

    }
}
