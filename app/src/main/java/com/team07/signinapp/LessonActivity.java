package com.team07.signinapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class LessonActivity extends AppCompatActivity {

    private Lesson lesson;
    private String lessonName;
    private String lessonTime;
    private String lessonLocation;
    private String lessonDate;
    private Login.UserType userType;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiveUserData();
        setLayout();
        setVariables();
        setLessonText();
        setupToolBar();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                //Toolbar button has been pressed. End this activity. Defaults to parent activity
                this.finish();
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
            lesson = (Lesson)getIntent().getSerializableExtra("Lesson");
            userType = (Login.UserType)extras.get("UserType");
        }
    }

    private void setLayout()
    {
        if(userType.equals(Login.UserType.Staff))
        {
            setContentView(R.layout.activity_lesson_staff);
        }
        else
        {
            setContentView(R.layout.activity_lesson_student);
        }
    }

    private void setVariables() {
        if (lesson != null) {
            lessonName = lesson.name;
            lessonLocation = lesson.location;
            lessonTime = lesson.time;
            lessonDate = lesson.date;
        }
    }

    private void setLessonText()
    {
        TextView lessonTitleView = (TextView)findViewById(R.id.lessonTitle);
        TextView lessonLocationView = (TextView)findViewById(R.id.locationField);
        TextView lessonTimeView = (TextView)findViewById(R.id.timeField);
        TextView lessonDateView = (TextView)findViewById(R.id.dateField);
        lessonTitleView.setText(lessonName);
        lessonLocationView.setText(lessonLocation);
        lessonTimeView.setText(lessonTime);
        lessonDateView.setText(lessonDate);

    }

    private void setupToolBar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(null);
        }
    }

    public void generateCode(View view){
        TextView codeTextView = (TextView)this.findViewById(R.id.codeText);

        // Generate and store code to db for lesson id
        String code = Pin.getShared().generatePin(lesson.id);
        codeTextView.setText(code);
    }
}
