package a13solutions.myEco.dbHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.model.ReturnPacket;

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

    public ReturnPacket getUserEmail(String email) {
        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+UserDBHelper.COLUMN_EMAIL+" FROM "+UserDBHelper.TABLE_NAME+" WHERE "+UserDBHelper.COLUMN_EMAIL+" = '"+email+"'",null);
        cursor.moveToFirst();
        ReturnPacket res;
        if(cursor==null || cursor.getCount()<=0){
            res= new ReturnPacket(false,"This email is not registered");
        }else{
            res = new ReturnPacket(true,cursor.getString(0));
        }
        if(cursor!=null)
            cursor.close();
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

    /*
    public ReturnPacket login(String email, String password){
        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+UserDBHelper.COLUMN_PASSWORD+" FROM "+UserDBHelper.TABLE_NAME+" WHERE "+UserDBHelper.COLUMN_EMAIL+" = '"+email+"'",null);

        ReturnPacket res;
        if(cursor==null || cursor.getCount()<=0){
            res= new ReturnPacket(false,"This email is not registered");
        }else{
            cursor.moveToFirst();
            res = new ReturnPacket(true,cursor.getString(cursor.getPosition()));
        }
        if(cursor!=null)
            cursor.close();
        return res;
    }*/

    public ReturnPacket login(String email, String password){
        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+UserDBHelper.COLUMN_PASSWORD+", "+UserDBHelper.COLUMN_FIRST_NAME
                +", "+UserDBHelper.COLUMN_SURNAME+" FROM "+UserDBHelper.TABLE_NAME+" WHERE "+UserDBHelper.COLUMN_EMAIL+" = '"+email+"'",null);

        ReturnPacket res;



        if(cursor==null || cursor.getCount()<=0){
            res= new ReturnPacket(false,"This email is not registered");
        }else{
            int firstNameIndex = cursor.getColumnIndex(UserDBHelper.COLUMN_FIRST_NAME);
            int surNameIndex = cursor.getColumnIndex(UserDBHelper.COLUMN_SURNAME);
            int passwordIndex = cursor.getColumnIndex(UserDBHelper.COLUMN_PASSWORD);


            cursor.moveToFirst();
            HashMap<String, String> vals = new HashMap<>();
            vals.put(UserDBHelper.COLUMN_FIRST_NAME,cursor.getString(firstNameIndex));
            vals.put(UserDBHelper.COLUMN_SURNAME,cursor.getString(surNameIndex));
            vals.put(UserDBHelper.COLUMN_PASSWORD, cursor.getString(passwordIndex));

            res = new ReturnPacket(true, vals);

        }
        if(cursor!=null)
            cursor.close();
        return res;
    }
}
