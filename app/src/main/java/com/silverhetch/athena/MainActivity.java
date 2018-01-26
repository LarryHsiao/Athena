package com.silverhetch.athena;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.silverhetch.athena.ui.about.AboutThisAppFragment;
import com.silverhetch.athena.ui.setting.SettingFragment;
import com.silverhetch.athena.ui.vocabularylist.VocabularyListFragment;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;
import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private final BroadcastReceiver connectionReceiver;
    private Snackbar connectionSnackBar;

    public MainActivity() {
        this.connectionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                invalidateConnectionIndicator();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.main_navigationDrawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_drawerOpen, R.string.app_drawerClose);
        drawerLayout.addDrawerListener(drawerToggle);

        NavigationView navigationView = findViewById(R.id.main_navigation);
        navigationView.setNavigationItemSelectedListener(this);

        connectionSnackBar = Snackbar.make(findViewById(R.id.main_root), R.string.app_noConnection, LENGTH_INDEFINITE);

        if (savedInstanceState == null) {
            launchListPage();
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(connectionReceiver, new IntentFilter(CONNECTIVITY_ACTION));
        invalidateConnectionIndicator();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectionReceiver);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_aboutThisApp:
                launchAboutPage();
                break;
            case R.id.navigation_vocabularyList:
                launchListPage();
                break;
            case R.id.navigation_setting:
                launchSettingPage();
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return false;
    }

    private void launchSettingPage() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragmentContainer, SettingFragment.newInstance());
        transaction.commit();
    }

    private void launchAboutPage() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragmentContainer, AboutThisAppFragment.newInstance());
        transaction.commit();
    }

    private void launchListPage() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragmentContainer, VocabularyListFragment.newInstance());
        fragmentTransaction.commit();
    }

    private boolean hasConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void invalidateConnectionIndicator() {
        if (hasConnection()) {
            connectionSnackBar.dismiss();
        } else {
            connectionSnackBar.show();
        }
    }
}
