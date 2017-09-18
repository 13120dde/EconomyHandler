package a13solutions.myEco.dbHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.model.ReturnPacket;

import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_AMOUNT;
import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_CATEGORY;
import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_DATE;
import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_TITLE;
import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_USER_EMAIL;
import static a13solutions.myEco.dbHelpers.DBHelper.EXPENDITURE_TABLE_NAME;
import static a13solutions.myEco.dbHelpers.DBHelper.INCOME_TABLE_NAME;
import static a13solutions.myEco.dbHelpers.DBHelper.USER_COLUMN_EMAIL;
import static a13solutions.myEco.dbHelpers.DBHelper.USER_TABLE_NAME;

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

    public void putIncome(String email, String title, String category, String date, double amountReal) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getWritableDatabase();
        values = new ContentValues();
        values.put(EX_INC_COLUMN_USER_EMAIL, email);
        values.put(EX_INC_COLUMN_TITLE, title);
        values.put(EX_INC_COLUMN_CATEGORY, category);
        values.put(EX_INC_COLUMN_AMOUNT, amountReal);
        values.put(EX_INC_COLUMN_DATE, date);
        db.insert(INCOME_TABLE_NAME,"",values);
    }

    public void putExpenditure(String email, String title, String category, String date, double amountReal) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getWritableDatabase();
        values = new ContentValues();
        values.put(EX_INC_COLUMN_USER_EMAIL, email);
        values.put(EX_INC_COLUMN_TITLE, title);
        values.put(EX_INC_COLUMN_CATEGORY, category);
        values.put(EX_INC_COLUMN_AMOUNT, amountReal);
        values.put(EX_INC_COLUMN_DATE, date);
        db.insert(EXPENDITURE_TABLE_NAME,"",values);

    }

    public ReturnPacket getUserEmail(String email) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+ DBHelper.USER_COLUMN_EMAIL
                        +" FROM "+ DBHelper.USER_TABLE_NAME
                        +" WHERE "+ DBHelper.USER_COLUMN_EMAIL +" = '"+email+"'"
                ,null);

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

    public ReturnPacket login(String email){
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+ DBHelper.USER_COLUMN_PASSWORD +", "
                + DBHelper.USER_COLUMN_FIRST_NAME +", "
                + DBHelper.USER_COLUMN_SURNAME
                +" FROM "+ DBHelper.USER_TABLE_NAME
                +" WHERE "+ DBHelper.USER_COLUMN_EMAIL +" = '"+email+"'"
                ,null);

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



    public void getExpenditures(String email, String dateFrom, String dateTo) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+EX_INC_COLUMN_TITLE+", "+EX_INC_COLUMN_CATEGORY+", "
                +EX_INC_COLUMN_AMOUNT+", "+EX_INC_COLUMN_DATE
                +"FROM "+EXPENDITURE_TABLE_NAME +" JOIN "+USER_TABLE_NAME
                +" ON "+ EX_INC_COLUMN_USER_EMAIL +" = "+USER_COLUMN_EMAIL
                +" WHERE "+ EX_INC_COLUMN_DATE +" BETWEEN '"+dateFrom+"' AND '"+dateTo+"'"
                ,null);
    }

    public void getIncomes(String email, String dateFrom, String dateTo) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+EX_INC_COLUMN_TITLE+", "+EX_INC_COLUMN_CATEGORY+", "
                        +EX_INC_COLUMN_AMOUNT+", "+EX_INC_COLUMN_DATE
                        +"FROM "+INCOME_TABLE_NAME +" JOIN "+USER_TABLE_NAME
                        +" ON "+ EX_INC_COLUMN_USER_EMAIL +" = "+USER_COLUMN_EMAIL
                        +" WHERE "+ EX_INC_COLUMN_DATE +" BETWEEN '"+dateFrom+"' AND '"+dateTo+"'"
                ,null);
    }

}
