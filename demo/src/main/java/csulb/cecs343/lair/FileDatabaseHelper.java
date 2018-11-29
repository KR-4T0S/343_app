package csulb.cecs343.lair;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class FileDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "files.db";
    public static final String TABLE_FILES = "files";
    public static final String TABLE_FOLDERS = "folders";
    // File table cols
    public static final String FILES_PK1 = "fileid";
    public static final String FILES_COL1 = "file_source";
    public static final String FILES_COL2 = "file_name";
    public static final String FILES_COL3 = "file_type";
    public static final String FILES_FK1 = "folderid";
    // Folder table cols
    public static final String FOLDERS_PK1 = "folderid";
    public static final String FOLDERS_COL1 = "folder_name";
    public static final String FOLDERS_COL2 = "folder_source";
    public static final String FOLDERS_FK1 = "Parentfolderid";

    // Constructor
    public FileDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (checkForTableExists(db, TABLE_FOLDERS) == false){
            String query_create_folders = "CREATE TABLE folders " +
                    "(folderid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "folder_name TEXT NOT NULL, " +
                    "folder_source TEXT NOT NULL, " +
                    "Parentfolderid INTEGER, " +
                    "FOREIGN KEY (Parentfolderid) REFERENCES folders(folderid) ON DELETE CASCADE)";
            db.execSQL(query_create_folders);
        }
        if (checkForTableExists(db, TABLE_FILES) == false){
            //TODO: this should create files table
            String query_create_files = "CREATE TABLE files " +
                    "(fileid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "file_source TEXT NOT NULL, " +
                    "file_name TEXT NOT NULL, " +
                    "file_type TEXT NOT NULL, " +
                    "folderid INTEGER NOT NULL, " +
                    "FOREIGN KEY (folderid) REFERENCES folders(folderid) ON DELETE CASCADE)";
            db.execSQL(query_create_files);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FILES);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FOLDERS);
        onCreate(db);
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

    ///////////////////////////////////////////////////////////////////////
    ///////////////////// Query functions /////////////////////////////////
    ////////////////// Get / Set methods for queries //////////////////////
    ///////////////////////////////////////////////////////////////////////

    public boolean addFolder(String _folderName, String _folderSource, String _folderParentFolderID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String folderParentFolderID;

        // Format parameter for parent folder
        if (_folderParentFolderID.equals("") || _folderParentFolderID.equals("null".toLowerCase())){
            folderParentFolderID = "NULL";
        } else {
            folderParentFolderID = _folderParentFolderID;
        }

        // Set query values
        values.put(FOLDERS_COL1, _folderName);
        values.put(FOLDERS_COL2, _folderSource);
        values.put(FOLDERS_FK1, folderParentFolderID);
        // Execute query
        long result = db.insert(TABLE_FOLDERS, null, values);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean addFile(String _fileName, String _fileType, String _fileSource, String _fileParentFolderID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String fileParentFolderID;

        // Format parameter for parent folder
        if (_fileParentFolderID.equals("") || _fileParentFolderID.equals("null".toLowerCase())) {
            fileParentFolderID = "NULL";
        } else {
            fileParentFolderID = _fileParentFolderID;
        }

        values.put(FILES_COL1, _fileSource);
        values.put(FILES_COL2, _fileName);
        values.put(FILES_COL3, _fileType);
        values.put(FILES_FK1, fileParentFolderID);
        // Execute query
        long result = db.insert(TABLE_FILES, null, values);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<String> getChildrenFiles(String _folderID) {
        ArrayList<String> results = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        // Execute query
        Cursor cursor = db.rawQuery("SELECT * FROM files WHERE files.folderid IS ?",
                new String[] {_folderID});

        // Get results
        try {
            if (!cursor.moveToFirst())
                return results;
            do {
                String current = "";
                current = current + cursor.getString(cursor.getColumnIndex(FILES_PK1)) + "|";
                current = current + cursor.getString(cursor.getColumnIndex(FILES_COL1)) + "|";
                current = current + cursor.getString(cursor.getColumnIndex(FILES_COL2)) + "|";
                current = current + cursor.getString(cursor.getColumnIndex(FILES_COL3)) + "|";
                current = current + cursor.getString(cursor.getColumnIndex(FILES_FK1)) + "|";
                results.add(current);
            } while (cursor.moveToNext());
        } finally {

        }

        return results;
    }

    public ArrayList<String> getChildrenFolders(String _folderID) {
        ArrayList<String> results = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        // Execute query
        Cursor cursor = db.rawQuery("SELECT * FROM folders WHERE folders.Parentfolderid IS ?",
                new String[] {_folderID});

        // Get results
        try {
            if (!cursor.moveToFirst())
                return results;
            do {
                String current = "";
                current = current + cursor.getString(cursor.getColumnIndex(FOLDERS_PK1)) + "|";
                current = current + cursor.getString(cursor.getColumnIndex(FOLDERS_COL1)) + "|";
                current = current + cursor.getString(cursor.getColumnIndex(FOLDERS_COL2)) + "|";
                current = current + cursor.getString(cursor.getColumnIndex(FOLDERS_FK1)) + "|";
                results.add(current);
            } while (cursor.moveToNext());
        } finally {
            cursor.close();
        }

        return results;
    }
}
