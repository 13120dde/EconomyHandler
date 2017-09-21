package a13solutions.myEco.view;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.model.DataFragment;
import a13solutions.myEco.model.LogicHome;
import a13solutions.myEco.model.UtilityMethods;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements FragmentMethods {

    private TextView tvWelcome, tvWelcomeMessage, tvChangeMonth;;
    private RelativeLayout containerGraph;
    private PieChart pieChart;
    private LogicHome logicHome;
    private String date;
    private DialogFragment datePicker = new DatePickerFragment();

    public FragmentHome() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        date = UtilityMethods.getCurrentDate();
        instantiateComponents(rootView);
        addListeners();
        return rootView;
    }

    private void addListeners() {

        datePicker.setShowsDialog(true);
        datePicker.setTargetFragment(this,0);

        tvChangeMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getActivity().getFragmentManager(),"Date picker");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void instantiateComponents(View rootView) {

        tvWelcome = rootView.findViewById(R.id.tvWelcome);
        tvWelcomeMessage = rootView.findViewById(R.id.tvWelcomeMessage);
        containerGraph= rootView.findViewById(R.id.containerGraph);
        tvChangeMonth= rootView.findViewById(R.id.tvChangeMonth);


        SharedPreferences sp = getActivity().getSharedPreferences(getString(R.string.ECONOMYHANDLER_USER_DATA), Activity.MODE_PRIVATE);

        if(true==sp.getBoolean(getString(R.string.USER_IS_LOGGEDIN),false)){
            String email = sp.getString(getString(R.string.USER_EMAIL),"");
           tvWelcome.setText(getString(R.string.tv_welcome)+" "+sp.getString(getString(R.string.USER_FIRST_NAME),""));
           tvWelcomeMessage.setText(email);
            tvChangeMonth.setText(getString(R.string.monthly_expenditures)+"\n"+date);

           containerGraph.setVisibility(View.VISIBLE);

            pieChart = rootView.findViewById(R.id.chart);
            logicHome= new  LogicHome((MainActivity)getActivity(), email, pieChart );
            logicHome.fillPie(date);

        }else{
            tvWelcome.setText(getString(R.string.tv_welcome)+"!");
            tvWelcomeMessage.setText(getString(R.string.tv_welcome_message_not_signedin));
            containerGraph.setVisibility(View.INVISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void updateData() {
        DataFragment dataFragment = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        tvChangeMonth.setText(getString(R.string.monthly_expenditures)+"\n"+dataFragment.getChosenDate());
        logicHome.fillPie(dataFragment.getChosenDate());
    }

}
