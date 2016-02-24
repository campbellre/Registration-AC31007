package com.team07.signinapp;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LandingScreenActivity extends AppCompatActivity {


    private DrawerLayout drawer_menu_layout;
    private RecyclerView scheduleView;
    private RecyclerView.Adapter scheduleAdapter;
    private RecyclerView.LayoutManager scheduleLayout;

    //current list of lessons to be used for testing.
    //Later implement fetch from database
    List<Lesson> lessons;

    private void initializeData(){
        lessons = new ArrayList<>();

        lessons.add(new Lesson("Name1", "Place1", "Time1"));
        lessons.add(new Lesson("Name2", "Place2", "Time3"));
        lessons.add(new Lesson("name3","Place2","Time3"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);

        initializeData();

        // Get data passed to this activity from LoginScreenActivity
        String username = "";
        Login.UserType userType = Login.UserType.None;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            userType = (Login.UserType)extras.get("UserType");
        }

        //Fetches the recycler view by id and sets up layout and
        //adapters to fill schedule with the correct information
        scheduleView  = (RecyclerView) findViewById(R.id.schedule_view);
        scheduleLayout = new LinearLayoutManager(this);
        scheduleView.setLayoutManager(scheduleLayout);

        scheduleAdapter = new ScheduleAdapter(lessons);
        scheduleView.setAdapter(scheduleAdapter);

        //initialises tool bar with menu button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Schedule");
        }

        //Fetches the layout for the drawer
        //Set in layout->drawer_layout
        drawer_menu_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Gets the navigation view component and accesses the associated header
        //Then sets the title to the currently signed in username
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navigationDrawerHeader = navigationView.getHeaderView(0);
        TextView drawerHeaderTitle = (TextView)navigationDrawerHeader.findViewById(R.id.drawer_header_title);
        drawerHeaderTitle.setText(username + " (" + userType +")");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawer_menu_layout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
