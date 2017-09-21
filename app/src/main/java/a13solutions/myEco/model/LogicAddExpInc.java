package a13solutions.myEco.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.dbHelper.DBManager;

/**
 * Created by 13120dde on 2017-09-17.
 */

public class LogicAddExpInc {

    private MainActivity activity;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private String email;

    /**
     *
     * @param activity
     */
    public LogicAddExpInc(MainActivity activity) {
        this.activity=activity;
        sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.ECONOMYHANDLER_USER_DATA),Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        email = sharedPreferences.getString(activity.getString(R.string.USER_EMAIL),"");

    }

    public boolean addIncomeExpenditureToDb(String title, String category, String date, String amount, boolean addToIncomeTable) {
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
            chooseTableAndSend(title, category, date, amount, addToIncomeTable);

        }else{
            UtilityMethods.showNeutralDialog("Error",message,activity);
        }
        return isSuccess;
    }

    private void chooseTableAndSend(String title, String category, String date, String amount, boolean tableIncome) {

        double amountReal = Double.parseDouble(amount);

        if (tableIncome){
            new DBManager(activity).putIncome(email,title,category,date,amountReal);
        }else{
            String barcode = sharedPreferences.getString("bundle",null);
            if(barcode==null){
                new DBManager(activity).putExpenditureWithoutScan(email,title,category,date,amountReal);
            }else{
                new DBManager(activity).putExpenditureWithScan(email,title,category,date,amountReal,barcode);
                editor.remove("bundle");
                editor.commit();
            }


        }

    }


    public boolean addExpenditureWithScan(String result) {

        ReturnPacket resultFromDb = new DBManager(activity).getScanContent(email,result);

        if(resultFromDb!=null){
            new DBManager(activity).putExpenditureWithScan(email,resultFromDb.getTitle(),resultFromDb.getCategory(),
                    UtilityMethods.getCurrentDate(),resultFromDb.getAmount(),resultFromDb.getScanContent());
            Toast.makeText(activity, activity.getString(R.string.scanner_value)+resultFromDb.getTitle()+" - "+resultFromDb.getAmount() , Toast.LENGTH_LONG).show();

            return true;
        }else{

            editor.putString("bundle", result);
            editor.commit();
            return false;
        }
    }
}
