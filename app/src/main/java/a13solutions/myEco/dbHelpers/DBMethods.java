package a13solutions.myEco.dbHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import a13solutions.myEco.MainActivity;

/**
 * Created by 13120dde on 2017-09-17.
 */

public final class DBMethods {

    private UserDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues values;

    public DBMethods(MainActivity activity) {
        dbHelper = new UserDBHelper(activity);
    }

    public String getUserEmail(String email) {
        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+UserDBHelper.COLUMN_EMAIL+" FROM "+UserDBHelper.TABLE_NAME+" WHERE "+UserDBHelper.COLUMN_EMAIL+" = '"+email+"'",null);
        cursor.moveToFirst();
        String res;
        if(cursor==null || cursor.getCount()<=0){
            res="EMAIL NOT IN DB";
        }else{
            res = cursor.getString(0);
        }
        Log.d("IN_DB","getUserEmail input: "+email+" | in db:"+res);
        return res;
    }

    public void registerUser(String email, String password, String firstName, String surname) {
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(UserDBHelper.COLUMN_EMAIL, email);
        values.put(UserDBHelper.COLUMN_PASSWORD, password);
        values.put(UserDBHelper.COLUMN_FIRST_NAME, firstName);
        values.put(UserDBHelper.COLUMN_SURNAME, surname);
        db.insert(UserDBHelper.TABLE_NAME,"",values);
        Log.d("IN_DB","registerUser(...): ");
    }
}
