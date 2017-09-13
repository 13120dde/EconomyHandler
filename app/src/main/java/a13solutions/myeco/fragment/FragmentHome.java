package a13solutions.myeco.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a13solutions.myeco.Controller;
import a13solutions.myeco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements CustomFragment {

    private Controller controller;
    private int fragmentId;

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
    public void setController(Controller controller) {
        this.controller=controller;
    }

    @Override
    public void setFragmentId(int id) {
        this.fragmentId=id;
    }

    @Override
    public int getFragmentId() {
        return fragmentId;
    }
}
