package com.team07.signinapp;

import android.support.design.widget.NavigationView;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class LandingScreenActivity extends AppCompatActivity {
    private DrawerLayout drawer_menu_layout;
    private RecyclerView scheduleView;
    private RecyclerView.Adapter scheduleAdapter;
    private RecyclerView.LayoutManager scheduleLayout;

    // Current list of lessons to be used for testing.
    // Later implement fetch from database
    private List<Lesson> lessons;

    private String username = null;
    private Login.UserType userType = null;

    private void initializeData(){
        lessons = new ArrayList<>();

        // TODO: Pull from database
        lessons.add(new Lesson(1,"Name1", "Place1", "Time1", "Date1"));
        lessons.add(new Lesson(2,"Name2", "Place2", "Time3", "Date2"));
        lessons.add(new Lesson(3,"Name3", "Place2", "Time3", "Date3"));
        lessons.add(new Lesson(4,"Name4", "Place2", "Time3", "Date4"));
        lessons.add(new Lesson(5,"Name5", "Place2", "Time3", "Date5"));
        lessons.add(new Lesson(6,"Name6", "Place2", "Time3", "Date6"));
        lessons.add(new Lesson(7,"Name7", "Place2", "Time3", "Date7"));
        lessons.add(new Lesson(8,"Name8", "Place2", "Time3", "Date8"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);

        initializeData();

        receiveUserData();
        setupScheduleView();
        setupToolbar();
        setupDrawer();
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

    private void receiveUserData(){
        // Get data passed to this activity from LoginScreenActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            userType = (Login.UserType)extras.get("UserType");
        }
    }

    private void setupScheduleView(){
        // Fetches the recycler view by id and sets up layout and
        // adapters to fill schedule with the correct information
        scheduleView  = (RecyclerView) findViewById(R.id.schedule_view);
        scheduleLayout = new LinearLayoutManager(this);
        scheduleView.setLayoutManager(scheduleLayout);

        final Login.UserType finalUserType = userType;

        scheduleAdapter = new ScheduleAdapter(lessons, new ScheduleAdapter.ScheduleLessonHandler() {
            @Override
            public void handleLesson(int i, Lesson lesson) {
                Intent intent = new Intent(LandingScreenActivity.this, LessonActivity.class);
                intent.putExtra("UserType", finalUserType);
                intent.putExtra("Lesson", lesson);
                startActivity(intent);
            }
        });
        scheduleView.setAdapter(scheduleAdapter);
    }

    private void setupToolbar(){
        // initialises tool bar with menu button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Schedule");
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
        drawerHeaderTitle.setText(username + " (" + userType + ")");

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
}