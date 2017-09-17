package a13solutions.myEco.model;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

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

    /**
     *
     * @param date : String
     * @return int[3] : int[0] = day, int[1] = month, int[2]=year
     */
    public static int[] parseDateFromString(String date){
        String[]dmy=date.split("-");
        Log.d("IN_ParseDate", "before: "+date);

        int[] dayMonthYear = new int[3];
        dayMonthYear[0] = Integer.parseInt(dmy[0]);
        dayMonthYear[1] = Integer.parseInt(dmy[1]);
        dayMonthYear[2] = Integer.parseInt(dmy[2]);

        Log.d("IN_ParseDateToString", "after: \n[0]:"+dayMonthYear[0]+" [1]:"+dayMonthYear[1]+" [2]:"+dayMonthYear[2]);

        return dayMonthYear;
    }

    /**
     *
     * @param dayMonthYear int[3] : int[0] = day, int[1] = month, int[2]=year
     * @return
     */
    public static String parseDateFromIntArray(int[] dayMonthYear){
        String date="";

        return date;
    }
}
