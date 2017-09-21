package a13solutions.myEco.view;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    private EditText etTitleIncome, etAmountIncome;
    private Button btnAddIncome;
    private Spinner spinCategoryIncome;
    private TextView tvDate, tvInfo;
    private RadioGroup radioGroup;

    private String category;
    private DialogFragment datePicker = new DatePickerFragment();
    private boolean incomeOrExpenditure = true;


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
        setUpCategoryAdapter(incomeOrExpenditure);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logicAddExpInc = new LogicAddExpInc(((MainActivity)getActivity()));
    }

    private void instantiateComponents(View rootView) {

        etTitleIncome = rootView.findViewById(R.id.etTitleIncome);
        etAmountIncome = rootView.findViewById(R.id.etAmountIncome);
        spinCategoryIncome = rootView.findViewById(R.id.spinnerCategoryIncome);
        btnAddIncome = rootView.findViewById(R.id.btnAddIncome);
        tvDate = rootView.findViewById(R.id.tvDateIncome);
        radioGroup = rootView.findViewById(R.id.radioGroup);
        tvInfo = rootView.findViewById(R.id.tvInfoInAdd);

        //gets todays date
        DataFragment f = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        tvDate.setText(f.getDate());


    }

    public void setInfoText(String text){
        tvInfo.setText(text);
    }

    private void setUpCategoryAdapter(boolean incomeExpendiutre){
        ArrayAdapter<CharSequence> adapterIncome = null;

        if(incomeExpendiutre){
            adapterIncome = ArrayAdapter.createFromResource(getActivity(),
                    R.array.category_income, android.R.layout.simple_spinner_item);
        }else{
            adapterIncome = ArrayAdapter.createFromResource(getActivity(),
                    R.array.category_expenditure, android.R.layout.simple_spinner_item);
        }



        adapterIncome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategoryIncome.setAdapter(adapterIncome);
    }

    public void updateData() {

        DataFragment dataFragment = (DataFragment) getActivity().getFragmentManager().findFragmentByTag(DataFragment.DATA_TAG);
        tvDate.setText(dataFragment.getChosenDate());
    }

    private void addListeners() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton rb = radioGroup.findViewById(i);
                if(rb.getId()==R.id.radioIncome){
                    incomeOrExpenditure = true;
                }
                if (rb.getId()==R.id.radioExpenditure){
                    incomeOrExpenditure = false;
                }
                setUpCategoryAdapter(incomeOrExpenditure);
            }
        });

        //Listener for spinners
        spinCategoryIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                category = (String) spinCategoryIncome.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                category = (String) spinCategoryIncome.getItemAtPosition(0);
            }
        });





        datePicker.setShowsDialog(true);
        datePicker.setTargetFragment(this,0);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getActivity().getFragmentManager(),"Date picker");
            }
        });


        //Listener for buttons
        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setUpCategoryAdapter(incomeOrExpenditure);
                logicAddExpInc.addIncomeExpenditureToDb(etTitleIncome.getText().toString(), category, tvDate.getText().toString(), etAmountIncome.getText().toString(), incomeOrExpenditure);
                etAmountIncome.setText("");
                etTitleIncome.setText("");
                }
        });

    }



}
