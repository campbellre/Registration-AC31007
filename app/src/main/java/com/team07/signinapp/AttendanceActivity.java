package com.team07.signinapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AttendanceActivity extends AppCompatActivity {

    private DrawerLayout drawer_menu_layout;
    private String username;
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        receiveUserData();
        setupToolbar();
        setupDrawer();
    }

    private void receiveUserData(){
        // Get data passed to this activity from LoginScreenActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getParcelable("User");
            //userType = (Login.UserType)extras.get("UserType");
            username = user.getUsername();
        }
    }

    private void setupToolbar(){
        // Initialises tool bar with menu button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Attendance");
        }
    }

    private void setupDrawer(){
        // Fetches the layout for the drawer
        // Set in layout->drawer_layout
        drawer_menu_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Gets the navigation view component and accesses the associated header
        // Then sets the title to the currently signed in username
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navigationDrawerHeader = navigationView.getHeaderView(0);
        TextView drawerHeaderTitle = (TextView)navigationDrawerHeader.findViewById(R.id.drawer_header_title);
        drawerHeaderTitle.setText(username + " (" +
                ((user.isStaff()) ? "Staff" : "Student")
                + ")");

        // Add onClick event to drawer Schedule button
        navigationView.getMenu().findItem(R.id.navigation_schedule).setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // TODO: Remove user login data if stored in future
                        Intent intent = new Intent(getApplicationContext(), LandingScreenActivity.class);
                        intent.putExtra("User", user);
                        startActivity(intent);
                        return true;
                    }
                }
        );

        // Add onClick event to drawer logout button
        navigationView.getMenu().findItem(R.id.navigation_logout).setOnMenuItemClickListener(
            new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // TODO: Remove user login data if stored in future
                    Intent intent = new Intent(getApplicationContext(), LoginScreenActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                }
            }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                drawer_menu_layout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
