package com.itechgenie.apps.geniewpmatrimony;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmQRConfig;
import com.itechgenie.apps.geniewpmatrimony.loaders.ProfileActivity;
import com.itechgenie.apps.geniewpmatrimony.loaders.SearchUserActivity;
import com.itechgenie.apps.geniewpmatrimony.utilities.GwpmBusinessEntity;
import com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants;
import com.itechgenie.apps.geniewpmatrimony.utilities.ITGDbManager;
import com.itechgenie.apps.geniewpmatrimony.utilities.ITGUtility;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GwpmConstants {

    private ITGDbManager dbManager = null;

    private static final String LOGGER_NAME = "MainActivity" ;

    private static final int MENU_PERSONAL_PROFILE = Menu.FIRST;
    private static final int MENU_SEARCH = Menu.FIRST + 1;
    private static final int MENU_MESSAGES = Menu.FIRST + 2;
    private static final int MENU_GALLERY = Menu.FIRST + 3;
    private static final int MENU_SETTINGS = Menu.FIRST + 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Log.d("MainActivity", "Started the main Activity, Loading config !!! ");

        Menu navMenus = navigationView.getMenu() ;

        dbManager = new ITGDbManager(this);
        dbManager.open();

        String configJson = dbManager.fetchByKeyName(GWPM_GLOBAL_CONFIG_JSON);

        Log.d("MainActivity", "Obtained Config: " + configJson);

        if (configJson == null) {
            Log.d("MainActivity", "No config found, Loading config screen: ");
            Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
            startActivity(intent);
        } else {

            TextView tv = (TextView) findViewById(R.id.configValueTempId);
            TextView tvms = (TextView) findViewById(R.id.welcomeMsgId);

            tvms.setVisibility(View.GONE);

            try {
                GwpmQRConfig gwpmQRConfig = (GwpmQRConfig) ITGUtility.jsonToObject(configJson, GwpmQRConfig.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tv.setText(android.text.Html.fromHtml(gwpmQRConfig.getHtmlString(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    tv.setText(android.text.Html.fromHtml(gwpmQRConfig.getHtmlString()));
                }
                GwpmBusinessEntity.setGwpmQRConfig(gwpmQRConfig);
            } catch (Exception e) {
                Log.d("MainActivity", "Exception in reading value from DB: " + e.getMessage(), e) ;
                tv.setText(e.getMessage());
            }

            Log.d(LOGGER_NAME, "Adding Dynamic Menu") ;

            navMenus.clear();
            navMenus.add(0, MENU_PERSONAL_PROFILE, Menu.NONE, R.string.menu_my_profile).setIcon(R.drawable.ic_menu_manage) ;
            navMenus.add(0, MENU_GALLERY, Menu.NONE, R.string.menu_gallery).setIcon(R.drawable.ic_menu_gallery) ;
            navMenus.add(0, MENU_MESSAGES, Menu.NONE, R.string.menu_messages).setIcon(R.drawable.ic_menu_send) ;
            navMenus.add(0, MENU_SEARCH, Menu.NONE, R.string.menu_search).setIcon(R.drawable.ic_menu_search) ;
            navMenus.add(0, MENU_SETTINGS, Menu.NONE, R.string.menu_settings).setIcon(R.drawable.ic_menu_settings) ;
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            loadActivity(MENU_SETTINGS) ;
        }

        if (id == R.id.action_exit) {
            // return true;
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int itemId = item.getItemId() ;
        switch (itemId) {
            case MENU_PERSONAL_PROFILE: loadMyProfile() ; break;
            case MENU_SEARCH: loadSearchProfile(); break;
            case MENU_GALLERY: loadActivity(itemId); break;
            case MENU_MESSAGES: loadActivity(itemId); break;
            case MENU_SETTINGS: loadActivity(itemId); break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadActivity (int itemId) {

        Toast.makeText(this, "Loading page: " + itemId,Toast.LENGTH_SHORT).show();

        if (itemId == MENU_SETTINGS) {
            Intent intent = new Intent(MainActivity.this, ConfigConfirmActivity.class);
            startActivity(intent);
        }

    }

    public void onClickClearConfigs(View view) {

        Log.d(LOGGER_NAME, "Delete Config Button Clicked: " + view.getId());

        clearConfigs() ;

    }

    public void clearConfigs() {
        int i = dbManager.deleteByKey(GWPM_GLOBAL_CONFIG_JSON);
        Log.d(LOGGER_NAME, "Delete key config value: " + i ) ;

        Toast.makeText(this,"Deleted Config !",Toast.LENGTH_LONG).show();

        Log.d(LOGGER_NAME, "Forwarding to Config Activity: ");
        Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
        startActivity(intent);

    }

    public void loadMyProfile() {
        Toast.makeText(this, "Loading my profile page : ",Toast.LENGTH_SHORT).show();
        // GwpmBusinessEntity.object().getMyProfile() ;

        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);

    }

    public void loadSearchProfile() {
      //  Toast.makeText(this, "Loading search profile page : ",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, SearchUserActivity.class);
        startActivity(intent);

    }


}
