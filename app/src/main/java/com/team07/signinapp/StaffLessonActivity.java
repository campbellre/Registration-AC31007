package com.team07.signinapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.RandomStringUtils;

public class StaffLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pin);
    }

    public void generateCode(View view){
        TextView codeTextView = (TextView)this.findViewById(R.id.codeTextView);
        //can use randomAlphanumeric also
        String code = Pin.getShared().generatePin();
        codeTextView.setText(code);
    }
}
