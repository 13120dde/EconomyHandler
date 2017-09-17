package a13solutions.myEco.view;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import a13solutions.myEco.R;
import a13solutions.myEco.adapter.ExpandableListViewAdapter;
import a13solutions.myEco.model.ChildInfo;
import a13solutions.myEco.model.DataFragment;
import a13solutions.myEco.model.ListItemInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListExpInc extends Fragment implements FragmentMethods {

    private int typeOfFragment;
    private String fragmentTitle;
    private boolean isExpanded;

    private TextView tvDateFrom, tvDateTo, tvBottom, tvSummary;
    private ImageButton btnExpandCollapse;
    private DialogFragment datePicker = new DatePickerFragment();

    //Expandable list declarations
    private LinkedHashMap<String, ListItemInfo> subjects = new LinkedHashMap<String, ListItemInfo>();
    private ArrayList<ListItemInfo> deptList = new ArrayList<ListItemInfo>();
    private ExpandableListViewAdapter listAdapter;
    private ExpandableListView listView;

    //Other structure for list
    private ArrayList<ListItemInfo> listItems = new ArrayList<>();



    public FragmentListExpInc() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeOfFragment = getArguments().getInt(getString(R.string.ARG_FRAGMENT_NUMBER));
        fragmentTitle = getArguments().getString(getString(R.string.ARG_FRAGMENT_TITLE));
        isExpanded=false;
        //EXOANDABLE LIST
        loadData();
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

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            listView.expandGroup(i);
            listAdapter.changeIcon(i);
        }

    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            listView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData(){

        if(fragmentTitle.equals(getString(R.string.fragment_incomes))){
            //TODO fetch data from db set on chosen dates
        }
        if(fragmentTitle.equals(getString(R.string.fragment_incomes))){
            //TODO fetch data from db set on chosen dates
        }

        //TODO remove after data is loaded from db
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);
        addProduct("Android","ListView", "11-02-2015", 200,0);
        addProduct("Android","Food", "11-02-2015",134.50, 0);
        addProduct("Java","Leisure", "11-02-2015", 44,0);
        addProduct("Java","Other", "11-02-2015", 32.99,0);

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



        DataFragment f = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        tvDateFrom.setText(f.getDateFrom());
        tvDateTo.setText(f.getDateTo());

        if(fragmentTitle.equals(getString(R.string.fragment_incomes))){
            tvBottom.setText(R.string.tv_total_income);
            //TODO calculate total incomes
        }
        if(fragmentTitle.equals(getString(R.string.fragment_expenditures))){
            tvBottom.setText(R.string.tv_total_expenditure);
            //TODO calculate total expenditures
        }
    }

    @Override
    public void setDate() {
        DataFragment dataFragment = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);

        tvDateFrom.setText(dataFragment.getDateFrom());
        tvDateTo.setText(dataFragment.getDateTo());

    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnExpandCollapseAll:
                    if(!isExpanded){
                        expandAll();
                        isExpanded=true;
                        btnExpandCollapse.setImageResource(R.drawable.ic_collapse_white);
                    }else{
                        collapseAll();
                        isExpanded=false;
                        btnExpandCollapse.setImageResource(R.drawable.ic_expand_white);
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
