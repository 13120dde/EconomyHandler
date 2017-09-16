package a13solutions.myeco.view;


import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import a13solutions.myeco.R;
import a13solutions.myeco.model.DataFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListExpInc extends Fragment implements FragmentMethods {


    public final String ARG_FRAME_NUMBER="frame_number";

    private TextView tvDateFrom, tvDateTo;
    private DialogFragment datePicker = new DatePickerFragment();

    public FragmentListExpInc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_list_exp_inc, container, false);
        instantiateComponents(rootView);
        addListeners();

        return rootView;
    }

    private void addListeners() {

        ///listener for tvDate

        tvDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getActivity().getFragmentManager(),"Date from");
                ((DatePickerFragment)datePicker).setDateFrom(true);
            }
        });

        tvDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getActivity().getFragmentManager(),"Date to");
                ((DatePickerFragment)datePicker).setDateFrom(false);

            }
        });
    }

    private void instantiateComponents(View rootView) {

        datePicker.setShowsDialog(true);
        datePicker.setTargetFragment(this,0);


        tvDateFrom = rootView.findViewById(R.id.tvDateFrom);
        tvDateTo = rootView.findViewById(R.id.tvDateTo);

        DataFragment f = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        tvDateFrom.setText(f.getDateFrom());
        tvDateTo.setText(f.getDateTo());
    }



    @Override
    public String getFrameNumberTag() {
        return ARG_FRAME_NUMBER;
    }

    @Override
    public void setDate() {
        DataFragment dataFragment = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);

        tvDateFrom.setText(dataFragment.getDateFrom());
        tvDateTo.setText(dataFragment.getDateTo());

    }
}
