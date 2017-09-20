package a13solutions.myEco.model;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.comparator.Sort;
import a13solutions.myEco.dbHelper.DBManager;

import static android.content.ContentValues.TAG;

/**
 * Created by 13120dde on 2017-09-19.
 */

public class LogicHome {

    private MainActivity activity;
    private String email;

    private PieChart pieChart;


    public LogicHome(MainActivity activity, String email, PieChart pieChart) {
        this.activity=activity;
        this.email=email;
        this.pieChart=pieChart;
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    public void fillPie(String date){
        String dateFrom = getDateFrom(date);
        String dateTo = getDateTo(date);
        float accomodation=0, food=0, other=0, travel=0,leisure=0;

        ArrayList<ExpIncItem> expenditures = new DBManager(activity).getExpenditures(email,dateFrom,dateTo);

        for (ExpIncItem item: expenditures) {
            if(item.getCategory().equals("Accomodation")){
              accomodation+= (float)item.getAmount();
            }
            if(item.getCategory().equals("Food")){
              food+= (float)item.getAmount();
            }
            if(item.getCategory().equals("Travel")){
              travel+= (float)item.getAmount();
            }
            if(item.getCategory().equals("Leisure")){
              leisure+= (float)item.getAmount();
            }
        }

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(accomodation,"Accomodation"));
        entries.add(new PieEntry(food,"Food"));
        entries.add(new PieEntry(leisure,"Leisure"));
        entries.add(new PieEntry(travel,"Travel"));
        entries.add(new PieEntry(other,"Other"));
        PieEntry e = new PieEntry(food,"Food");


        PieDataSet set = new PieDataSet(entries,"");
        setDesign(set);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setDesign(PieDataSet set) {
        Resources res = activity.getResources();
        int[] colors = {res.getColor(R.color.colorBlue, null),res.getColor(R.color.colorPurple, null),res.getColor(R.color.colorGreen, null),res.getColor(R.color.colorRed, null),res.getColor(R.color.colorYellow, null)};
        set.setColors(colors);
        set.setSliceSpace(2f);
        set.setFormSize(10f);
        set.setValueTextSize(15f);
        IValueFormatter f = new PercentFormatter();
        set.setValueFormatter(f);
        set.setValueTextColor(res.getColor(R.color.colorWhite,null));
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(48f);
        pieChart.setTransparentCircleRadius(53f);
        pieChart.setTransparentCircleColor(res.getColor(R.color.colorPrimary,null));
        pieChart.setTransparentCircleAlpha(70);
        pieChart.setCenterTextColor(res.getColor(R.color.colorBlue));
        pieChart.setCenterTextTypeface(Typeface.SERIF);
        pieChart.setCenterTextSizePixels(70f);
        pieChart.setEntryLabelTypeface(Typeface.SERIF);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterText(res.getString(R.string.monthly_expenditures));

    }

    /***
     *
     * @param currentDate : String formatted as yyyy-mm-dd
     * @return : returns date yyyy-mm-01
     */
    private String getDateFrom(String currentDate) {
        String[] arr = currentDate.split("-");
        arr[2]="01";
        return arr[0]+"-"+arr[1]+"-"+arr[2];
    }

    /***
     *
     * @param currentDate : String formatted as yyyy-mm-dd
     * @return : returns date yyyy-mm-31
     */
    private String getDateTo(String currentDate) {
        String[] arr = currentDate.split("-");
        arr[2]="31";
        return arr[0]+"-"+arr[1]+"-"+arr[2];
    }
}
