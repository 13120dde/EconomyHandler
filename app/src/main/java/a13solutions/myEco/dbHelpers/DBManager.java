package a13solutions.myEco.dbHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.model.ReturnPacket;

/**
 * Created by 13120dde on 2017-09-17.
 */

public final class DBManager {

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues values;
    private MainActivity actvity;

    public DBManager(MainActivity activity) {
        this.actvity=activity;
    }

    public ReturnPacket getUserEmail(String email) {
        dbHelper = new UserDBHelper(actvity);
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
        dbHelper = new UserDBHelper(actvity);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(UserDBHelper.COLUMN_EMAIL, email);
        values.put(UserDBHelper.COLUMN_PASSWORD, password);
        values.put(UserDBHelper.COLUMN_FIRST_NAME, firstName);
        values.put(UserDBHelper.COLUMN_SURNAME, surname);
        db.insert(UserDBHelper.TABLE_NAME,"",values);
        Log.d("IN_DB","registerUser(...): ");
    }


    public ReturnPacket login(String email){
        dbHelper = new UserDBHelper(actvity);
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

    public void putIncome(String email, String title, String category, int[] dayMonthYear, double amountReal) {
        dbHelper = new IncomeDBHelper(actvity);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(IncomeDBHelper.COLUMN_USER_EMAIL, email);
        values.put(IncomeDBHelper.COLUMN_TITLE, title);
        values.put(IncomeDBHelper.COLUMN_CATEGORY, category);
        values.put(IncomeDBHelper.COLUMN_AMOUNT, amountReal);
        values.put(IncomeDBHelper.COLUMN_DAY, dayMonthYear[0]);
        values.put(IncomeDBHelper.COLUMN_MONTH, dayMonthYear[1]);
        values.put(IncomeDBHelper.COLUMN_YEAR, dayMonthYear[2]);
        db.insert(IncomeDBHelper.TABLE_NAME,"",values);
    }

    public void putExpenditure(String email, String title, String category, int[] dayMonthYear, double amountReal) {
        dbHelper = new ExpenditureDBHelper(actvity);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(IncomeDBHelper.COLUMN_USER_EMAIL, email);
        values.put(IncomeDBHelper.COLUMN_TITLE, title);
        values.put(IncomeDBHelper.COLUMN_CATEGORY, category);
        values.put(IncomeDBHelper.COLUMN_AMOUNT, amountReal);
        values.put(IncomeDBHelper.COLUMN_DAY, dayMonthYear[0]);
        values.put(IncomeDBHelper.COLUMN_MONTH, dayMonthYear[1]);
        values.put(IncomeDBHelper.COLUMN_YEAR, dayMonthYear[2]);
        db.insert(IncomeDBHelper.TABLE_NAME,"",values);

    }
}
