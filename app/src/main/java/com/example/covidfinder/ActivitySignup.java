package com.example.covidfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivitySignup extends AppCompatActivity {


    EditText editText_name,editText_email,editText_password,editText_confirmPassword;
    RadioGroup radioGroup;
    Button button;
    RadioButton radioButton;

    String email;
    String password;
    String date;
    String covid_check;
    String name;
    String userid;
    int a;

    FirebaseUser user;
   FirebaseAuth auth;
   DatabaseReference databaseReference;
   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_signup);

        editText_name=findViewById(R.id.Name);
        editText_email=findViewById(R.id.Email);
        editText_password=findViewById(R.id.password);
        editText_confirmPassword=findViewById(R.id.confirmPassword);
        radioGroup= this.<RadioGroup>findViewById(R.id.radio);
        button= this.<Button>findViewById(R.id.button1);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        auth= FirebaseAuth.getInstance();
        Intent intent=getIntent();
        if(intent!=null)
        {
            name=intent.getStringExtra("name");
            covid_check=intent.getStringExtra("covcheck");
            date=intent.getStringExtra("date");
            email=intent.getStringExtra("email");
            password=intent.getStringExtra("password");

        }

    }

    @Override
    public void onBackPressed()
    {

        Intent intent=new Intent(ActivitySignup.this, ActivityLogin.class);
        startActivity(intent);
        finish();
    }

    public void register(View v)
    {

        name = editText_name.getText().toString();
        email= editText_email.getText().toString();
        password=editText_password.getText().toString();
        String getConfirmPassword =editText_confirmPassword.getText().toString();
        a =-1;
        a=radioGroup.getCheckedRadioButtonId();

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(email);

        if (name.equals("") || name.length() == 0
                || email.equals("") ||email.length() == 0
                ||password.equals("") || password.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0 || a==-1) {

            Toast.makeText(this, "All fields are required !", Toast.LENGTH_SHORT).show();

        }
        else if(password.length()<6 || getConfirmPassword.length()<6)
        {
            Toast.makeText(this, "Password must be at least 6 charecter ! ", Toast.LENGTH_SHORT).show();
        }

        else if (!m.find()) {
            Toast.makeText(this, "Your Email Id is Invalid !", Toast.LENGTH_SHORT).show();
        }
        else if (!getConfirmPassword.equals(password))
            Toast.makeText(this, "Both password doesn't match !", Toast.LENGTH_SHORT).show();

        else {

            progressDialog=new ProgressDialog(this);

            progressDialog.setMessage("Checking email ....");
            progressDialog.show();

            auth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                            if(task.isSuccessful())
                            {
                                boolean check=!task.getResult().getSignInMethods().isEmpty();

                                progressDialog.dismiss();
                                if(!check)
                                {
                                    radioButton = findViewById(a);
                                    covid_check = radioButton.getText().toString();

                                    progressDialog.setMessage("Please wait while your account is created....");
                                    progressDialog.show();

                                    auth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {

                                                    if (task.isSuccessful()) {
                                                        ClassUserInfo createUser = new ClassUserInfo(name, email, password, covid_check, 0.0, 0.0);
                                                        user = auth.getCurrentUser();

                                                        userid = user.getUid();

                                                        databaseReference.child(userid).setValue(createUser)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            progressDialog.dismiss();
                                                                            send_email();
                                                                            Intent intent = new Intent(ActivitySignup.this, ActivityLogin.class);
                                                                            startActivity(intent);
                                                                            finish();

                                                                        } else {
                                                                            progressDialog.dismiss();
                                                                            Toast.makeText(ActivitySignup.this, "Something went wrong ! ", Toast.LENGTH_SHORT).show();
                                                                        }

                                                                    }
                                                                });
                                                    }

                                                }
                                            });
                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Email is already registered",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
         }
    }

    public void send_email()
    {
        user.sendEmailVerification().
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(ActivitySignup.this, "Email was  sent for verification ", Toast.LENGTH_SHORT).show();
                            auth.signOut();
                        }
                        else
                        {
                            Toast.makeText(ActivitySignup.this, "Something went wrong ! ", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
