package com.team07.signinapp;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.os.Parcelable;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

public class LandingScreenActivity extends AppCompatActivity{
    private DrawerLayout drawer_menu_layout;
    private RecyclerView scheduleView;
    private RecyclerView.Adapter scheduleAdapter;
    private RecyclerView.LayoutManager scheduleLayout;

    // Current list of lessons to be used for testing.
    // Later implement fetch from database
    private ArrayList<Lesson> lessons;
    private LessonInterface lessonInterface = new LessonInterface();
    private String username = null;
    //private Login.UserType userType = null;
    private User user = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);
        receiveUserData();
        fetchLessons();
        initializeData();
        setupScheduleView();
        setupToolbar();
        setupDrawer();
    }

    private void fetchLessons()
    {
        lessonInterface.fetchLessons(user.getId(), user.getUserType());
        lessons = lessonInterface.getLessons();
    }

    private void initializeData() {
        int dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        for(int i=0; i<10; i++){
            // Set date to be within five days of today
            int closeDay = dayOfYear - 5 + (int)Math.round(Math.random() * 10);
            GregorianCalendar day = new GregorianCalendar();
            day.set(Calendar.YEAR, 2016);
            day.set(Calendar.DAY_OF_YEAR, closeDay);
        }
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

    private void receiveUserData(){
        // Get data passed to this activity from LoginScreenActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getParcelable("User");
            //userType = (Login.UserType)extras.get("UserType");
            username = user.getUsername();
        }
    }

    private void setupScheduleView(){
        // Fetches the recycler view by id and sets up layout and
        // adapters to fill schedule with the correct information
        scheduleView  = (RecyclerView) findViewById(R.id.schedule_view);
        scheduleLayout = new LinearLayoutManager(this);
        scheduleView.setLayoutManager(scheduleLayout);

        //final Login.UserType finalUserType = userType;

        scheduleAdapter = new ScheduleAdapter(lessons, new ScheduleAdapter.ScheduleLessonHandler() {
            @Override
            public void handleLesson(int i, Lesson lesson) {
                Intent intent = new Intent(LandingScreenActivity.this, LessonActivity.class);
                //intent.putExtra("UserType", finalUserType);
                intent.putExtra("Lesson", lesson);
                intent.putExtra("User", user);
                startActivity(intent);
                overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);
            }
        });
        scheduleView.setAdapter(scheduleAdapter);
    }

    private void setupToolbar(){
        // Initialises tool bar with menu button
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
        drawerHeaderTitle.setText(username + " (" +
                ((user.isStaff()) ? "Staff" : "Student")
                + ")");

        navigationView.getMenu().findItem(R.id.navigation_schedule).setChecked(true);
        // Add onClick event to drawer Attendance button
        navigationView.getMenu().findItem(R.id.navigation_attendance).setOnMenuItemClickListener(
            new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // TODO: Remove user login data if stored in future
                    Intent intent = new Intent(getApplicationContext(), AttendanceActivity.class);
                    intent.putExtra("Lessons", LandingScreenActivity.this.lessons);
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
}