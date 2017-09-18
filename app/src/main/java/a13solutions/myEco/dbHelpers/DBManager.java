package a13solutions.myEco.dbHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.model.DataFragment;
import a13solutions.myEco.model.ExpIncItem;
import a13solutions.myEco.model.ReturnPacket;

import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_AMOUNT;
import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_CATEGORY;
import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_DATE;
import static a13solutions.myEco.dbHelpers.DBHelper.EX_INC_COLUMN_ID;
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



    public ArrayList<ExpIncItem> getExpenditures(String email, String dateFrom, String dateTo, ArrayList<ExpIncItem> expenditureArray) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getReadableDatabase();
        int index = 0;
        double totalAmount=0;

        cursor = db.rawQuery("SELECT "+EX_INC_COLUMN_TITLE+", "+EX_INC_COLUMN_CATEGORY+", "
                +EX_INC_COLUMN_AMOUNT+", "+EX_INC_COLUMN_DATE+", "+EX_INC_COLUMN_ID
                +" FROM "+EXPENDITURE_TABLE_NAME
                +" WHERE "+EX_INC_COLUMN_USER_EMAIL+" = '"+email+"' AND ("
                + EX_INC_COLUMN_DATE +" BETWEEN '"+dateFrom+"' AND '"+dateTo+"')"
                ,null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            String title = cursor.getString(cursor.getColumnIndex(EX_INC_COLUMN_TITLE));
            String category = cursor.getString(cursor.getColumnIndex(EX_INC_COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndex(EX_INC_COLUMN_DATE));
            double amount = cursor.getDouble(cursor.getColumnIndex(EX_INC_COLUMN_AMOUNT));
            int key = cursor.getInt(cursor.getColumnIndex(EX_INC_COLUMN_ID));
            expenditureArray.add(new ExpIncItem(title,category,date,amount,key,index));
            index++;
            totalAmount+=amount;
        }

        //just to skip iterating trough array later to calculate total amount
        expenditureArray.add(new ExpIncItem("","","",totalAmount,0,0));
        return expenditureArray;
    }

    public ArrayList<ExpIncItem> getIncomes(String email, String dateFrom, String dateTo, ArrayList<ExpIncItem> incomeArray) {
        dbHelperUser = new DBHelper(actvity);
        db = dbHelperUser.getReadableDatabase();
        int index = 0;
        double totalAmount=0;

        cursor = db.rawQuery("SELECT "+EX_INC_COLUMN_TITLE+", "+EX_INC_COLUMN_CATEGORY+", "
                        +EX_INC_COLUMN_AMOUNT+", "+EX_INC_COLUMN_DATE+", "+EX_INC_COLUMN_ID
                        +" FROM "+INCOME_TABLE_NAME
                        +" WHERE "+EX_INC_COLUMN_USER_EMAIL+" = '"+email+"' AND ("
                        + EX_INC_COLUMN_DATE +" BETWEEN '"+dateFrom+"' AND '"+dateTo+"')"
                ,null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(EX_INC_COLUMN_TITLE));
            String category = cursor.getString(cursor.getColumnIndex(EX_INC_COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndex(EX_INC_COLUMN_DATE));
            double amount = cursor.getDouble(cursor.getColumnIndex(EX_INC_COLUMN_AMOUNT));
            int key = cursor.getInt(cursor.getColumnIndex(EX_INC_COLUMN_ID));
            incomeArray.add(new ExpIncItem(title,category,date,amount,key,index));
            index++;
            totalAmount+=amount;
        }

        //just to skip iterating trough array later to calculate total amount
        incomeArray.add(new ExpIncItem("","","",totalAmount,0,0));
        return incomeArray;
    }
}
