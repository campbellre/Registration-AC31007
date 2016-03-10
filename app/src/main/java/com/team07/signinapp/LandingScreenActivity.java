package com.team07.signinapp;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LandingScreenActivity extends AppCompatActivity {

    private DrawerLayout drawer_menu_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);

        //initialises tool bar with menu button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Schedule");

        drawer_menu_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
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

<<<<<<< Updated upstream
    public void lessonClickButton(View view)
    {
        Intent intent = new Intent(this, LessonCardViewActivity.class);
        startActivity(intent);
=======
    private void receiveUserData(){
        // Get data passed to this activity from LoginScreenActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //userType = (Login.UserType)extras.get("UserType");
            user = (User)extras.getParcelable("User");
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

        // Add onClick event to drawer attendance button
        navigationView.getMenu().findItem(R.id.navigation_attendance).setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // TODO: display attendance
                        Intent intent = new Intent(getApplicationContext(), AttendanceActivity.class);
                        startActivity(intent);
                        return true;
                    }
                }
        );

>>>>>>> Stashed changes
    }
}
