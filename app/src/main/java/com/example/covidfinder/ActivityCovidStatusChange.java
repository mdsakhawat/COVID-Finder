package com.example.covidfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.covidfinder.R;

public class ActivityCovidStatusChange extends AppCompatActivity {



    RadioGroup radioGroup;
    Button button;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_covid_status_change);

        radioGroup= this.<RadioGroup>findViewById(R.id.radio);
        button= this.<Button>findViewById(R.id.button1);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(ActivityCovidStatusChange.this, ActivityLocationMap.class);
        startActivity(intent);
        finish();
    }

    public void gotolocationmapActivity(View v)

    {
        int a =-1;
        a=radioGroup.getCheckedRadioButtonId();
        if(a==-1)
        {
            Toast.makeText(this, "Please Select Anyone ! ", Toast.LENGTH_SHORT).show();
        }
        else {

            radioButton = findViewById(a);
            String  s = radioButton.getText().toString();
            String message=s;
            Intent intent=new Intent();
            intent.putExtra("MESSAGE",message);
            setResult(2,intent);
            finish();


        }


    }
}
