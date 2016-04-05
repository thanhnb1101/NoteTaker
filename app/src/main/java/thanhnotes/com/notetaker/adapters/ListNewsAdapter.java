package thanhnotes.com.notetaker.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import thanhnotes.com.notetaker.R;
import thanhnotes.com.notetaker.models.NewsItem;

/**
 * Created by nguyenbathanh on 4/5/16.
 */
public class ListNewsAdapter extends BaseAdapter {
    private List<NewsItem> listData;
    private Activity activity;


    //Constructor
    public ListNewsAdapter(List<NewsItem> _listData, Activity _activity) {
        this.listData = _listData;
        this.activity = _activity;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ObjectsHolder objectsHolder;
        if (view != null && view.getTag() != null) {
            objectsHolder = (ObjectsHolder) view.getTag();
        }  else {
            objectsHolder = new ObjectsHolder();
            view = activity.getLayoutInflater().inflate(R.layout.item_news, null);
            objectsHolder.tvNewsTitle = (TextView) view.findViewById(R.id.tv_news_title);
            objectsHolder.ivNewsThumb = (ImageView) view.findViewById(R.id.iv_thumb_news);
        }

        view.setTag(objectsHolder);

        NewsItem newsItem = listData.get(i);
        objectsHolder.tvNewsTitle.setText(newsItem.getNewsTitle());
        //objectsHolder.ivNewsThumb.setBackgroundResource();
        return view;
    }

    private class ObjectsHolder {
        TextView tvNewsTitle;
        ImageView ivNewsThumb;
    }
}
