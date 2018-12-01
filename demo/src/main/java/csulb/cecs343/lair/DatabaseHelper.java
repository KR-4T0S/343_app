package csulb.cecs343.lair;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "account.db";
    public static final String TABLE_NAME = "account_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PINCODE";
    public static final String COL_3 = "USERTRAINED";
    public static final String COL_4 = "FAILEDFACIALIDENTIFICATION";
    public static final String COL_5 = "FAILEDPINCODEIDENTIFICATION";
    //   private static final Object ContentValues = ;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        preLoaded();
    }

    private void preLoaded(){
        Cursor res = getResCount();
        if(res.getCount() == 0){
            //show message
            insertData("",false,0,0);
        }
    }

    public Cursor getResCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PINCODE TEXT,  USERTRAINED  BOOLEAN, FAILEDFACIALIDENTIFICATION  INTEGER,  FAILEDPINCODEIDENTIFICATION  INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean updatePincode(String pincode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, pincode);
        db.update(TABLE_NAME,contentValues, "ID = ?", new String[]{"1"});
        return true;
    }

    public boolean updateModelTrained(String one){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, one);
        db.update(TABLE_NAME,contentValues, "ID = ?", new String[]{"1"});
        return true;
    }


    public boolean insertData(String pincode, boolean userTrained, Integer failedFacialIndentification, Integer failedPincodeIdentification){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, pincode);
        contentValues.put(COL_3, userTrained);
        contentValues.put(COL_4, failedFacialIndentification);
        contentValues.put(COL_5, failedPincodeIdentification);
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

    public String random_test(){
        String r = "whatever";
        return r;
    }

    public String getPincode(){
        String pincode = "";
        SQLiteDatabase dber = this.getReadableDatabase();

        Cursor res = getResCount();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            //          buffer.append("Id : " + res.getString(0) + "\n");
            buffer.append(res.getString(1));
            //         buffer.append("userTrained : " + res.getString(2) + "\n");
            //         buffer.append("FailedFace : " + res.getString(3) + "\n");
            //          buffer.append("FailedPin : " + res.getString(3) + "\n");
        }

        pincode = buffer.toString();
        res.close();
        return pincode;
    }

    public String getUserTrained() {
        String trained = "";
        SQLiteDatabase dber = this.getReadableDatabase();

        Cursor res = getResCount();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //          buffer.append("Id : " + res.getString(0) + "\n");
            //buffer.append("pincode : " + res.getString(1));
            buffer.append(res.getString(2));
            //         buffer.append("FailedFace : " + res.getString(3) + "\n");
            //          buffer.append("FailedPin : " + res.getString(3) + "\n");
        }

        trained = buffer.toString();
        res.close();
        return trained;
    }
    public void deleteDB(Context mContext) {
        try {
            mContext.deleteDatabase(DATABASE_NAME);
        }finally {

        }
    }
}

