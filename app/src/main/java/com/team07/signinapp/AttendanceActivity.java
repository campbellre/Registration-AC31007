package com.team07.signinapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.prolificinteractive.materialcalendarview.*;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import org.apache.commons.lang3.time.DateUtils;

public class AttendanceActivity extends AppCompatActivity {

    private DrawerLayout drawer_menu_layout;
    private String username;
    private User user = null;
    private List<Lesson> lessons;

    private MaterialCalendarView calendarView;

    private final static int MAX_LESSONS_IN_DAY = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        receiveUserData();
        setupToolbar();
        setupDrawer();

        // FIX: get rid of this
        final HashMap<Integer, Integer> lessonsDayCache = new HashMap<>();

        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        // NOTE: this will surely lead to insanity
        for(int i=1;i<MAX_LESSONS_IN_DAY;i++) {
            final int n = i;

            calendarView.addDecorator(new DayViewDecorator() {
                @Override
                public boolean shouldDecorate(CalendarDay day) {
                    int dayOfYear = day.getCalendar().get(Calendar.DAY_OF_YEAR);

                    if(!lessonsDayCache.containsKey(dayOfYear)) {
                        // FIX: probably a little in-efficient
                        int nLessons = 0;
                        for (int i = 0; i < AttendanceActivity.this.lessons.size(); i++) {
                            if (DateUtils.isSameDay(AttendanceActivity.this.lessons.get(i).getDateTime(), day.getDate()))
                                nLessons++;
                        }

                        lessonsDayCache.put(dayOfYear, nLessons);
                    }

                    return lessonsDayCache.get(dayOfYear) == n;
                }

                @Override
                public void decorate(DayViewFacade view) {
                    view.addSpan(new MultipleDotSpan(5, Color.RED, n));
                }
            });
        }
    }

    private void receiveUserData(){
        // Get data passed to this activity from LoginScreenActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getParcelable("User");
            lessons = (ArrayList<Lesson>) getIntent().getSerializableExtra("Lessons");
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

        navigationView.getMenu().findItem(R.id.navigation_attendance).setChecked(true);
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

class MultipleDotSpan implements LineBackgroundSpan {

    private final float radius;
    private final int color;
    private final int quantity;

    /**
     * Create a span to draw a dot using a specified radius and color
     *
     * @param radius radius for the dot
     * @param color  color of the dot
     */
    public MultipleDotSpan(float radius, int color, int quantity) {
        this.radius = radius;
        this.color = color;
        this.quantity = quantity;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }
        double radPerLesson = (Math.PI * 2) / 18;
        for(int i=0;i<this.quantity;i++) {
            int halfWidth = (left + right) / 2;
            int halfHeight = (top + bottom) / 2;
            canvas.drawCircle((int)(halfWidth + Math.sin(i * radPerLesson) * ((right - left) / 3)),
                              (int)(halfHeight + Math.cos(i * radPerLesson) * ((right - left) / 3)), radius, paint);
        }
        paint.setColor(oldColor);
    }
}
