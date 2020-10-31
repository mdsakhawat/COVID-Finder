package com.example.covidfinder;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.covidfinder.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class ActivityLocationMap extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    Boolean bool = false, bool1 = false, covid_status = true, camera_zoom = true;
    GoogleMap mMap;
    LatLng latLng;
    String current_pass = "", current_email = "", cur_name = "", cov_check = "NO";
    TextView textView_name, textView_email, textView_cov_check;
    Double lat = 0.0, lng = 0.0, l1 = 0.0, l2 = 0.0;
    public long count = 0;
    String user_id, cov = "NO";

    private long UPDATE_INTERVAL = 20 * 1000;  /* 20 secs */
    private long FASTEST_INTERVAL = 10 * 1000; /* 10 sec */
    private LocationRequest mLocationRequest;
    List<Address> addresses = null;
    String city, locality, knownName;
    MarkerOptions markerOptions = new MarkerOptions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_location_map);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        user_id = user.getUid();


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tracking COVID Patients");
        setSupportActionBar(toolbar);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        statusCheck();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        textView_name = header.findViewById(R.id.title_text);
        textView_email = header.findViewById(R.id.title1_email);
        textView_cov_check = header.findViewById(R.id.title1_covcheck);

        if(!isNetworkAvailable())
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enable your Internet !")
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


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                 current_pass = dataSnapshot.child(user_id).child("password").getValue(String.class);
                cur_name = dataSnapshot.child(user_id).child("name").getValue(String.class);
                current_email = dataSnapshot.child(user_id).child("email").getValue(String.class);
                cov_check = dataSnapshot.child(user_id).child("covid_check").getValue(String.class);

                textView_name.setText("Name : " + cur_name);
                textView_email.setText("Email : " + current_email);
                textView_cov_check.setText("COVID Status: " + cov_check);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startLocationUpdates();


    }


    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable your location ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
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


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET) ==
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_NETWORK_STATE) ==
                PackageManager.PERMISSION_GRANTED) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.setMyLocationEnabled(true);

            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        } else {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET

            }, 10);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_location_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_covid_update) {
            Intent intent = new Intent(ActivityLocationMap.this, ActivityCovidUpdate.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_cov_status) {

            Intent intent = new Intent(ActivityLocationMap.this, ActivityCovidStatusChange.class);
            startActivityForResult(intent, 2);

        } else if (id == R.id.nav_deleteac) {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Do you want to Delete your account ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    bool = true;
                    AuthCredential credential = EmailAuthProvider
                            .getCredential(current_email, current_pass);

                    // Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    finish();
                                    user.delete()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {


                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                dataSnapshot.child(user_id).getRef().removeValue();
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });


                                                        Toast.makeText(ActivityLocationMap.this, "Deleted successfully !", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(ActivityLocationMap.this, ActivityLogin.class);
                                                        startActivity(intent);
                                                        finish();

                                                    }
                                                }
                                            });

                                }
                            });
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        } else if (id == R.id.nav_sign_out) {

            bool1 = true;
            auth.signOut();
            user = null;
            Intent intent = new Intent(ActivityLocationMap.this, ActivityLogin.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(ActivityLocationMap.this, ActivityFeedback.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_cov_hospital) {
            Intent intent = new Intent(ActivityLocationMap.this, ActivityCovidHospital.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_cov_lab_test) {
            Intent intent = new Intent(ActivityLocationMap.this, ActivityCovidTestinfo.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_ambulance) {
            Intent intent = new Intent(ActivityLocationMap.this, ActivityAmbulance.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_cov_hotline) {
            Intent intent = new Intent(ActivityLocationMap.this, ActivityCovidHotline.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = this.<DrawerLayout>findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == 2) {

                String message = data.getStringExtra("MESSAGE");
                if (message.equals("YES")) {
                    cov_check = "YES";
                    covid_status = true;
                    textView_cov_check.setText("COVID Status: " + cov_check);
                    databaseReference.child(user_id).child("covid_check").setValue(message);


                } else {
                    cov_check = "NO";
                    covid_status = false;
                    databaseReference.child(user_id).child("covid_check").setValue(message);
                    databaseReference.child(user_id).child("lat").setValue(0.0);
                    databaseReference.child(user_id).child("lng").setValue(0.0);
                    textView_cov_check.setText("COVID Status: " + message);


                }
            }
        }
    }


    // Trigger new location updates at interval
    protected void startLocationUpdates() {

        // Create the location request to start receiving updates
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);
        getFusedLocationProviderClient(ActivityLocationMap.this).requestLocationUpdates(mLocationRequest, new LocationCallback() {


                    @Override
                    public void onLocationResult(LocationResult locationResult) {

                        if (locationResult.getLastLocation() != null)
                            onLocationChanged(locationResult.getLastLocation());


                    }
                },
                Looper.myLooper());


    }


    public void onLocationChanged(final Location location) {

        if (location == null) {
            SingleToast.show(ActivityLocationMap.this, count + " Something went wrong !", Toast.LENGTH_SHORT);
            return;
        } else {


            if (bool == false && bool1 == false) {
                try {

                    Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                    l1 = location.getLatitude();
                    l2 = location.getLongitude();


                    if (cov_check.equals("YES") && covid_status) {
                        databaseReference.child(user_id).child("lat").setValue(l1);
                        databaseReference.child(user_id).child("lng").setValue(l2);
                    }

                  mMap.clear();

                    if (camera_zoom) {

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(l1, l2)));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(l1, l2), 12));
                        camera_zoom = false;
                    }

                    latLng = new LatLng(l1, l2);

                     if (addresses != null) {
                        addresses.clear();
                    }
                    try {

                        addresses = geocoder.getFromLocation(l1, l2, 5);
                        city = addresses.get(0).getSubAdminArea();
                        locality = addresses.get(0).getLocality();
                        knownName = addresses.get(0).getFeatureName();
                        markerOptions.position(latLng);
                        markerOptions.title(city + "," +locality+","+knownName);
                        mMap.addMarker(markerOptions);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            double R = 6373.0, dlat, dlng;
                            double a, c, distance;
                            count = 0;
                            if(dataSnapshot!=null) {

                                count=0;
                                for (DataSnapshot dst : dataSnapshot.getChildren()) {

                                    lat = (dst.child("lat").getValue(Double.class));
                                    lng = dst.child("lng").getValue(Double.class);
                                    cov = dst.child("covid_check").getValue(String.class);

                                    if ((lat == 0 && lng == 0) || cov.equals("NO")) {

                                        continue;
                                    }

                                    dlng = Math.toRadians(Math.abs(lng - l2));
                                    dlat = Math.toRadians(Math.abs(lat - l1));
                                    a = Math.sin(dlat / 2.0) * Math.sin(dlng / 2.0)
                                            + Math.cos(Math.toRadians(l1)) * Math.cos(Math.toRadians(lat))
                                            * Math.sin(dlng / 2.0) * Math.sin(dlng / 2.0);
                                    c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
                                    distance = R * c * 1000.0;

                                    if (distance <= 100.0) {
                                        count++;
                                    }
                                    if(user_id.equals(dst.getKey()))
                                    {

                                        continue;
                                    }

                                    latLng = new LatLng(lat, lng);
                                    markerOptions.position(latLng);
                                    markerOptions.title("");
                                    mMap.addMarker(markerOptions);


                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            SingleToast.show(ActivityLocationMap.this, databaseError.getMessage().toString(), Toast.LENGTH_SHORT);
                        }

                    });
                    if (count > 0) {

                        SingleToast.show(ActivityLocationMap.this, count + " COVID patients in your area ! ", Toast.LENGTH_SHORT);

                        count = 0;

                    } else {

                        SingleToast.show(ActivityLocationMap.this, "No  COVID patient in your area ! ", Toast.LENGTH_SHORT);
                        count = 0;

                    }





                } catch (Exception e) {
                    SingleToast.show(ActivityLocationMap.this, e.toString(), Toast.LENGTH_SHORT);
                }

            }

        }

    }


    public static class SingleToast {

        private static Toast mToast;

        public static void show(Context context, String text, int duration) {
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(context, text, duration);
            mToast.show();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}










