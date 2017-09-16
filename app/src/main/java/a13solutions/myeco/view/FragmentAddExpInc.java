package a13solutions.myeco.view;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import a13solutions.myeco.R;
import a13solutions.myeco.model.DataFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddExpInc extends Fragment implements FragmentMethods {

    public final String ARG_FRAME_NUMBER="frame_number";

    private EditText etTitle, etAmount;
    private Button btnAdd;
    private Spinner spinCategory;
    private TextView tvDate;

    private String title, amount, category, date;
    private DialogFragment datePicker = new DatePickerFragment();

    //Reuses UI for Expenditure and Income
    private int typeOfFragment;

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
        typeOfFragment = getArguments().getInt(ARG_FRAME_NUMBER);
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

        //change header and spinner based on if this fragment is expenditure or income
        switch (typeOfFragment){
            case 3:
                adapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.category_income, android.R.layout.simple_spinner_item);

                break;
            case 4:
                adapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.category_expenditure, android.R.layout.simple_spinner_item);
                break;
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategory.setAdapter(adapter);
    }

    public void setDate() {

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

                //check if this fragment is income or expenditure
                switch (typeOfFragment){
                    //income
                    case 3:
                        //TODO ADD to dbIncome
                        break;
                    //expenditure
                    case 4:
                        //TODO add to dbExpenditure
                        break;
                }
            }
        });

    }
    @Override
    public String getFrameNumberTag() {
        return ARG_FRAME_NUMBER;
    }

}
