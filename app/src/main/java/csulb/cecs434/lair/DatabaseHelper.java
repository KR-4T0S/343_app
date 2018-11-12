package csulb.cecs434.lair;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "account.db";
    public static final String TABLE_NAME = "account_table";
    public static final String COL_1 = "PINCODE";
    public static final String COL_2 = "FirstEntryPINCODE";
    public static final String COL_3 = "SecondEntryPINCODE";
    public static final String COL_4 = "HasProfile";
 //   private static final Object ContentValues = ;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        if (checkForTableExists(db, TABLE_NAME) == false){
            db.execSQL("create table " + TABLE_NAME + "(PINCODE INTEGER PRIMARY KEY, FirstEntryPINCODE INTEGER," +
                    "SecondEntryPINCODE INTEGER, HasProfile BOOLEAN)");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(Integer pincode, Integer firstEntryPincode, Integer secondEntryPincode, Boolean hasProfile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, pincode);
        contentValues.put(COL_2, firstEntryPincode);
        contentValues.put(COL_3, secondEntryPincode);
        contentValues.put(COL_4, hasProfile);
        long result = db.insert(TABLE_NAME, null,contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }

    }


    private boolean checkForTableExists(SQLiteDatabase db, String table){
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+table+"'";
        Cursor mCursor = db.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }


}
