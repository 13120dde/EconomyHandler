package a13solutions.myEco.view;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;

import a13solutions.myEco.R;
import a13solutions.myEco.adapter.ExpandableListViewAdapter;
import a13solutions.myEco.model.ChildInfo;
import a13solutions.myEco.model.DataFragment;
import a13solutions.myEco.model.ListItemInfo;
import a13solutions.myEco.model.UtlilityMethods;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListExpInc extends Fragment implements FragmentMethods {

    private String fragmentTitle;
    private boolean isExpanded;

    private TextView tvDateFrom, tvDateTo, tvBottom, tvSummary;
    private ImageButton btnExpandCollapse;
    private DialogFragment datePicker = new DatePickerFragment();


    //Expandable list declarations
    private ExpandableListViewAdapter listAdapter;
    private ExpandableListView listView;

    //Other structure for list
    private ArrayList<ListItemInfo> listItems = new ArrayList<>();
    DataFragment dataFragment;
    private double totalAmount=0;



    public FragmentListExpInc() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentTitle = getArguments().getString(getString(R.string.ARG_FRAGMENT_TITLE));
        dataFragment= (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        isExpanded=false;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_list_exp_inc, container, false);
        instantiateComponents(rootView);
        addListeners();
        loadData();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();

    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            listView.expandGroup(i);
            listAdapter.changeIcon(i);
        }
        isExpanded = true;
        btnExpandCollapse.setImageResource(R.drawable.ic_collapse_white);

    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            listView.collapseGroup(i);
        }
        isExpanded = false;
        btnExpandCollapse.setImageResource(R.drawable.ic_expand_white);
    }

    //load some initial data into out list
    private void loadData(){

        listItems.clear();
        listAdapter.notifyDataSetChanged();
        collapseAll();
        DataFragment data = (DataFragment) getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);

        if(fragmentTitle.equals(getString(R.string.fragment_incomes))){
            data.getIncomesFromDb();
            for(int i=0; i<dataFragment.getIncomesSize();i++){
                addProduct(dataFragment.getIncomeTitle(i), dataFragment.getIncomeCategory(i),
                dataFragment.getIncomeDate(i), dataFragment.getIncomeAmount(i),
                0);
            }
            tvSummary.setText(UtlilityMethods.roundTwoDecimals(dataFragment.getTotalIncomeAmount())+getString(R.string.tv_currency));
        }
        if(fragmentTitle.equals(getString(R.string.fragment_expenditures))){
            data.getExpendituresFromDb();
            for(int i =0; i<dataFragment.getExpendituresSize();i++){
                addProduct(dataFragment.getExpenditureTitle(i),dataFragment.getExpenditureCategory(i),
                dataFragment.getExpenditureDate(i), dataFragment.getExpenditureAmount(i),
                0);
            }
            tvSummary.setText(UtlilityMethods.roundTwoDecimals(dataFragment.getTotalExpenditureAmount())+getString(R.string.tv_currency));
        }
    }

    private int addProduct(String title, String date, String category, double amounmt, int itemId){

        int groupPosition = listItems.size()+1;
        listItems.add(new ListItemInfo(title, new ChildInfo(date,0), new ChildInfo(category,1), amounmt, itemId));

        return groupPosition;
    }

    private void addListeners() {


        ButtonListener listener = new ButtonListener();
        btnExpandCollapse.setOnClickListener(listener);
        tvDateFrom.setOnClickListener(listener);
        tvDateTo.setOnClickListener(listener);

    }

    private void instantiateComponents(View rootView) {

        listView = (ExpandableListView) rootView.findViewById(R.id.lvEpandableList);
        // create the adapter by passing your ArrayList data
        listAdapter = new ExpandableListViewAdapter(getActivity(), listItems);
        // attach the adapter to the expandable list view
        listView.setAdapter(listAdapter);

        datePicker.setShowsDialog(true);
        datePicker.setTargetFragment(this,0);


        tvDateFrom = rootView.findViewById(R.id.tvDateFrom);
        tvDateTo = rootView.findViewById(R.id.tvDateTo);
        tvBottom= rootView.findViewById(R.id.tvBottom);
        tvSummary= rootView.findViewById(R.id.tvSummary);

        btnExpandCollapse = rootView.findViewById(R.id.btnExpandCollapseAll);

        tvDateFrom.setText(dataFragment.getDateFrom());
        tvDateTo.setText(dataFragment.getDateTo());

        if(fragmentTitle.equals(getString(R.string.fragment_incomes))){
            tvBottom.setText(R.string.tv_total_income);
        }
        if(fragmentTitle.equals(getString(R.string.fragment_expenditures))){
            tvBottom.setText(R.string.tv_total_expenditure);
        }


    }

    public String getFragmentTitle() {
        return fragmentTitle;
    }

    @Override
    public void updateData() {

        tvDateFrom.setText(dataFragment.getDateFrom());
        tvDateTo.setText(dataFragment.getDateTo());
        loadData();

    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnExpandCollapseAll:
                    if(!isExpanded){
                        expandAll();
                    }else{
                        collapseAll();
                    }
                    break;
                case R.id.tvDateFrom:
                    datePicker.show(getActivity().getFragmentManager(),"Date from");
                    ((DatePickerFragment)datePicker).setDateFrom(true);
                    break;
                case R.id.tvDateTo:
                    datePicker.show(getActivity().getFragmentManager(),"Date to");
                    ((DatePickerFragment)datePicker).setDateFrom(false);
                    break;
            }
        }
    }
}
