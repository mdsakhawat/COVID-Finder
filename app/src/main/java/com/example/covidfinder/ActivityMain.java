package com.example.covidfinder;




import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.covidfinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityMain extends AppCompatActivity {

    FirebaseUser user;
    FirebaseAuth auth;
    Button imageButton_help,imageButton_start;

    private  static  final  String API_KEY=BuildConfig.ApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        imageButton_help=findViewById(R.id.help_button);
        imageButton_start=findViewById(R.id.start_button);

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }


    public void gotohelp(View v)
   {
       Intent intent = new Intent(ActivityMain.this, Activityhelp.class);
       startActivity(intent);
       finish();
   }
   public void goto_login(View v)
   {
       if (user == null || !(user.isEmailVerified())) {


           Intent intent = new Intent(ActivityMain.this, ActivityLogin.class);
           startActivity(intent);
           finish();


       } else {
           Intent intent = new Intent(ActivityMain.this, ActivityLocationMap.class);
           startActivity(intent);
           finish();
       }
   }
}
