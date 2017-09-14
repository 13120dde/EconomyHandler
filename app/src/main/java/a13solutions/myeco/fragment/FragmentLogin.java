package a13solutions.myeco.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a13solutions.myeco.MainActivity;
import a13solutions.myeco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment implements FragmentMethods {

    public final String ARG_FRAME_NUMBER="frame_number";

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

        return rootView;
    }

}
