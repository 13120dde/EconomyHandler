package a13solutions.myEco.view;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import a13solutions.myEco.MainActivity;
import a13solutions.myEco.R;
import a13solutions.myEco.model.DataFragment;
import a13solutions.myEco.model.LogicAddExpInc;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddExpInc extends Fragment implements FragmentMethods{

    private EditText etTitle, etAmount;
    private Button btnAdd;
    private Spinner spinCategory;
    private TextView tvDate;

    private String title, amount, category, date;
    private DialogFragment datePicker = new DatePickerFragment();

    //Reuses UI for Expenditure and Income
    private int typeOfFragment;
    private String fragmentTitle;
    private LogicAddExpInc logicAddExpInc;

    public FragmentAddExpInc() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_exp_inc, container, false);
        instantiateComponents(rootView);
        addListeners();

        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeOfFragment = getArguments().getInt(getString(R.string.ARG_FRAGMENT_NUMBER));
        fragmentTitle = getArguments().getString(getString(R.string.ARG_FRAGMENT_TITLE));
        logicAddExpInc = new LogicAddExpInc(((MainActivity)getActivity()), fragmentTitle);
    }

    private void instantiateComponents(View rootView) {

        etTitle = rootView.findViewById(R.id.et_title);
        etAmount = rootView.findViewById(R.id.et_amount);
        spinCategory = rootView.findViewById(R.id.spin_category);
        btnAdd = rootView.findViewById(R.id.btnAddExpInc);
        tvDate = rootView.findViewById(R.id.tvDate);

        //gets todays date
        DataFragment f = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        tvDate.setText(f.getDate());


        ArrayAdapter<CharSequence> adapter = null;

        //change spinner based on if this fragment is expenditure or income
        if (fragmentTitle.equals(getString(R.string.fragment_add_income))){
                adapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.category_income, android.R.layout.simple_spinner_item);
        }
        if(fragmentTitle.equals(getString(R.string.fragment_add_expenditure))){
            adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.category_expenditure, android.R.layout.simple_spinner_item);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategory.setAdapter(adapter);
    }

    public void updateData() {

        DataFragment dataFragment = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        tvDate.setText(dataFragment.getChosenDate());
    }

    private void addListeners() {

        //Listener for spinner
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                category = (String) spinCategory.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                category = (String) spinCategory.getItemAtPosition(0);
            }
        });

        ///listener for tvDate
        datePicker.setShowsDialog(true);
        datePicker.setTargetFragment(this,0);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getActivity().getFragmentManager(),"Date picker");
            }
        });

        //Listener for button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = etTitle.getText().toString();
                amount = etAmount.getText().toString();
                date = tvDate.getText().toString();

                if(logicAddExpInc.addToDb(title, category, date, amount)){
                    etAmount.setText("");
                    etTitle.setText("");
                    ((MainActivity) getActivity()).hideKeyboard();
                };

                //Just to fill db with some data
                //logicAddExpInc.fillDbWithIncomesExpenditures();

            }
        });

    }


}
