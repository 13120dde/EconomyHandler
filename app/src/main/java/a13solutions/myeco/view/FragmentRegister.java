package a13solutions.myeco.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import a13solutions.myeco.MainActivity;
import a13solutions.myeco.R;
import a13solutions.myeco.model.LogicRegister;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegister extends Fragment implements FragmentMethods{

    private final String ARG_FRAME_NUMBER="frame_number";

    private EditText etEmail, etPassword, etPasswordRepeat, etFirstName, etSurname;
    private Button btnRegister;

    public FragmentRegister() {
        // Required empty public constructor
    }

    @Override
    public String getFrameNumberTag() {
        return ARG_FRAME_NUMBER;
    }

    @Override
    public void setDate() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_register,container,false);

        instantiateComponents(rootView);
        addListener();
        return rootView;
    }

    private void addListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean registered = new LogicRegister((MainActivity) getActivity()).registerAccount(etEmail.getText().toString(),etPassword.getText().toString(),
                        etPasswordRepeat.getText().toString(),etFirstName.getText().toString(), etSurname.getText().toString()) ;

               if(!registered){
                   etEmail.setText("");
                   etPassword.setText("");
                   etPasswordRepeat.setText("");
                   etFirstName.setText("");
                   etSurname.setText("");
               }else{
                   //proceed to loginwindow
                   ((MainActivity)getActivity()).selectFragment(2);
               }
            }
        });
    }

    private void instantiateComponents(View rootView) {

        etEmail = rootView.findViewById(R.id.et_email);
        etPassword= rootView.findViewById(R.id.et_password);
        etPasswordRepeat = rootView.findViewById(R.id.et_password_repeat);
        etFirstName = rootView.findViewById(R.id.et_first_name);
        etSurname= rootView.findViewById(R.id.et_surname);
        btnRegister= rootView.findViewById(R.id.btn_register);
    }

}
