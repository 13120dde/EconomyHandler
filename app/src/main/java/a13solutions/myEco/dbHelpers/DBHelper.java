package a13solutions.myEco.dbHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 13120dde on 2017-09-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    //table user
    public static final String USER_TABLE_NAME ="users";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_FIRST_NAME = "first_name";
    public static final String USER_COLUMN_SURNAME = "surname";

    //table expenditure/income
    public static final String EXPENDITURE_TABLE_NAME="expenditures";
    public static final String INCOME_TABLE_NAME="incomes";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_USER_EMAIL="user_email";
    public static final String COLUMN_TITLE="title";
    public static final String COLUMN_CATEGORY="category";
    public static final String COLUMN_AMOUNT="amount";
    public static final String COLUMN_DAY="day";
    public static final String COLUMN_MONTH="month";
    public static final String COLUMN_YEAR="year";

    private static final String DATABASE_NAME ="economyHandlerUserData.db";
    private static final int DATABASE_VERSION = 1;

    //query create table user
    private static final String DATABASE_CREATE_TABLE_USER = "CREATE TABLE "+ USER_TABLE_NAME +"("
            + USER_COLUMN_EMAIL +" TEXT NOT NULL PRIMARY KEY, "+ USER_COLUMN_PASSWORD +" TEXT NOT NULL, "
            + USER_COLUMN_FIRST_NAME +" TEXT NOT NULL, "+ USER_COLUMN_SURNAME +" TEXT NOT NULL );";

    //query create table expenditure
    private static final String DATABASE_CREATE_TABLE_EXPENDITURE=" CREATE TABLE "+EXPENDITURE_TABLE_NAME+"("+COLUMN_ID
            +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+COLUMN_USER_EMAIL+" TEXT NOT NULL, "
            +COLUMN_TITLE+" TEXT NOT NULL, "+COLUMN_CATEGORY+" TEXT, "+COLUMN_AMOUNT+ " REAL NOT NULL, "
            +COLUMN_DAY+" INTEGER, "+COLUMN_MONTH+" INTEGER, "+COLUMN_YEAR+" INTEGER, "
            +" FOREIGN KEY ("+COLUMN_USER_EMAIL+") REFERENCES "+USER_TABLE_NAME +"("+USER_COLUMN_EMAIL+"));";

    //query create table income
    private static final String DATABASE_CREATE_TABLE_INCOME=" CREATE TABLE "+INCOME_TABLE_NAME+"("+COLUMN_ID
            +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+COLUMN_USER_EMAIL+" TEXT NOT NULL, "
            +COLUMN_TITLE+" TEXT NOT NULL, "+COLUMN_CATEGORY+" TEXT, "+COLUMN_AMOUNT+ " REAL NOT NULL, "
            +COLUMN_DAY+" INTEGER, "+COLUMN_MONTH+" INTEGER, "+COLUMN_YEAR+" INTEGER, "
            +" FOREIGN KEY ("+COLUMN_USER_EMAIL+") REFERENCES "+USER_TABLE_NAME +"("+USER_COLUMN_EMAIL+"));";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_INCOME);
        sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_EXPENDITURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ INCOME_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ EXPENDITURE_TABLE_NAME);


        //TODO handle migration of data
        onCreate(sqLiteDatabase);
    }
}
