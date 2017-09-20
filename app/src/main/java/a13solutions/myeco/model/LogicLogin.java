package a13solutions.myEco.model;

import android.content.SharedPreferences;

import java.util.HashMap;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.dbHelper.DBManager;
import a13solutions.myEco.dbHelper.DBHelper;

/**
 * Created by 13120dde on 2017-09-14.
 */

public class LogicLogin {

    private MainActivity activity;
    private SharedPreferences sp;



    public LogicLogin(MainActivity activity) {
        this.activity=activity;
        sp = activity.getSharedPreferences(activity.getString(R.string.ECONOMYHANDLER_USER_DATA), activity.MODE_PRIVATE);

    }

    public boolean login(String email, String password) {

        DBManager dbManager = new DBManager(activity);
        ReturnPacket res = dbManager.login(email);

        if(res.isSuccess()){
            HashMap<String, String> vals = res.getVals();

            if(vals.get(DBHelper.USER_COLUMN_PASSWORD).equals(password)){

                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(activity.getString(R.string.USER_IS_LOGGEDIN), true);
                editor.putString(activity.getString(R.string.USER_FIRST_NAME), vals.get(DBHelper.USER_COLUMN_FIRST_NAME));
                editor.putString(activity.getString(R.string.USER_SURNAME), vals.get(DBHelper.USER_COLUMN_SURNAME));
                editor.putString(activity.getString(R.string.USER_EMAIL), email);
                editor.putString(activity.getString(R.string.USER_PASSWORD),password);

                editor.commit();
                activity.addItemsToSlidingList();
                activity.showHomeFragment();
                return true;
            }else{
                UtilityMethods.showNeutralDialog("Error", "Passwords don't match.", activity);
            }

        }else{
            UtilityMethods.showNeutralDialog("Error", res.getMessage(), activity);
        }


        return false;
    }

}
