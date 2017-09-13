package a13solutions.myeco.fragment;

import a13solutions.myeco.Controller;

/**
 * Created by 13120dde on 2017-09-13.
 */
public interface CustomFragment {

    /**Sets the Controller object passed in as argument.
     *
     * @param controller : Controller
     */
    public void setController(Controller controller);

    /**Sets the integer passed in as argument to this frament's id. The id value is used by
     * MainActivity to retain the current fragment to show when changing screen-orientation.
     *
     * @param id : int
     */
    public void setFragmentId(int id);

    /**Returns the id of this Fragment-object. The id is set when Controller creates a new Fragment
     * in replaceFragment(int i, boolean backstack) method.
     *
     * @return id : int
     */
    public int getFragmentId();
}
