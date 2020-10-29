package com.example.covidfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityForgotePass extends AppCompatActivity {


    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_forgote_pass);
        editText= this.<EditText>findViewById(R.id.edit_text);


    }

    @Override
    public void onBackPressed()
    {


        Intent intent=new Intent(ActivityForgotePass.this, ActivityLogin.class);
        startActivity(intent);
        finish();

    }


    public void sendmail(View v)
    {

        String getEmailId = editText.getText().toString();

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        if (getEmailId.equals("") || getEmailId.length() == 0) {


            Toast.makeText(this, " Enter  Email ", Toast.LENGTH_SHORT).show();


        }
        // Check if email id is valid or not
        else if (!m.find())
            Toast.makeText(this, "Your Email Id is Invalid.", Toast.LENGTH_SHORT).show();

        else
        {
            FirebaseAuth.getInstance().sendPasswordResetEmail(getEmailId)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Intent intent=new Intent(ActivityForgotePass.this, ActivityLogin.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(ActivityForgotePass.this, "Email sent.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }





}
