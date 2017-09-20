package a13solutions.myEco;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import a13solutions.myEco.adapter.SlidingMenuAdapter;
import a13solutions.myEco.model.DataFragment;
import a13solutions.myEco.view.FragmentHome;
import a13solutions.myEco.view.FragmentListExpInc;
import a13solutions.myEco.view.FragmentLogin;
import a13solutions.myEco.view.FragmentRegister;
import a13solutions.myEco.view.FragmentAddExpInc;
import a13solutions.myEco.model.ItemSlideMenu;

/**
 * This project follows 1 Activity - multiple Fragments paradigm which is a realisation of MVC-pattern.
 *The main Activity swaps Fragments in and out depending on the selected item in menu drawer.
 * Created by: 13120dde on 2017-09-12
 *
 */
public class MainActivity extends AppCompatActivity {

    //Objects needed for the sliding-menu
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SharedPreferences sp;

    //Datafragment
    DataFragment data;


    //Declaration of other variables


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //Init components
        instantiateComponents();

        sp = this.getSharedPreferences(getString(R.string.ECONOMYHANDLER_USER_DATA), this.MODE_PRIVATE);

        //first time app running
        if(sp==null){
            initOnFirstTimeRunningComponents();
        }

        //populate the menu with a list containing ItemSlideMenu-objects.
        initDataFragment();


        addItemsToSlidingList();
        setupListView();
        instantiateActionBar();

        if (savedInstanceState == null) {
            selectFragment(0, listSliding.get(0).getTitle());
        }
    }

    public void showHomeFragment() {
        selectFragment(0, listSliding.get(0).getTitle());
    }

    private void initOnFirstTimeRunningComponents() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(getString(R.string.USER_IS_LOGGEDIN), false);
        editor.commit();
    }

    private void initDataFragment() {
        FragmentManager fm = getFragmentManager();
        data = (DataFragment) fm.findFragmentByTag(DataFragment.DATA_TAG);
        if(data==null){
            data = new DataFragment();
            fm.beginTransaction().add(data, DataFragment.DATA_TAG).commit();
        }
    }

    private void instantiateActionBar() {

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.drawer_opened,R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    private void setupListView() {

        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(listSliding.get(0).getTitle());
        listViewSliding.setItemChecked(0, true);
        drawerLayout.closeDrawer(listViewSliding);

        //Handle on item click
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                if(listSliding.get(position).getTitle().equals(getString(R.string.fragment_logout))){
                    logout();
                    selectFragment(0, listSliding.get(0).getTitle());
                }else{
                    selectFragment(position, listSliding.get(position).getTitle());
                }


            }
        });
    }

    private void logout() {

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(getString(R.string.USER_IS_LOGGEDIN), false);
        editor.remove(getString(R.string.USER_FIRST_NAME));
        editor.remove(getString(R.string.USER_SURNAME));
        editor.remove(getString(R.string.USER_EMAIL));
        editor.remove(getString(R.string.USER_PASSWORD));

        editor.commit();
        addItemsToSlidingList();
    }

    public void selectFragment(int position, String fragmentTitle) {
        hideKeyboard();
        Fragment fragment = getNewFragment(fragmentTitle, position);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.main_content,fragment).commit();

        setTitle(fragmentTitle);
        listViewSliding.setItemChecked(position,true);
        drawerLayout.closeDrawer(listViewSliding);
    }

    private void instantiateComponents() {
        listViewSliding =(ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    /**Instantiate and return a new fragment. The type of fragment returned is based on the int
     * passed in as argument.
     *
     * @param fragmentTitle : int
     *@return fragment : Fragment
     */
    public Fragment getNewFragment(String fragmentTitle, int position) {

        Fragment fragment = null;
        Bundle args = new Bundle();

        if(fragmentTitle.equals(getString(R.string.fragment_home))){
            fragment = new FragmentHome();
        }if(fragmentTitle.equals(getString(R.string.fragment_add_income))){
            fragment = new FragmentAddExpInc();
            args.putString(getString(R.string.ARG_FRAGMENT_TITLE),getString(R.string.fragment_add_income));
        }if(fragmentTitle.equals(getString(R.string.fragment_add_expenditure))){
            fragment = new FragmentAddExpInc();
            args.putString(getString(R.string.ARG_FRAGMENT_TITLE),getString(R.string.fragment_add_expenditure));
        }if(fragmentTitle.equals(getString(R.string.fragment_incomes))){
            fragment = new FragmentListExpInc();
            args.putString(getString(R.string.ARG_FRAGMENT_TITLE),getString(R.string.fragment_incomes));
        }if(fragmentTitle.equals(getString(R.string.fragment_expenditures))){
            fragment = new FragmentListExpInc();
            args.putString(getString(R.string.ARG_FRAGMENT_TITLE),getString(R.string.fragment_expenditures));

        }if(fragmentTitle.equals(getString(R.string.fragment_register))){
            fragment = new FragmentRegister();
        }if(fragmentTitle.equals(getString(R.string.fragment_login))){
            fragment = new FragmentLogin();
        }if(fragmentTitle.equals(getString(R.string.fragment_logout))){
            fragment = new FragmentHome();
        }
        args.putInt(getString(R.string.ARG_FRAGMENT_NUMBER), position);
        fragment.setArguments(args);
        return fragment;
    }

    /**Populates the List-object passed in as arguments with new menu-items.
     */
    public void addItemsToSlidingList() {

        boolean loggedin = sp.getBoolean(getString(R.string.USER_IS_LOGGEDIN), false);

        if(!listSliding.isEmpty()){
            listSliding.clear();
        }

        listSliding.add(new ItemSlideMenu(R.drawable.ic_home, getString(R.string.fragment_home)));
        if(loggedin){
            listSliding.add(new ItemSlideMenu(R.drawable.ic_money, getString(R.string.fragment_add_income)));
            listSliding.add(new ItemSlideMenu(R.drawable.ic_expenditure, getString(R.string.fragment_add_expenditure)));
            listSliding.add(new ItemSlideMenu(R.drawable.ic_list_inc, getString(R.string.fragment_incomes)));
            listSliding.add(new ItemSlideMenu(R.drawable.ic_list_exp, getString(R.string.fragment_expenditures)));
            listSliding.add (new ItemSlideMenu(R.drawable.ic_logout, getString(R.string.fragment_logout)));

        }else{
            listSliding.add(new ItemSlideMenu(R.drawable.ic_user_register, getString(R.string.fragment_register)));
            listSliding.add(new ItemSlideMenu(R.drawable.ic_login, getString(R.string.fragment_login)));
        }
    }

    public void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        View v = getCurrentFocus();
        if(v==null){
            return;
        }
        inputManager.hideSoftInputFromWindow(v.getWindowToken(),0);

    }


}
