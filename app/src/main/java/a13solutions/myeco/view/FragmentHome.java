package a13solutions.myeco.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a13solutions.myeco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements FragmentMethods {

    public final String ARG_FRAME_NUMBER="frame_number";

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        return rootView;
    }

    @Override
    public String getFrameNumberTag() {
        return ARG_FRAME_NUMBER;
    }
}
