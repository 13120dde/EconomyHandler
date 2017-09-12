package a13solutions.myeco;

import android.app.Fragment;

import java.util.List;

import a13solutions.myeco.fragment.FragmentHome;
import a13solutions.myeco.fragment.FragmentLogin;
import a13solutions.myeco.fragment.FragmentRegister;
import a13solutions.myeco.fragment.FragmentTest;
import a13solutions.myeco.model.ItemSlideMenu;

/**
 * Created by 13120dde on 2017-09-12.
 */

/**Controller class - should implement logic here.
 *
 * To add new fragments and populate the slide-menu with new items implement code in:
 * + replaceFragment(int position, boolean backstack) : void
 * + addItemsToSlidingList(List<ItemSlideMenu> listSliding) : List<ItemSlideMenu>
 */
class Controller {

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
}
