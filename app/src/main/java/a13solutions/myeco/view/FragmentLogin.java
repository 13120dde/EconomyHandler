package a13solutions.myeco.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import a13solutions.myeco.MainActivity;
import a13solutions.myeco.R;
import a13solutions.myeco.model.DialogManager;
import a13solutions.myeco.model.LogicLogin;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment implements FragmentMethods {

    public final String ARG_FRAME_NUMBER="frame_number";

    private EditText etEmail, etPassword;
    private TextView tvForgotPassword;
    private Button btnLogin;

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public String getFrameNumberTag() {
        return ARG_FRAME_NUMBER;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,container,false);
        instantiateComponents(rootView);
        addListener();
        return rootView;
    }

    private void addListener() {
        LoginListener listener = new LoginListener();
        btnLogin.setOnClickListener(listener);
        tvForgotPassword.setOnClickListener(listener);
    }

    private void instantiateComponents(View rootView) {
        etEmail = rootView.findViewById(R.id.et_email_login);
        etPassword = rootView.findViewById(R.id.et_password_login);
        btnLogin = rootView.findViewById(R.id.btn_login);
        tvForgotPassword = rootView.findViewById(R.id.tv_forgot_password);
    }

    private class LoginListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_login:
                    boolean loggedIn = new LogicLogin(((MainActivity)getActivity())).login(etEmail.getText().toString(), etPassword.getText().toString());
                    if(!loggedIn){
                        etEmail.setText("");
                        etPassword.setText("");
                    }else{
                        //proceed to homefragment and set some data
                    }
                    break;
                case R.id.tv_forgot_password:
                    DialogManager.showNeutralDialog("TODO","implement reset password", (MainActivity) getActivity());
                    break;
                default:
                    break;
            }
        }
    }

}
