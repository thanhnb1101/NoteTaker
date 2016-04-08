package thanhnotes.com.notetaker.modules.webservice;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import thanhnotes.com.notetaker.models.NewsItem;
import thanhnotes.com.notetaker.utils.ConnectionDetector;

/**
 * Created by nguyenbathanh on 4/8/16.
 */
public class NewsParser {
    private static final String TAG_POSTS = "posts";
    private static final String TAG_TITLE = "title";
    private static final String TAG_THUMBNAIL = "thumbnail";

    public static List<NewsItem> getNews(Context context, String linkJSON) {
        JSONArray cateJsonArr = null;
        List<NewsItem> listData = new ArrayList<NewsItem>();
        ConnectionDetector connectionDetector = new ConnectionDetector(context);
        String strRespone;
        if (connectionDetector.isConnectingToInternet()) {
            ServiceHandler sh = new ServiceHandler();

            strRespone = sh.makeServiceCall(linkJSON, ServiceHandler.GET);

            if (strRespone != null) try {
                JSONObject jsonObj = new JSONObject(strRespone);

                // Getting JSON Array node
                cateJsonArr = jsonObj.getJSONArray(TAG_POSTS);

                // looping through All Contacts
                for (int i = 0; i < cateJsonArr.length(); i++) {
                    JSONObject c = cateJsonArr.getJSONObject(i);
                    NewsItem item = new NewsItem();

                    String title = c.getString(TAG_TITLE);

                    String thumb = c.getString(TAG_THUMBNAIL);

                    item.setNewsTitle(title);
                    item.setNewsThumb(thumb);

                    listData.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        }

        return listData;
    }
}
