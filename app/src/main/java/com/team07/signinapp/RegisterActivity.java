package com.team07.signinapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RegisterActivity extends AppCompatActivity {
    private Register register;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        receiveExtraData();
        setupToolBar();
        displayList();
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

    private void setupToolBar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Register");
        }
    }

    private void receiveExtraData()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            register = (Register) getIntent().getSerializableExtra("Register");
        }
    }

    private void displayList()
    {
        listView = (ListView) findViewById(R.id.registerList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, register.getStudents());
        listView.setAdapter(adapter);

//        TextView registerList = (TextView) findViewById(R.id.RegisterList);
//        for(String student: register.getStudents())
//        {
//            registerList.append("\n" + student);
//        }
    }

    //Override for custom transition animation when android back button is pressed
    @Override
    public void onBackPressed()
    {
        this.finish();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }
}
