package a13solutions.myEco.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import java.util.Random;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.dbHelper.DBManager;

/**
 * Created by 13120dde on 2017-09-17.
 */

public class LogicAddExpInc {

    private MainActivity activity;
    private String fragmentTitle;

    /**
     *
     * @param activity
     * @param fragmentTitle
     */
    public LogicAddExpInc(MainActivity activity, String fragmentTitle) {
        this.activity=activity;
        this.fragmentTitle=fragmentTitle;

    }

    public boolean addToDb(String title, String category, String date, String amount) {
        String message="";
        boolean isSuccess = true;

        if(title.isEmpty()){
            message+="Title is empty.\n";
            isSuccess=false;
        }

        if(amount.isEmpty()){
            message+="Amount is empty";
            isSuccess=false;
        }
        if(isSuccess){
            chooseTableAndSend(title, category, date, amount);
        }else{
            UtilityMethods.showNeutralDialog("Error",message,activity);
        }
        return isSuccess;
    }

    private void chooseTableAndSend(String title, String category, String date, String amount) {

        Resources res = activity.getResources();
        SharedPreferences sp = activity.getSharedPreferences(res.getString(R.string.ECONOMYHANDLER_USER_DATA), Activity.MODE_PRIVATE);
        String email = sp.getString(res.getString(R.string.USER_EMAIL),null);
        double amountReal = Double.parseDouble(amount);

        if (fragmentTitle.equals(activity.getString(R.string.fragment_add_income))){
            new DBManager(activity).putIncome(email,title,category,date,amountReal);
        }
        if(fragmentTitle.equals(activity.getString(R.string.fragment_add_expenditure))){
            new DBManager(activity).putExpenditure(email,title,category,date,amountReal);
        }
    }

}
