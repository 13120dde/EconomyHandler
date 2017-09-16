package a13solutions.myeco.model;

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
        date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        dateFrom = date;
        dateTo = addOneMonth(date);


    }

    private String addOneMonth(String date) {
        String[] vals = date.split("-");
        int nextMonth = Integer.parseInt(vals[1])+1;
        if(nextMonth>12){
            int nextYear = Integer.parseInt(vals[2]);
            vals[2] = Integer.toString(++nextYear);
        }
        if(nextMonth<10){
            vals[1]="0"+nextMonth;
        }else{
            vals[1]= String.valueOf(nextMonth);
        }

        return vals[0]+"-"+vals[1]+"-"+vals[2];
    }

    public void setChosenDate(int year, int month, int day) {
        chosenDate=formatDate(year,month,day);
    }


    public String getDate(int dayOfWeek, int month, int year) {
        return formatDate(year,month,dayOfWeek);
    }

    public void setDateFrom(int year, int month, int day) {
        dateFrom = formatDate(year,month,day);
    }

    public void setDateTo(int year, int month, int day) {
        dateTo = formatDate(year, month, day);
    }

    private String formatDate(int year, int month, int day){
        String d=Integer.toString(day), m=Integer.toString(month);

        if(day<10){
            d="0"+d;
        }if(month<10){
            m="0"+m;
        }
        return d+"-"+m+"-"+year;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }
}
