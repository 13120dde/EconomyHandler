package a13solutions.myEco.model;

import android.content.SharedPreferences;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;

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

        //TODO implement check if user in db, also dialog if incorrect

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(activity.getString(R.string.USER_IS_LOGGEDIN), true);
        editor.commit();
        activity.addItemsToSlidingList();

        return true;
    }

}
