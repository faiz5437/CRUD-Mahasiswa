package com.example.crudmahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    private String TAG = "SQLHelper";

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "dbMahasiswa";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "biodata";

    private static final String ID_COL = "id";
    private static final String NIM_COL = "nim";
    private static final String NAME_COL = "nama";
    private static final String TGL_COL = "tgl";
    private static final String JK_COL = "jk";
    private static final String ALAMAT_COL = "alamat";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NIM_COL + " TEXT,"
                + NAME_COL + " TEXT,"
                + TGL_COL + " TEXT,"
                + JK_COL + " TEXT,"
                + ALAMAT_COL+ " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewData(String nimMhs,String namaMhs, String tglMhs, String jkMhs, String alamatMhs) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NIM_COL, nimMhs);
        values.put(NAME_COL, namaMhs);
        values.put(TGL_COL, tglMhs);
        values.put(JK_COL, jkMhs);
        values.put(ALAMAT_COL, alamatMhs);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<DataModel> readData() {
        // on below line we are creating a
        // database for reading our database.
        
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorData = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<DataModel> dataModelArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorData.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                Log.d(TAG, "readCourses: cek 0: "+ cursorData.getString(0));
                Log.d(TAG, "readCourses: cek 1: "+ cursorData.getString(1));
                Log.d(TAG, "readCourses: cek 2: "+ cursorData.getString(2));
                Log.d(TAG, "readCourses: cek 3: "+ cursorData.getString(3));
                Log.d(TAG, "readCourses: cek 4: "+ cursorData.getString(4));
                Log.d(TAG, "readCourses: cek 5: "+ cursorData.getString(5));

                dataModelArrayList.add(new DataModel(cursorData.getInt(0),
                        cursorData.getString(1),
                        cursorData.getString(2),
                        cursorData.getString(3),
                        cursorData.getString(4),
                        cursorData.getString(5)));
//                DataModelArrayList.add(new DataModel(cursorData.getString(1),
//                        cursorData.getString(2),
//                        cursorData.getString(3),
//                        cursorData.getString(4),
//                        cursorData.getInt(0)));
            } while (cursorData.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorData.close();
        return dataModelArrayList;
    }

    // below is the method for updating our courses
    public void updateData(String idMhs, String nimMhs,String namaMhs, String tglMhs, String jkMhs, String alamatMhs) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NIM_COL, nimMhs);
        values.put(NAME_COL, namaMhs);
        values.put(TGL_COL, tglMhs);
        values.put(JK_COL, jkMhs);
        values.put(ALAMAT_COL, alamatMhs);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "id=?", new String[]{idMhs});
        db.close();
    }

    // below is the method for deleting our course.
    public void deleteData(String idMhs) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.

        db.delete(TABLE_NAME, "id=?", new String[]{idMhs});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
