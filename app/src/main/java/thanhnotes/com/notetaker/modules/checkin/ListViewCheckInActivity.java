package thanhnotes.com.notetaker.modules.checkin;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import thanhnotes.com.notetaker.R;
import thanhnotes.com.notetaker.utils.ConstantUtil;

/**
 * Created by Jen's on 4/6/2016.
 */
public class ListViewCheckInActivity extends ListActivity {
    private TextView txtCheckInNote, txtCheckInAddress;
    private String note, address;

    ArrayList<HashMap<String, String>> checkInList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_checkin_layout);
        txtCheckInAddress = (TextView)findViewById(R.id.txt_check_address);
        txtCheckInNote = (TextView)findViewById(R.id.txt_check_in_note);
        ListView lv = getListView();
        checkInList = new ArrayList<HashMap<String, String>>();

        new GetCheckIn().execute();
    }
    private class GetCheckIn extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String selectQuery = "SELECT * FROM " + ConstantUtil.TABLE_CHECKIN;
            SQLiteDatabase db = openOrCreateDatabase(ConstantUtil.DATABASE_NAME, Context.MODE_PRIVATE,null);
            Cursor c = db.rawQuery(selectQuery, null);
            while(c.moveToNext())
            {
                note=c.getString(0);
                address=c.getString(1);

                HashMap<String, String> contact = new HashMap<String, String>();
                // adding each child node to HashMap key => value

                contact.put(ConstantUtil.TAG_CHECKIN_NOTE, note);
                contact.put(ConstantUtil.TAG_CHECKIN_ADDRESS, address);

                checkInList.add(contact);

            }
            return null;
        }
        protected void onPostExecute(Void result) {
            ListAdapter adapter = new SimpleAdapter(
                    ListViewCheckInActivity.this, checkInList, R.layout.item_checkin,
                    new String[] {ConstantUtil.TAG_CHECKIN_ADDRESS, ConstantUtil.TAG_CHECKIN_NOTE,},
                    new int[] {R.id.txt_check_address, R.id.txt_check_in_note,});
            setListAdapter(adapter);
        }


    }
}
