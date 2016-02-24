package com.team07.signinapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginScreenActivity extends AppCompatActivity {
    private Login login;

    public LoginScreenActivity(){
        login = new Login();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        setTitle("Login");
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

        if(username.isEmpty()){
            usernameEditText.setError("Username cannot be empty");
            loginFieldsFilled = false;
        }

        if(password.isEmpty()){
            passwordEditText.setError("Password cannot be empty");
            loginFieldsFilled = false;
        }

        if(loginFieldsFilled) {
            if (login.loginValid(username, password)) {
                Intent intent = new Intent(this, LandingScreenActivity.class);
                intent.putExtra("Username", username);
                intent.putExtra("UserType", login.getUserType(username));
                startActivity(intent);
            } else {
                passwordEditText.setError("Incorrect username or password");
            }
        }
    }
}