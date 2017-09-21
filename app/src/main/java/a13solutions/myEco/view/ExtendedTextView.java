package a13solutions.myEco.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import a13solutions.myEco.R;
import a13solutions.myEco.model.DataFragment;
import a13solutions.myEco.model.UtilityMethods;

/**
 * Created by 13120dde on 2017-09-21.
 */

public class ExtendedTextView extends android.support.v7.widget.AppCompatTextView {


    private String date="";

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ExtendedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTextColor(context.getColor(R.color.colorBlue));
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        date=UtilityMethods.getCurrentDate();
        setText(date);
        addListener();
    }

    private void addListener() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new DateChooser();
            }
        });
    }

    public void setDate(String date){
        this.date=date;
        setText(date);
    }


    public String getDate() {
        return date;
    }

    @SuppressLint("ValidFragment")
    public class DateChooser extends DialogFragment implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), R.style.datepicker, this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            setDate(UtilityMethods.formatDate(year,++month,day));
        }

    }
}
