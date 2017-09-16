package a13solutions.myeco;

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
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import a13solutions.myeco.adapter.SlidingMenuAdapter;
import a13solutions.myeco.model.DataFragment;
import a13solutions.myeco.view.FragmentHome;
import a13solutions.myeco.view.FragmentListExpInc;
import a13solutions.myeco.view.FragmentLogin;
import a13solutions.myeco.view.FragmentMethods;
import a13solutions.myeco.view.FragmentRegister;
import a13solutions.myeco.view.FragmentAddExpInc;
import a13solutions.myeco.model.ItemSlideMenu;

/**The skeleton code for an app that shows all it's UI components in MainActivity's frame by
 * chosing an item to show from the sliding-menu implemented in this project.
 *
 * This project follows MVC-pattern and all the logic should be implemented in the Controller
 * class.
 *
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

    //Datafragment
    DataFragment data;


    //Declaration of other variables


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //Init components
        instantiateComponents();

        //populate the menu with a list containing ItemSlideMenu-objects.
        initDataFragment();
        addItemsToSlidingList();
        setupListView();
        instantiateActionBar();

        if (savedInstanceState == null) {
            selectFragment(0);
        }
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

                selectFragment(position);


            }
        });
    }

    public void selectFragment(int position) {
        Fragment fragment = getNewFragment(position);
        Bundle args = new Bundle();
        args.putInt(((FragmentMethods)fragment).getFrameNumberTag(), position);

        fragment.setArguments(args);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.main_content,fragment).commit();

        setTitle(listSliding.get(position).getTitle());
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

    /**Rnstantiate and return a new fragment. The type of fragment returned is based on the int
     * passed in as argument.
     *
     * @param position : int
     *@return fragment : Fragment
     */
    public Fragment getNewFragment(int position) {

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
                fragment = new FragmentAddExpInc();
                break;
            case 4:
                fragment = new FragmentAddExpInc();
                break;
            case 5:
                fragment = new FragmentListExpInc();
                break;
            case 6:
                fragment = new FragmentListExpInc();
                break;
            default:
                fragment = new FragmentHome();
                break;
        }
        return fragment;
    }

    /**Populates the List-object passed in as arguments with new menu-items. Returns the populated
     * list.
     */
    public void addItemsToSlidingList() {

        listSliding.add(new ItemSlideMenu(R.drawable.ic_home, "Home"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_user_register, "Register"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_login, "Login"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_money, "Add income"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_expenditure, "Add expenditure"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_list_inc, "Incomes"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_list_exp, "Expenditures"));



    }

}
