package com.example.covidfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.covidfinder.R;

public class ActivityCovidCheck extends AppCompatActivity {

    RadioGroup radioGroup;
    Button button;
    String email,password,name,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_covid_status_change);

        radioGroup= this.<RadioGroup>findViewById(R.id.radio);
        button= this.<Button>findViewById(R.id.button1);


        Intent intent=getIntent();
        if(intent!=null)
        {
            name=intent.getStringExtra("name");
            date=intent.getStringExtra("date");
            email=intent.getStringExtra("email");
            password=intent.getStringExtra("password");

        }

    }
}
