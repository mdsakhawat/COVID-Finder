package com.example.newgpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Activityhelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_activityhelp);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent=new Intent(Activityhelp.this,ActivityMain.class);
        startActivity(intent);
        finish();
        super.onBackPressed();


    }
}
