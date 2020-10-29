package com.example.covidfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidfinder.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityFeedback extends AppCompatActivity {


    EditText editText_email, editText_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);

        editText_email = findViewById(R.id.edit_text_email);
        editText_feedback = findViewById(R.id.edit_text_feedback);


    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ActivityFeedback.this, ActivityLocationMap.class);
        startActivity(intent);
        finish();

    }

    public void sendfeedback(View v) {

        try {

            String email, feedback;

            email = editText_email.getText().toString();
            feedback = editText_feedback.getText().toString();

            Pattern p = Pattern.compile(Utils.regEx);

            Matcher m = p.matcher(email);

            // Check for both field is empty or not
            if (email.equals("") ||email.length() == 0
                    || feedback.equals("") || feedback.length() == 0) {


                Toast.makeText(this, " Email and Feedback can't be empty.", Toast.LENGTH_SHORT).show();


            }
            // Check if email id is valid or not
            else if (!m.find())
                Toast.makeText(this, "Your Email Id is Invalid.", Toast.LENGTH_SHORT).show();
            else {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"smsiam40@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback From User");
                intent.putExtra(Intent.EXTRA_TEXT, "Email : " + email + "\n" + "Message : " + feedback);
                startActivity(Intent.createChooser(intent, "Feedback With"));

                Toast.makeText(this, "Sent successfully !", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ActivityFeedback.this, ActivityLocationMap.class);
                startActivity(intent1);
                finish();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Something went  wrong !", Toast.LENGTH_SHORT).show();
        }


    }
}
