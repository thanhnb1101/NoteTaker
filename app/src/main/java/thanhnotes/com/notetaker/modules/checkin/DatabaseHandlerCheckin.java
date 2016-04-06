package thanhnotes.com.notetaker.modules.checkin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import thanhnotes.com.notetaker.utils.ConstantUtil;

/**
 * Created by Jen's on 4/6/2016.
 */
public class DatabaseHandlerCheckin extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Check in Table Columns names
    private static final String NOTE = "note";
    private static final String ADDRESS = "address";
    private static final String LONGITUDE = "longitude";
    private static final String LATITUDE = "latitude";

    public DatabaseHandlerCheckin(Context context) {
        super(context, ConstantUtil.DATABASE_NAME, null, DATABASE_VERSION);
    }
    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CHECKIN_TABLE = "CREATE TABLE "+ ConstantUtil.TABLE_CHECKIN + "("
                +NOTE + " TEXT,"
                +ADDRESS + " TEXT,"
                +LONGITUDE + " DOUBLE,"
                +LATITUDE + " DOUBLE" + ")";
        db.execSQL(CREATE_CHECKIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ConstantUtil.TABLE_CHECKIN);

        // Create tables again
        onCreate(db);
    }

    // Adding new check in
    void addCheckIn(CheckIn checkIn) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NOTE, checkIn.getNote()); //
        values.put(ADDRESS, checkIn.getAddress()); //
        values.put(LONGITUDE, checkIn.getLongitude());
        values.put(LATITUDE, checkIn.getLatitude());

        // Inserting Row
        db.insert(ConstantUtil.TABLE_CHECKIN, null, values);
        db.close(); // Closing database connection
    }

}
