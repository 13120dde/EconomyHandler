package a13solutions.myEco.dbHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.model.ReturnPacket;

import static a13solutions.myEco.dbHelpers.DBHelper.COLUMN_AMOUNT;
import static a13solutions.myEco.dbHelpers.DBHelper.COLUMN_CATEGORY;
import static a13solutions.myEco.dbHelpers.DBHelper.COLUMN_DAY;
import static a13solutions.myEco.dbHelpers.DBHelper.COLUMN_MONTH;
import static a13solutions.myEco.dbHelpers.DBHelper.COLUMN_TITLE;
import static a13solutions.myEco.dbHelpers.DBHelper.COLUMN_USER_EMAIL;
import static a13solutions.myEco.dbHelpers.DBHelper.COLUMN_YEAR;
import static a13solutions.myEco.dbHelpers.DBHelper.EXPENDITURE_TABLE_NAME;
import static a13solutions.myEco.dbHelpers.DBHelper.INCOME_TABLE_NAME;

/**
 * Created by 13120dde on 2017-09-17.
 */

public final class DBManager {

    private DBHelper dbHelperUser;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues values;
    private MainActivity actvity;

    public DBManager(MainActivity activity) {
        this.actvity=activity;
    }

    public ReturnPacket getUserEmail(String email) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+ DBHelper.USER_COLUMN_EMAIL +" FROM "+ DBHelper.USER_TABLE_NAME +" WHERE "+ DBHelper.USER_COLUMN_EMAIL +" = '"+email+"'",null);
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
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.USER_COLUMN_EMAIL, email);
        values.put(DBHelper.USER_COLUMN_PASSWORD, password);
        values.put(DBHelper.USER_COLUMN_FIRST_NAME, firstName);
        values.put(DBHelper.USER_COLUMN_SURNAME, surname);
        db.insert(DBHelper.USER_TABLE_NAME,"",values);
        Log.d("IN_DB","registerUser(...): ");
    }


    public ReturnPacket login(String email){
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+ DBHelper.USER_COLUMN_PASSWORD +", "+ DBHelper.USER_COLUMN_FIRST_NAME
                +", "+ DBHelper.USER_COLUMN_SURNAME +" FROM "+ DBHelper.USER_TABLE_NAME +" WHERE "+ DBHelper.USER_COLUMN_EMAIL +" = '"+email+"'",null);

        ReturnPacket res;



        if(cursor==null || cursor.getCount()<=0){
            res= new ReturnPacket(false,"This email is not registered");
        }else{
            int firstNameIndex = cursor.getColumnIndex(DBHelper.USER_COLUMN_FIRST_NAME);
            int surNameIndex = cursor.getColumnIndex(DBHelper.USER_COLUMN_SURNAME);
            int passwordIndex = cursor.getColumnIndex(DBHelper.USER_COLUMN_PASSWORD);


            cursor.moveToFirst();
            HashMap<String, String> vals = new HashMap<>();
            vals.put(DBHelper.USER_COLUMN_FIRST_NAME,cursor.getString(firstNameIndex));
            vals.put(DBHelper.USER_COLUMN_SURNAME,cursor.getString(surNameIndex));
            vals.put(DBHelper.USER_COLUMN_PASSWORD, cursor.getString(passwordIndex));

            res = new ReturnPacket(true, vals);

        }
        if(cursor!=null)
            cursor.close();
        return res;
    }

    public void putIncome(String email, String title, String category, int[] dayMonthYear, double amountReal) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getWritableDatabase();
        values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_AMOUNT, amountReal);
        values.put(COLUMN_DAY, dayMonthYear[0]);
        values.put(COLUMN_MONTH, dayMonthYear[1]);
        values.put(COLUMN_YEAR, dayMonthYear[2]);
        db.insert(INCOME_TABLE_NAME,"",values);
    }

    public void putExpenditure(String email, String title, String category, int[] dayMonthYear, double amountReal) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getWritableDatabase();
        values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_AMOUNT, amountReal);
        values.put(COLUMN_DAY, dayMonthYear[0]);
        values.put(COLUMN_MONTH, dayMonthYear[1]);
        values.put(COLUMN_YEAR, dayMonthYear[2]);
        db.insert(EXPENDITURE_TABLE_NAME,"",values);

    }
}
