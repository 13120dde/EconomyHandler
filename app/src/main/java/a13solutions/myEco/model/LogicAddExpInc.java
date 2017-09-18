package a13solutions.myEco.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.Random;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.dbHelpers.DBManager;

/**
 * Created by 13120dde on 2017-09-17.
 */

public class LogicAddExpInc {

    private MainActivity activity;
    private String fragmentTitle;

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
            UtlilityMethods.showNeutralDialog("Error",message,activity);
        }
        return isSuccess;
    }

    private void chooseTableAndSend(String title, String category, String date, String amount) {

        int[] dayMonthYear = UtlilityMethods.parseDateFromString(date);
        Resources res = activity.getResources();
        SharedPreferences sp = activity.getSharedPreferences(res.getString(R.string.ECONOMYHANDLER_USER_DATA), Activity.MODE_PRIVATE);
        String email = sp.getString(res.getString(R.string.USER_EMAIL),null);
        double amountReal = Double.parseDouble(amount);

        if (fragmentTitle.equals(activity.getString(R.string.fragment_add_income))){
            new DBManager(activity).putIncome(email,title,category,dayMonthYear,amountReal);
        }
        if(fragmentTitle.equals(activity.getString(R.string.fragment_add_expenditure))){
            new DBManager(activity).putExpenditure(email,title,category,dayMonthYear,amountReal);
        }
    }

    public void fillEmptyTable() {
        Resources res = activity.getResources();
        SharedPreferences sp = activity.getSharedPreferences(res.getString(R.string.ECONOMYHANDLER_USER_DATA), Activity.MODE_PRIVATE);
        String email = sp.getString(res.getString(R.string.USER_EMAIL),null);
        String[] categoryIncome = {"Salary", "Other"};
        String[] categoryExp = {"Accomodation","Food","Leisure","Travel", "Other"};
        String[] incomeSalaryTitle ={"Work 1", "Work 2", "Work 1 bonus", "Work 2 bonus", "App1 sale","App2 sale","Lotto","Gift"};
        String[] otherTitle ={"Swich","Ebay","Lotto","Some random event", "Lotto","Gift","Presents"};
        String[] expFoodTitle ={"Takeaway","Thaifood","Dinner","Pizza","Lunch","Breakfast"};
        String[] expLeisureTitle={"Games","Cinema","Shopping","Toys","Soccer","Books"};
        String[] expAccomodationTitle={"Rent","Phone","Bills","Car maintenance"};
        String[] expTravelTitle ={"Gas","Buss ticket","Taxi","Uber"};

        //DATE AND AMOUNT TOO
        for(int i = 0; i<2; i++){
            String category="", title="";
            int[] dmy = getRandomDate();
            double amount =0;

            if(fragmentTitle.equals(res.getString(R.string.fragment_add_income))){
                category = getRandomElement(categoryIncome);
                if(category.equals("Salary")){
                    title = getRandomElement(incomeSalaryTitle);
                    amount = getRandomAmount(1000);
                }if(category.equals("Other")){
                    title = getRandomElement(otherTitle);
                    amount = getRandomAmount(100);
                }

                new DBManager(activity).putIncome(email,title,category,dmy,amount);
                Log.d("IN_GenInc", email+": "+title.toUpperCase()+" <"+category+"> date:"+dmy[0]+"-"+dmy[1]+"-"+dmy[2]+", amount:"+amount);
            }if(fragmentTitle.equals(res.getString(R.string.fragment_add_expenditure))){

                category = getRandomElement(categoryExp);
                if(category.equals(categoryExp[0])){
                    title=getRandomElement(expAccomodationTitle);
                    amount = getRandomAmount(1000);
                }if(category.equals(categoryExp[1])){
                    title=getRandomElement(expFoodTitle);
                    amount = getRandomAmount(100);
                }if(category.equals(categoryExp[2])){
                    title=getRandomElement(expLeisureTitle);
                    amount = getRandomAmount(2000);
                }if(category.equals(categoryExp[3])){
                    title=getRandomElement(expTravelTitle);
                    amount = getRandomAmount(500);
                }if(category.equals(categoryExp[4])){
                    title = getRandomElement(otherTitle);
                    amount = getRandomAmount(100);
                }

                new DBManager(activity).putExpenditure(email,title,category,dmy,amount);
                Log.d("IN_GenExp", email+": "+title.toUpperCase()+" <"+category+"> date:"+dmy[0]+"-"+dmy[1]+"-"+dmy[2]+", amount:"+amount);
            }
        }
    }

    private double getRandomAmount(int i) {

        double r =(new Random().nextDouble()+(new Random().nextInt(9)+1))*i;
        DecimalFormat df = new DecimalFormat("#.##");
        df.format(r);

        return Double.parseDouble(df.format(r));
    }

    private int[] getRandomDate() {
        int dmy[] = new int[3];
        dmy[0] = new Random().nextInt(31)+1;
        dmy[1] = new Random().nextInt(12)+1;
        dmy[2] = new Random().nextInt(18)+2000;
        return dmy;
    }

    private String getRandomElement(String[] arg) {
        return arg[new Random().nextInt(arg.length)];
    }
}
