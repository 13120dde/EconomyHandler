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
 * + placeFragmentInActivity(int position, boolean backstack) : void
 * + addItemsToSlidingList(List<ItemSlideMenu> listSliding) : List<ItemSlideMenu>
 */
public class Controller {

    private MainActivity mainActivity;

    public Controller(MainActivity mainActivity) {
        this.mainActivity=mainActivity;

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


    }
}
