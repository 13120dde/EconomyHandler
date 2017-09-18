package a13solutions.myEco.model;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by 13120dde on 2017-09-15.
 */

public class DataFragment extends Fragment{

    private final String LOG_TAG="IN_DATA";
    public static final String DATA_TAG="data";

    public String date, chosenDate, dateFrom, dateTo;

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
}
