package a13solutions.myeco.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import a13solutions.myeco.Controller;
import a13solutions.myeco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegister extends Fragment implements CustomFragment {


    private Controller controller;
    private int fragmentId;
    private String email, password, passwordRepeat, firstName, surname;

    private EditText etEmail, etPassword, etPasswordRepeat, etFirstName, etSurname;
    private Button btnRegister;

    public FragmentRegister() {
        // Required empty public constructor
    }

    @Override
    public void setFragmentId(int id) {
        this.fragmentId=id;
    }

    @Override
    public int getFragmentId() {
        return fragmentId;
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
                controller.registerAccount(etEmail.getText().toString(),etPassword.getText().toString(),
                        etPasswordRepeat.getText().toString(),etFirstName.getText().toString(), etSurname.getText().toString());
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

    @Override
    public void setController(Controller controller) {
        this.controller=controller;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        etEmail.onSaveInstanceState(new Bundle(){

        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
