package thanhnotes.com.notetaker.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import thanhnotes.com.notetaker.R;
import thanhnotes.com.notetaker.models.ModuleItem;

/**
 * Created by nguyenbathanh on 4/4/16.
 */
public class ListModuleAdapter extends BaseAdapter {
    private List<ModuleItem> listData;
    private Activity activity;

    public ListModuleAdapter(List<ModuleItem> _listData, Activity _activity) {
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
        } else {
            objectsHolder = new ObjectsHolder();
            view = activity.getLayoutInflater().inflate(R.layout.item_module, null);
            objectsHolder.tvModuleName = (TextView) view.findViewById(R.id.tv_module_name);
        }

        view.setTag(objectsHolder);

        ModuleItem item = new ModuleItem();
        item = listData.get(i);
        objectsHolder.tvModuleName.setText(item.getModuleName());

        return view;
    }

    private class ObjectsHolder {
        TextView tvModuleName;
    }

}
