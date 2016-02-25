package com.team07.signinapp;

import android.support.design.widget.NavigationView;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.Toast;

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

    private void initializeData(){
        lessons = new ArrayList<>();

        // TODO: Pull from database
        lessons.add(new Lesson("Name1", "Place1", "Time1"));
        lessons.add(new Lesson("Name2", "Place2", "Time3"));
        lessons.add(new Lesson("Name3","Place2","Time3"));
        lessons.add(new Lesson("Name4","Place2","Time3"));
        lessons.add(new Lesson("Name5","Place2","Time3"));
        lessons.add(new Lesson("Name6","Place2","Time3"));
        lessons.add(new Lesson("Name7","Place2","Time3"));
        lessons.add(new Lesson("Name8","Place2","Time3"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);

        initializeData();

        // Get data passed to this activity from LoginScreenActivity
        String username = null;
        Login.UserType userType = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            userType = (Login.UserType)extras.get("UserType");
        }

        // Fetches the recycler view by id and sets up layout and
        // adapters to fill schedule with the correct information
        scheduleView  = (RecyclerView) findViewById(R.id.schedule_view);
        scheduleLayout = new LinearLayoutManager(this);
        scheduleView.setLayoutManager(scheduleLayout);

        final Login.UserType finalUserType = userType;
        scheduleAdapter = new ScheduleAdapter(lessons, new ScheduleAdapter.ScheduleLessonHandler() {
            @Override
            public void handleLesson(int i) {
                if(finalUserType.equals(Login.UserType.Staff)) {
                    //Toast.makeText(v.getContext(), "Staff", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LandingScreenActivity.this, StaffLessonActivity.class);
                    startActivity(intent);
                }
                else if(finalUserType.equals(Login.UserType.Student)){
                    final EditText code = new EditText(LandingScreenActivity.this);
                    new AlertDialog.Builder(LandingScreenActivity.this)
                            .setTitle("Enter Lesson Code")
                            .setMessage("Enter 4-digit number to mark your presence")
                            .setView(code)
                            .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String url = code.getText().toString();
                                    Toast.makeText(LandingScreenActivity.this, url, Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            })
                            .show();
                }

            }
        });
        scheduleView.setAdapter(scheduleAdapter);



        // initialises tool bar with menu button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Schedule");
        }

        // Fetches the layout for the drawer
        // Set in layout->drawer_layout
        drawer_menu_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Gets the navigation view component and accesses the associated header
        // Then sets the title to the currently signed in username
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
