package com.example.covidfinder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityLogin extends AppCompatActivity {

    FirebaseUser user;
    FirebaseAuth auth;
    PermissionManager manager;
    EditText emailid,password;
    CheckBox  show_hide_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_login);



        if(!isNetworkAvailable())
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Enable your Internet ")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
        emailid= this.<EditText>findViewById(R.id.login_emailid);
        password= this.<EditText>findViewById(R.id.login_password);
        show_hide_password=findViewById(R.id.show_hide_password);
        manager = new PermissionManager() {

        };
        manager.checkAndRequestPermissions(this);

        show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });

        auth= FirebaseAuth.getInstance();
    }



        public void onBackPressed() {
           Intent intent=new Intent(ActivityLogin.this,ActivityMain.class);
           startActivity(intent);
           finish();
        }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> denied_permision = manager.getStatus().get(0).denied;
        if (denied_permision.isEmpty()) {
            Toast.makeText(this, "Permision enabled", Toast.LENGTH_SHORT).show();
        }
    }



    public  void login(View v)
    {

        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {


            Toast.makeText(this, " Enter both Email and Password.", Toast.LENGTH_SHORT).show();


        }
        // Check if email id is valid or not
        else if (!m.find())
            Toast.makeText(this, "Your Email Id is Invalid.", Toast.LENGTH_SHORT).show();

      else {


            auth.signInWithEmailAndPassword(emailid.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = auth.getCurrentUser();

                                if (user.isEmailVerified()) {
                                    Intent intent = new Intent(ActivityLogin.this, ActivityLocationMap.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(ActivityLogin.this, "Email is not verified !", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else {

                                Toast.makeText(getApplicationContext(), "Invalid Email or Password !", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }
    }

    public void gotosignupActivity(View v)
    {
        Intent intent=new Intent(ActivityLogin.this, ActivitySignup.class);
        startActivity(intent);
        finish();

    }

    public  void  gotoforgotepassActivity(View v)
    {
        Intent intent=new Intent(ActivityLogin.this, ActivityForgotePass.class);
        startActivity(intent);
        finish();
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
