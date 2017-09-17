package a13solutions.myEco.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

import a13solutions.myEco.R;
import a13solutions.myEco.model.DataFragment;

/**
 * Created by 13120dde on 2017-09-15.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    private boolean dateFrom;

    public void setDateFrom(boolean dateFrom) {
        this.dateFrom=dateFrom;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), R.style.datepicker,this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        DataFragment data = (DataFragment) getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        Fragment targetFragment =  getTargetFragment();

        if(targetFragment instanceof FragmentAddExpInc){
            data.setChosenDate(year,++month,day);
        }
        if(targetFragment instanceof FragmentListExpInc){
            if(dateFrom){
                data.setDateFrom(year,++month,day);
            }else{
                data.setDateTo(year,++month,day);
            }
        }

        ((FragmentMethods)targetFragment).setDate();
    }
}
