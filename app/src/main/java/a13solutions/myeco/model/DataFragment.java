package a13solutions.myEco.model;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.comparator.Sort;
import a13solutions.myEco.dbHelper.DBManager;


/**
 * Created by 13120dde on 2017-09-15.
 */

public class DataFragment extends Fragment{

    public enum DataType{
        EXPENDITURE,
        INCOME;
    }
    public enum SortingCategory{
        CATEGORY,
        DATE_ASC,
        DATE_DESC,
        AMOUNT_ASC,
        AMOUNT_DESC,
        ADDED_ASC,
        ADDED_DESC;

    }

    private final String LOG_TAG="IN_DATA";
    public static final String DATA_TAG="data";
    MainActivity activity;

    private String date, chosenDate, dateFrom, dateTo, email;
    private double totalExpenditureAmount, totalIncomeAmount;
    private ArrayList<ExpIncItem> listIncome, listExpenditure;

    private DataType dataType;
    private SortingCategory sortingCategory;

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

    public void setSortingCategory(SortingCategory sortingCategory) {
        this.sortingCategory=sortingCategory;
    }
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
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
        date = UtilityMethods.getCurrentDate();
        dateFrom = date;
        dateTo = UtilityMethods.addOneMonth(date);
        sortingCategory=SortingCategory.DATE_DESC;

        //Load some initial data from db
        activity = (MainActivity) getActivity();
        Resources res = activity.getResources();
        SharedPreferences sp = activity.getSharedPreferences(activity.getString(R.string.ECONOMYHANDLER_USER_DATA),
                activity.MODE_PRIVATE);
        email = sp.getString(res.getString(R.string.USER_EMAIL), "");
        getExpendituresFromDb( );
        getIncomesFromDb();
    }

    public void getIncomesFromDb(){
        listIncome= new DBManager(activity).getIncomes(email,dateFrom,dateTo);
        totalIncomeAmount = UtilityMethods.calculateTotalAmount(listIncome);

        dataType=DataType.INCOME;
        sortData();

    }

    public void getExpendituresFromDb(){
        listExpenditure = new DBManager(activity).getExpenditures(email,dateFrom,dateTo);
            totalExpenditureAmount = UtilityMethods.calculateTotalAmount(listExpenditure);
        dataType=DataType.EXPENDITURE;
        sortData();
    }

    public void setChosenDate(int year, int month, int day) {
        chosenDate= UtilityMethods.formatDate(year,month,day);
    }

    public void setDateFrom(int year, int month, int day) {
        dateFrom = UtilityMethods.formatDate(year,month,day);
    }

    public void setDateTo(int year, int month, int day) {
        dateTo = UtilityMethods.formatDate(year, month, day);
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

    public void sortData() {

        if(dataType==DataType.EXPENDITURE){
            switch (sortingCategory) {
                case DATE_DESC:
                    listExpenditure = Sort.dateDescending(listExpenditure);
                    break;
                case DATE_ASC:
                    listExpenditure = Sort.dateAscending(listExpenditure);
                    break;
                case AMOUNT_DESC:
                    listExpenditure = Sort.amountDescending(listExpenditure);
                    break;
                case AMOUNT_ASC:
                    listExpenditure = Sort.amountAscending(listExpenditure);
                    break;
                case CATEGORY:
                    listExpenditure = Sort.category(listExpenditure);;
                    break;
            }
        }
        if(dataType==DataType.INCOME){
            switch (sortingCategory) {
                case DATE_DESC:
                    listIncome = Sort.dateDescending(listIncome);
                    break;
                case DATE_ASC:
                    listIncome = Sort.dateAscending(listIncome);
                    break;
                case AMOUNT_DESC:
                    listIncome= Sort.amountDescending(listIncome);
                    break;
                case AMOUNT_ASC:
                    listIncome = Sort.amountAscending(listIncome);
                    break;
                case CATEGORY:
                    listIncome = Sort.category(listIncome);
                    break;
            }
        }
    }


            }
