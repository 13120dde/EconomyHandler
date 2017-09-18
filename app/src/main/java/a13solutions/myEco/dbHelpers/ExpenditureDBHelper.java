package a13solutions.myEco.dbHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 13120dde on 2017-09-17.
 */

public class ExpenditureDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="expenditures";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_USER_EMAIL="user_email";
    public static final String COLUMN_TITLE="title";
    public static final String COLUMN_CATEGORY="category";
    public static final String COLUMN_AMOUNT="amount";
    public static final String COLUMN_DAY="day";
    public static final String COLUMN_MONTH="month";
    public static final String COLUMN_YEAR="year";

    private static final String DATABASE_NAME="appUserExpenditure.db";
    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_CREATE=" CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID
            +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+COLUMN_USER_EMAIL+" TEXT NOT NULL, "
            +COLUMN_TITLE+" TEXT NOT NULL, "+COLUMN_CATEGORY+" TEXT, "+COLUMN_AMOUNT+ " REAL NOT NULL, "
            +COLUMN_DAY+" INTEGER, "+COLUMN_MONTH+" INTEGER, "+COLUMN_YEAR+" INTEGER );";


    public ExpenditureDBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //TODO handle migration of data
        onCreate(sqLiteDatabase);
    }
}
