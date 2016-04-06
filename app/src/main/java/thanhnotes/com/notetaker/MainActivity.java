package thanhnotes.com.notetaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import thanhnotes.com.notetaker.adapters.ListModuleAdapter;
import thanhnotes.com.notetaker.models.ModuleItem;
import thanhnotes.com.notetaker.modules.camera.CameraModuleActivity;
import thanhnotes.com.notetaker.modules.checkin.CheckInModuleActivity;
import thanhnotes.com.notetaker.modules.webview.WebviewModuleActivity;
import thanhnotes.com.notetaker.utils.ConstantUtil;

public class MainActivity extends AppCompatActivity {
    private ListView lvModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvModule = (ListView) findViewById(R.id.lv_module);
        final List<ModuleItem> listData = new ArrayList<>();
        ModuleItem item1 = new ModuleItem();
        item1.setModuleName("Camera Module");
        item1.setModuleID(ConstantUtil.MODULE_CAMERA);
        listData.add(item1);

        ModuleItem item2 = new ModuleItem();
        item2.setModuleName("Webview Module");
        item2.setModuleID(ConstantUtil.MODULE_WEBVIEW);
        listData.add(item2);

        ModuleItem item3 = new ModuleItem();
        item3.setModuleName("Webservice Module");
        item3.setModuleID(ConstantUtil.MODULE_WEBSERVICE);
        listData.add(item3);

        ModuleItem item4 = new ModuleItem();
        item4.setModuleName("Check In Module");
        item4.setModuleID(ConstantUtil.MODULE_CHECKIN);
        listData.add(item4);

        ListModuleAdapter adapter = new ListModuleAdapter(listData, this);
        lvModule.setAdapter(adapter);

        lvModule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModuleItem item = listData.get(i);
                switch (item.getModuleID()) {
                    case ConstantUtil.MODULE_CAMERA:
                        Intent intentCamera = new Intent(MainActivity.this, CameraModuleActivity.class);
                        startActivity(intentCamera);
                        break;

                    case ConstantUtil.MODULE_WEBVIEW:
                        Intent intentWebview = new Intent(MainActivity.this, WebviewModuleActivity.class);
                        startActivity(intentWebview);
                        break;
                    case ConstantUtil.MODULE_CHECKIN:
                        Intent intentCheckIn = new Intent(MainActivity.this, CheckInModuleActivity.class);
                        startActivity(intentCheckIn);
                        break;
                }
            }
        });

    }

}
