package a13solutions.myEco.model;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.dbHelpers.DBManager;


/**
 * Created by 13120dde on 2017-09-15.
 */

public class DataFragment extends Fragment{

    private final String LOG_TAG="IN_DATA";
    public static final String DATA_TAG="data";
    MainActivity activity;

    private String date, chosenDate, dateFrom, dateTo;
    private double totalExpenditureAmount, totalIncomeAmount;
    private ArrayList<ExpIncItem> listIncome, listExpenditure;

    public ArrayList<ExpIncItem> getIncomesArray() {
        return listIncome;
    }

    public void setIncomesArray(ArrayList<ExpIncItem> incomes) {
        this.listIncome = incomes;
    }

    public ArrayList<ExpIncItem> getExpendituresArray() {
        return listExpenditure;
    }

    public void setExpendituresArray(ArrayList<ExpIncItem> expenditures) {
        this.listExpenditure= expenditures;
    }


    public double getTotalExpenditureAmount() {
        return totalExpenditureAmount;
    }

    public void setTotalExpenditureAmount(double totalExpenditureAmount) {
        this.totalExpenditureAmount = totalExpenditureAmount;
    }

    public double getTotalIncomeAmount() {
        return totalIncomeAmount;
    }

    public void setTotalIncomeAmount(double totalIncomeAmount) {
        this.totalIncomeAmount = totalIncomeAmount;
    }

    public String getDate() {
        return date;
    }

    public String getChosenDate(){
        return chosenDate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d(LOG_TAG,"datafragment created");
        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateFrom = date;
        dateTo = UtlilityMethods.addOneMonth(date);

        //Load some initial data from db
        activity = (MainActivity) getActivity();
        Resources res = activity.getResources();
        SharedPreferences sp = activity.getSharedPreferences(activity.getString(R.string.ECONOMYHANDLER_USER_DATA),
                activity.MODE_PRIVATE);
        String email = sp.getString(res.getString(R.string.USER_EMAIL), "");
        getExpendituresFromDb(email,dateFrom,dateTo);
        getIncomesFromDb(email,dateFrom,dateTo);

        //printArray(listIncome);
        printArray(listExpenditure);
    }

    public void getIncomesFromDb(String email, String dateFrom, String dateTo){
        listIncome = new ArrayList<>();
        listIncome= new DBManager(activity).getIncomes(email,dateFrom,dateTo,listIncome);
        ExpIncItem totalAmount = listIncome.remove(listIncome.size()-1);
        if(totalAmount!=null){
            totalIncomeAmount = totalAmount.getAmount();
        }

    }

    public void getExpendituresFromDb(String email, String dateFrom, String dateTo){
        listIncome = new ArrayList<ExpIncItem>();
        listExpenditure = new DBManager(activity).getExpenditures(email,dateFrom,dateTo,listExpenditure);
        ExpIncItem totalAmount = listIncome.remove(listIncome.size()-1);
        if(totalAmount!=null){
            totalIncomeAmount = totalAmount.getAmount();
        }
    }

    private void printArray(ArrayList<ExpIncItem> listExpenditure) {
        for (int i = 0; i<listExpenditure.size();i++){
            Log.d("IN_PRINT_ARR", listExpenditure.get(i).getTitle()+" | "+listExpenditure.get(i).getCategory()+" | "
            +listExpenditure.get(i).getDate()+" | "+listExpenditure.get(i).getAmount()+" | "
            +listExpenditure.get(i).getKeyInDb()+" | ");
        }
    }

    public void setChosenDate(int year, int month, int day) {
        chosenDate= UtlilityMethods.formatDate(year,month,day);
    }

    public void setDateFrom(int year, int month, int day) {
        dateFrom = UtlilityMethods.formatDate(year,month,day);
    }

    public void setDateTo(int year, int month, int day) {
        dateTo = UtlilityMethods.formatDate(year, month, day);
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public int getIncomesSize(){
        Log.d("IN_DATAG", "getIncomes: "+listIncome.size());
        return listIncome.size();
    }

    public String getIncomeTitle(int i) {
        return listIncome.get(i).getTitle();
    }

    public String getIncomeCategory(int i) {
        return listIncome.get(i).getCategory();
    }

    public String getIncomeDate(int i) {
        return listIncome.get(i).getDate();
    }

    public double getIncomeAmount(int i) {
        return listIncome.get(i).getAmount();
    }

    public int getIncomeIndex(int i) {
        return listIncome.get(i).getIndex();
    }

    public int getExpendituresSize() {
        Log.d("IN_DATAG", "getExpendituresSize: "+listExpenditure.size());
        return listExpenditure.size();
    }

    public String getExpenditureTitle(int i) {
        return listExpenditure.get(i).getTitle();
    }

    public String getExpenditureCategory(int i) {
        return listExpenditure.get(i).getCategory();
    }

    public String getExpenditureDate(int i) {
        return listExpenditure.get(i).getDate();
    }

    public double getExpenditureAmount(int i) {
        return listExpenditure.get(i).getAmount();
    }

    public int getExpenditureIndex(int i) {
        return listExpenditure.get(i).getIndex();
    }

}
