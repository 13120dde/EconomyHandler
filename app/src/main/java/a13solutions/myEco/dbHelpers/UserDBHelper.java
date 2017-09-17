package a13solutions.myEco.dbHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 13120dde on 2017-09-17.
 */

public class UserDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="users";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_SURNAME = "surname";

    private static final String DATABASE_NAME ="economyhandler.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "+TABLE_NAME+"("
            +COLUMN_EMAIL+" text not null primary key, "+COLUMN_PASSWORD+" text not null, "
            +COLUMN_FIRST_NAME+" text not null, "+COLUMN_SURNAME+" text not null );";


    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
