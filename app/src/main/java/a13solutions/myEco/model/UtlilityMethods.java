package a13solutions.myEco.model;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.text.DecimalFormat;

import a13solutions.myEco.MainActivity;

/**
 * Created by 13120dde on 2017-09-14.
 */

public class UtlilityMethods {

    /**Shows a neutral Alert dialog with title  and message passed in as argument. Need to pass in
     * the Activity on which the Dialog will be shown.
     *
     * @param title : String
     * @param message : String
     * @param activity : MainActivity
     */
    public static void showNeutralDialog(String title, String message, MainActivity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    public static String formatDate(int year, int month, int day){
        String d=Integer.toString(day), m=Integer.toString(month);

        if(day<10){
            d="0"+d;
        }if(month<10){
            m="0"+m;
        }
        return year+"-"+m+"-"+d;
    }

    public static String addOneMonth(String date){
        String[] vals = date.split("-");

        int nextMonth = Integer.parseInt(vals[1])+1;
        int nextYear = Integer.parseInt(vals[0]);
        int day = Integer.parseInt(vals[2]);
        if(nextMonth>12){
            nextYear++;
            nextMonth =1;
        }
        return formatDate(nextYear,nextMonth,day);
    }

    public static String round(double amount) {
        DecimalFormat df = new DecimalFormat("#.##");
        String s = df.format(amount);
        return s;
    }
}
