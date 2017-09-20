package a13solutions.myEco.view;


import android.app.Activity;
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
import a13solutions.myEco.model.LogicHome;
import a13solutions.myEco.model.UtilityMethods;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements FragmentMethods {

    private TextView tvWelcome, tvWelcomeMessage;
    private RelativeLayout containerGraph;
    private PieChart pieChart;
    private LogicHome logicHome;


    public FragmentHome() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        instantiateComponents(rootView);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void instantiateComponents(View rootView) {

        tvWelcome = rootView.findViewById(R.id.tvWelcome);
        tvWelcomeMessage = rootView.findViewById(R.id.tvWelcomeMessage);
        containerGraph= rootView.findViewById(R.id.containerGraph);

        SharedPreferences sp = getActivity().getSharedPreferences(getString(R.string.ECONOMYHANDLER_USER_DATA), Activity.MODE_PRIVATE);

        if(true==sp.getBoolean(getString(R.string.USER_IS_LOGGEDIN),false)){
            String email = sp.getString(getString(R.string.USER_EMAIL),"");
           tvWelcome.setText(getString(R.string.tv_welcome)+" "+sp.getString(getString(R.string.USER_FIRST_NAME),""));
           tvWelcomeMessage.setText(email);
            containerGraph.setVisibility(View.VISIBLE);

            pieChart = rootView.findViewById(R.id.chart);
            logicHome= new  LogicHome((MainActivity)getActivity(), email, pieChart );
            logicHome.fillPie(UtilityMethods.getCurrentDate());

        }else{
            tvWelcome.setText(getString(R.string.tv_welcome)+"!");
            tvWelcomeMessage.setText(getString(R.string.tv_welcome_message_not_signedin));
            containerGraph.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void updateData() {

    }
}
