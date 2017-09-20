package a13solutions.myEco.model;

import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.dbHelper.DBManager;

/**
 * Created by 13120dde on 2017-09-14.
 */

public class UtilityMethods {

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

    public static String roundTwoDecimals(double amount) {
        DecimalFormat df = new DecimalFormat("#.##");
        String s = df.format(amount);
        return s;
    }

    public static int[] parseDateFromString(String date) {
        String[] s = date.split("-");
        int[] yyyymmdd = {Integer.parseInt(s[0]), Integer.parseInt(s[1]),Integer.parseInt(s[2])};
        return yyyymmdd;
    }


    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static void printArray(ArrayList<ExpIncItem> item) {
        for (int i = 0; i<item.size();i++){
            Log.d("IN_PRINT_ARR", item.toString());
        }
    }


    public static double calculateTotalAmount(ArrayList<ExpIncItem> list) {
        double totalAmount=0;
        for (ExpIncItem i : list) {
            totalAmount+=i.getAmount();
        }
        return totalAmount;
    }


    public static void populateEmptyTable(String email,MainActivity activity) {
        String[] categoryIncome = {"Salary", "Other"};
        String[] categoryExp = {"Accomodation","Food","Leisure","Travel", "Other"};
        String[] incomeSalaryTitle ={"Work 1", "Work 2", "Work 1 bonus", "Work 2 bonus", "App1 sale","App2 sale","Lotto","Gift"};
        String[] otherTitle ={"Swich","Ebay","Lotto","Some random event", "Lotto","Gift","Presents"};
        String[] expFoodTitle ={"Takeaway","Thaifood","Dinner","Pizza","Lunch","Breakfast"};
        String[] expLeisureTitle={"Games","Cinema","Shopping","Toys","Soccer","Books"};
        String[] expAccomodationTitle={"Rent","Phone","Bills","Car maintenance"};
        String[] expTravelTitle ={"Gas","Buss ticket","Taxi","Uber"};
        String date="";

        //DATE AND AMOUNT TOO
        for(int i = 0; i<1000; i++){
            boolean selectTable = true;
            String category="", title="";
            int[] dmy = getRandomDate();
            date = UtilityMethods.formatDate(dmy[0],dmy[1],dmy[2]);
            double amount =0;

            if(selectTable){
                category = getRandomElement(categoryIncome);
                if(category.equals("Salary")){
                    title = getRandomElement(incomeSalaryTitle);
                    amount = getRandomAmount(1000);
                }if(category.equals("Other")){
                    title = getRandomElement(otherTitle);
                    amount = getRandomAmount(100);
                }

                new DBManager(activity).putIncome(email,title,category,date,amount);
                selectTable=!selectTable;
            }if(!selectTable){

                category = getRandomElement(categoryExp);
                if(category.equals(categoryExp[0])){
                    title=getRandomElement(expAccomodationTitle);
                    amount = getRandomAmount(1000);
                }if(category.equals(categoryExp[1])){
                    title=getRandomElement(expFoodTitle);
                    amount = getRandomAmount(100);
                }if(category.equals(categoryExp[2])){
                    title=getRandomElement(expLeisureTitle);
                    amount = getRandomAmount(200);
                }if(category.equals(categoryExp[3])){
                    title=getRandomElement(expTravelTitle);
                    amount = getRandomAmount(500);
                }if(category.equals(categoryExp[4])){
                    title = getRandomElement(otherTitle);
                    amount = getRandomAmount(100);
                }

                new DBManager(activity).putExpenditure(email,title,category,date,amount);
                selectTable=!selectTable;
            }
        }
    }

    private static double getRandomAmount(int i) {

        return (new Random().nextDouble()+(new Random().nextInt(9)+1))*i;

    }

    private static int[] getRandomDate() {
        int dmy[] = new int[3];
        dmy[2] = new Random().nextInt(31)+1;
        dmy[1] = new Random().nextInt(12)+1;
        dmy[0] = 2017;
        return dmy;
    }

    private static  String getRandomElement(String[] arg) {
        return arg[new Random().nextInt(arg.length)];
    }

}
