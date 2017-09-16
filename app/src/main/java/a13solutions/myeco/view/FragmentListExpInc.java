package a13solutions.myeco.view;


import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import a13solutions.myeco.R;
import a13solutions.myeco.adapter.CustomAdapter;
import a13solutions.myeco.model.ChildInfo;
import a13solutions.myeco.model.DataFragment;
import a13solutions.myeco.model.GroupInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListExpInc extends Fragment implements FragmentMethods {


    public final String ARG_FRAME_NUMBER="frame_number";
    private int typeOfFragment;

    private TextView tvDateFrom, tvDateTo, tvBottom, tvSummary;
    private DialogFragment datePicker = new DatePickerFragment();

    //Expandable list declarations
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;



    public FragmentListExpInc() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeOfFragment = getArguments().getInt(ARG_FRAME_NUMBER);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_list_exp_inc, container, false);
        instantiateComponents(rootView);
        addListeners();

        //EXOANDABLE LIST
        loadData();
        return rootView;
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData(){

        addProduct("Android","ListView");
        addProduct("Android","ExpandableListView");
        addProduct("Android","GridView");

        addProduct("Java","PolyMorphism");
        addProduct("Java","Collections");

    }
    //here we maintain our products in various departments
    private int addProduct(String department, String product){

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }

    private void addListeners() {

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo =  headerInfo.getProductList().get(childPosition);
                //display it or do something with it
               /* Toast.makeText(getContext(), " Clicked on :: " + headerInfo.getName()
                        + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();*/
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                /*Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_LONG).show();*/

                return false;
            }
        });

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

        simpleExpandableListView = (ExpandableListView) rootView.findViewById(R.id.lvEpandableList);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(getActivity(), deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        datePicker.setShowsDialog(true);
        datePicker.setTargetFragment(this,0);


        tvDateFrom = rootView.findViewById(R.id.tvDateFrom);
        tvDateTo = rootView.findViewById(R.id.tvDateTo);
        tvBottom= rootView.findViewById(R.id.tvBottom);
        tvSummary= rootView.findViewById(R.id.tvSummary);



        DataFragment f = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        tvDateFrom.setText(f.getDateFrom());
        tvDateTo.setText(f.getDateTo());

        switch (typeOfFragment){
            case 5:
                tvBottom.setText("Total incomes:");
                break;
            case 6:
                tvBottom.setText("Total expenditures:");
                break;

        }
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
