package a13solutions.myeco;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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

    //Purple #8e44ad

    //Objects needed for the sliding-menu
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    //Declaration of other variables
    private Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Init components
        controller = new Controller(this);
        instantiateComponents();

        //populate the menu with a list containing ItemSlideMenu-objects.
        listSliding = controller.addItemsToSlidingList(listSliding);
        setupListView();
        instantiateActionBar();

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

        //Display icon to open / close sliding list
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle(listSliding.get(0).getTitle());

        //item-selected
        listViewSliding.setItemChecked(0, true);

        //Close menu
        drawerLayout.closeDrawer(listViewSliding);

        //Display fragment 1 when start
        controller.replaceFragment(0,true);

        //Handle on item click
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Set title
                setTitle(listSliding.get(i).getTitle());
                //item selected
                listViewSliding.setItemChecked(i,true);

                //Replace fragment
                controller.replaceFragment(i, true);
                //Close menu
                drawerLayout.closeDrawer(listViewSliding);

            }
        });
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


    /**Add new items to the list by instantiating a new ItemSlideMenu object, also dont forget to
     * add a new case in replaceFragment() method.
     *
     */
    /*private void addItemsToSlidingList() {
        listSliding.add(new ItemSlideMenu(R.drawable.ic_account_balance_black_48dp, "Home"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_assessment_black_48dp, "Register"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_settings_black_48dp, "Login"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_settings_black_48dp, "Test"));
    }*/

    /**The fragment passed in as argument in placed in the MainActivity's content_main frame.
     * The boolean value indicates if the previous Fragment should be in backstack.
     *
     * @param fragment : Fragment
     * @param backstack : boolean
     */
    public void replaceFragment(Fragment fragment, boolean backstack){
        if(fragment!=null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.main_content, fragment);
            if(backstack){
                ft.addToBackStack(null);
            }
            ft.commit();
        }
    }

    public void showNeutralDialog(String title, String message){
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
