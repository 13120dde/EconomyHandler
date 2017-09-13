package a13solutions.myeco;

import android.app.Fragment;

import java.util.List;

import a13solutions.myeco.fragment.CustomFragment;
import a13solutions.myeco.fragment.FragmentHome;
import a13solutions.myeco.fragment.FragmentLogin;
import a13solutions.myeco.fragment.FragmentRegister;
import a13solutions.myeco.fragment.FragmentTest;
import a13solutions.myeco.model.ItemSlideMenu;
import a13solutions.myeco.model.LogicRegister;
import a13solutions.myeco.model.ReturnPacket;

/**
 * Created by 13120dde on 2017-09-12.
 */

/**Controller class - should implement logic here.
 *
 * To add new fragments and populate the slide-menu with new items implement code in:
 * + replaceFragment(int position, boolean backstack) : void
 * + addItemsToSlidingList(List<ItemSlideMenu> listSliding) : List<ItemSlideMenu>
 */
public class Controller {

    private MainActivity mainActivity;

    public Controller(MainActivity mainActivity) {
        this.mainActivity=mainActivity;

    }


    /**
     *
     * @param position : int
     * @param backstack : boolean
     */
    public void replaceFragment(int position, boolean backstack) {

        Fragment fragment = null;

        //Add new Fragments to show here.
        switch (position) {
            case 0:
                fragment = new FragmentHome();
                break;
            case 1:
                fragment = new FragmentRegister();
                break;
            case 2:
                fragment = new FragmentLogin();
                break;
            case 3:
                fragment = new FragmentTest();
                break;
            default:
                fragment = new FragmentHome();
                break;
        }

        ((CustomFragment) fragment).setController(this);
        mainActivity.replaceFragment(fragment, true);

    }

    /**Populates the List-object passed in as arguments with new menu-items. Returns the populated
     * list.
     *
     * @param listSliding: List<ItemSlideMenu>
     * @return lisSliding: List<ItemSlideMenu>
     *
     */
    public List<ItemSlideMenu> addItemsToSlidingList(List<ItemSlideMenu> listSliding) {

        listSliding.add(new ItemSlideMenu(R.drawable.ic_home, "Home"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_user_register, "Register"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_login, "Login"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_settings, "Settings"));

        return listSliding;
    }


    public void registerAccount(String email, String password, String passwordRepeat,
                                String firstName, String surname) {

        String resultTitle="Success", resultInfo="";
        boolean allSuccessfull=true;

        LogicRegister reg = new LogicRegister();
        ReturnPacket result;

        result = reg.checkEmail(email);
        if(!result.isSuccess()){
            resultTitle="Error";
            resultInfo+=result.getMessage()+"\n";
            allSuccessfull=result.isSuccess();
        }
        result = reg.checkPassword(password,passwordRepeat);
        if(!result.isSuccess()){
            resultTitle="Error";
            resultInfo+=result.getMessage()+"\n";
            allSuccessfull=result.isSuccess();
        }

        if(allSuccessfull){
            resultInfo="Registration complete.\nProcede to login.";
        }

        mainActivity.showNeutralDialog(resultTitle,resultInfo);
        //Change to login frame
        if(allSuccessfull){
            replaceFragment(2,false);
        }

    }
}
