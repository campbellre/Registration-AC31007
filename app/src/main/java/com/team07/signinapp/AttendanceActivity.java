package com.team07.signinapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by Mantis on 10/03/2016.
 */
public class AttendanceActivity extends AppCompatActivity {

    public AttendanceActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_view);
        setTitle("Attendance");

    }

    //Override for custom transition animation when android back button is pressed
    @Override
    public void onBackPressed()
    {
        this.finish();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }

}
