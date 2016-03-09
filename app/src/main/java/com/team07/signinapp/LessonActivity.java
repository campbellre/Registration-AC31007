package com.team07.signinapp;

import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.Random;

public class LessonActivity extends AppCompatActivity {
    private Lesson lesson;
    private String lessonName;
    private String lessonTime;
    private String lessonLocation;
    private String lessonDate;
    private Login.UserType userType;

    //TODO:Implement Register Class Fully
    private List<String> students;
    private int totalStudents = 50;
    private int currentStudents = 10;

    private User user = null;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiveUserData();
        setLayout();
        setVariables();
        setLessonText();
        setRegisterCounter();
        setupToolBar();

        //if (userType.equals(Login.UserType.Student)) {
        if(user.isStudent()){
            // checks if student is already signed in for lesson
            // and updates button to reflect
            // FIX: possibly pass in student id
            if (Pin.getShared().isSignedIn(lesson.getId())) {
                Button attendanceSignIn = (Button) findViewById(R.id.attendanceSignIn);
                attendanceSignIn.setBackgroundColor(Color.GREEN);
                attendanceSignIn.setEnabled(false);
                attendanceSignIn.setText("Signed In");
            }
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void receiveUserData() {
        // Get data passed to this activity from LoginScreenActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lesson = (Lesson) getIntent().getSerializableExtra("Lesson");
            userType = (Login.UserType) extras.get("UserType");
            user = (User)extras.getParcelable("User");
        }
    }

    private void setLayout() {
        //if (userType.equals(Login.UserType.Staff)) {
        if(user.isStaff()){
            setContentView(R.layout.activity_lesson_staff);
        } else {
            setContentView(R.layout.activity_lesson_student);
        }
    }

    private void setVariables() {
        if (lesson != null) {
            lessonName = lesson.getName();
            lessonLocation = lesson.getLocation();
            lessonTime = lesson.getTimeString();
            lessonDate = lesson.getDateString();
        }
    }

    private void setLessonText() {
        TextView lessonTitleView = (TextView) findViewById(R.id.LessonTitle);
        TextView lessonLocationView = (TextView) findViewById(R.id.LessonLocation);
        TextView lessonTimeView = (TextView) findViewById(R.id.LessonTime);
        TextView lessonDateView = (TextView) findViewById(R.id.LessonDate);
        lessonTitleView.setText(lessonName);
        lessonLocationView.setText(lessonLocation);
        lessonTimeView.setText(lessonTime);
        lessonDateView.setText(lessonDate);
    }

    private void setRegisterCounter()
    {
        TextView registerCurrentStudents = (TextView) findViewById(R.id.RegisterCurrentStudents);
        TextView registerTotalStudents = (TextView) findViewById(R.id.RegisterTotalStudents);
        registerCurrentStudents.setText(Integer.toString(currentStudents));
        registerTotalStudents.setText(Integer.toString(totalStudents));
    }

    private void setupToolBar() {
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

    public void generateCode(View view) {
        TextView codeTextView = (TextView) this.findViewById(R.id.codeText);
        int code = Pin.getShared().generatePin(lesson.getId());
        codeTextView.setText(String.valueOf(code));
    }

    public void studentSignIn(View view) {
        final EditText code = new EditText(this);
        code.setInputType(InputType.TYPE_CLASS_NUMBER);

        DialogInterface.OnClickListener signInListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!Pin.getShared().checkPin(Integer.parseInt(code.getText().toString()), lesson.getId())) {
                    new AlertDialog.Builder(LessonActivity.this)
                            .setMessage(R.string.attendance_pin_incorrect)
                            .setTitle("Attendance")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            }).show();

                } else {
                    Button attendanceSignIn = (Button) findViewById(R.id.attendanceSignIn);
                    attendanceSignIn.setBackgroundColor(Color.GREEN);
                    attendanceSignIn.setEnabled(false);
                    attendanceSignIn.setText("Signed In");
                }
            }
        };

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(R.string.attendance_request_pin)
                .setTitle("Attendance")
                .setView(code)
                .setPositiveButton(R.string.attendance_sign_in, signInListener)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .create();

        // force keyboard
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }

    public void viewRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Lesson Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.team07.signinapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Lesson Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.team07.signinapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    //Override for custom transition animation when android back button is pressed
    @Override
    public void onBackPressed()
    {
        this.finish();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }
}
