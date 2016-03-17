package com.team07.signinapp;

import android.content.Context;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class LessonActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    private Lesson lesson;
    private Register register;
    private String lessonName;
    private String lessonTime;
    private String lessonLocation;
    private String lessonDate;
    private String lessonType;
    private Login.UserType userType;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;

    private Handler checkRegisterUpdate;
    private int registerUpdateInterval = 5000;


    private int totalStudents;
    private int currentStudents = 0;

    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiveUserData();
        setupGoogleApiClient();
        ensureLocationServicesEnabled();
        setLayout();
        setVariables();
        setLessonText();
        setupToolBar();

        //if (userType.equals(Login.UserType.Student)) {
        if(user.isStudent()){
            // checks if student is already signed in for lesson
            // and updates button to reflect
            // FIX: possibly pass in student id
            int userID = 1234;
            if (Pin.isSignedIn(lesson.getId(), userID)) {
                Button attendanceSignIn = (Button) findViewById(R.id.attendanceSignIn);
                attendanceSignIn.setBackgroundColor(Color.GREEN);
                attendanceSignIn.setEnabled(false);
                attendanceSignIn.setText("Signed In");
            }
        }
        if(user.isStaff()){
            register.fetchRegister(lesson.getId());
            totalStudents = register.getStudents().size();
            setRegisterCounter();
            startUpdateRunnable();
        }
    }

    public void setupGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(AppIndex.API)
                .build();
    }

    // Modified from http://stackoverflow.com/a/3470757
    protected void ensureLocationServicesEnabled() {
        // Provider not enabled, prompt user to enable it
        System.out.println(isLocationEnabled(getApplicationContext()));
        if(!isLocationEnabled(getApplicationContext())){
            finish();
            Toast.makeText(this, "Please turn on high-accuracy location service", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            this.startActivity(myIntent);
        }
    }

    // Function taken from http://stackoverflow.com/a/22980843
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode == Settings.Secure.LOCATION_MODE_HIGH_ACCURACY;
        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
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
                stopUpdateRunnable();
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
            user = extras.getParcelable("User");
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
        checkRegisterUpdate = new Handler();
        register = new Register();
        if (lesson != null) {
            lessonName = lesson.getName();
            lessonLocation = lesson.getBuilding();
            lessonTime = lesson.getTimeString();
            lessonDate = lesson.getDateString();
            lessonType = lesson.getType();
        }
    }

    private void setLessonText() {
        TextView lessonTitleView = (TextView) findViewById(R.id.LessonTitle);
        TextView lessonLocationView = (TextView) findViewById(R.id.LessonLocation);
        TextView lessonTimeView = (TextView) findViewById(R.id.LessonTime);
        TextView lessonDateView = (TextView) findViewById(R.id.LessonDate);
        TextView lessonTypeView = (TextView) findViewById(R.id.LessonType);
        lessonTitleView.setText(lessonName);
        lessonLocationView.setText(lessonLocation);
        lessonTimeView.setText(lessonTime);
        lessonDateView.setText(lessonDate);
        lessonTypeView.setText(lessonType);
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

    // TODO: Instead of hiding button when pressed, check whether pin for this lesson is currently
    // TODO: in the database and hide the button if so. This means that pressing back and entering
    // TODO: the lesson view again will not allow generation of a new pin.
    public void generateCode(View view) {
        TextView codeTextView = (TextView) this.findViewById(R.id.codeText);
        Button generateCodeBut = (Button) this.findViewById(R.id.generateCode);
        Integer code = Pin.generatePin(lesson.getId());
        if(code == null) {
            new AlertDialog.Builder(LessonActivity.this)
                .setMessage("Failed to set pin, please try again.")
                .setTitle("Attendance")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                }).show();
        } else {
            generateCodeBut.setVisibility(View.GONE);
            codeTextView.setVisibility(View.VISIBLE);
            codeTextView.setText(String.valueOf(code));
        }
    }

    private void startUpdateRunnable()
    {
        getUpdatedRegister.run();
    }

    private void stopUpdateRunnable()
    {
        checkRegisterUpdate.removeCallbacks(getUpdatedRegister);
    }

    Runnable getUpdatedRegister = new Runnable() {
        @Override
        public void run() {
            try {
                updateRegister();
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                checkRegisterUpdate.postDelayed(getUpdatedRegister, registerUpdateInterval);
            }
        }
    };

    //TODO:fetch details from database
    private void updateRegister()
    {
        if(currentStudents<totalStudents) {
            currentStudents += 1;
            TextView registerCurrentStudents = (TextView) findViewById(R.id.RegisterCurrentStudents);
            registerCurrentStudents.setText(Integer.toString(currentStudents));
        }
        else{
            stopUpdateRunnable();
        }
    }

    public void studentSignIn(View view) {
        final EditText codeText = new EditText(this);
        codeText.setInputType(InputType.TYPE_CLASS_NUMBER);

        DialogInterface.OnClickListener signInListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int pin = Integer.parseInt(codeText.getText().toString());
                int lessonID = lesson.getId();

                Boolean pinCorrect = Pin.checkPin(pin, lessonID);

                if(pinCorrect == null) {
                    new AlertDialog.Builder(LessonActivity.this)
                            .setMessage("Failed to check pin, please try again.")
                            .setTitle("Attendance")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            }).show();

                } else if(pinCorrect == false) {
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
                .setView(codeText)
                .setPositiveButton(R.string.attendance_sign_in, signInListener)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                })
                .create();

        // Force keyboard
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }

    public void viewRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("Register",register);
        startActivity(intent);
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        googleApiClient.connect();

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
        AppIndex.AppIndexApi.start(googleApiClient, viewAction);
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
        AppIndex.AppIndexApi.end(googleApiClient, viewAction);
        googleApiClient.disconnect();
    }

    //Override for custom transition animation when android back button is pressed
    @Override
    public void onBackPressed() {
        stopUpdateRunnable();
        this.finish();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }

    private void printLocation(){
        if (lastLocation != null) {
            System.out.println("Latitude: " + String.valueOf(lastLocation.getLatitude()));
            System.out.println("Longitude: " + String.valueOf(lastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        printLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("Connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        System.out.println("Connection failure");
    }
}