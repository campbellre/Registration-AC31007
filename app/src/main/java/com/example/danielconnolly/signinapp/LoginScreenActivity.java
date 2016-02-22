package com.example.danielconnolly.signinapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginButtonAction(View view){
        EditText usernameEditText = (EditText)this.findViewById(R.id.usernameField);
        EditText passwordEditText = (EditText)this.findViewById(R.id.passwordField);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean loginFieldsFilled = true;

        if(username == null || username.isEmpty()){
            usernameEditText.setError("Username cannot be empty");
            loginFieldsFilled = false;
        }

        if(password == null || password.isEmpty()){
            passwordEditText.setError("Password cannot be empty");
            loginFieldsFilled = false;
        }

        if(loginFieldsFilled) {
            if (loginValid(username, password)) {
                Intent intent = new Intent(this, LandingScreenActivity.class);
                startActivity(intent);
            } else {
                passwordEditText.setError("Incorrect username or password");
            }
        }
    }

    public boolean loginValid(String username, String password){
        // TODO: Validate login
        return true;
    }
}
