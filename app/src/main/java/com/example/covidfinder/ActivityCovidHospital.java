package com.example.covidfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.covidfinder.R;

public class ActivityCovidHospital extends AppCompatActivity {


    ListView listView;
    String [] hospitalname,phnnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_covid_hospit);
        listView=findViewById(R.id.list_view_id);
        hospitalname=getResources().getStringArray(R.array.hospital_name);
        phnnum=getResources().getStringArray(R.array.hospital_num);

        CustomAdapter2 customAdapter2=new CustomAdapter2(ActivityCovidHospital.this,hospitalname,phnnum);
        listView.setAdapter(customAdapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String value=phnnum[i];
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                        "tel", value, null));
                startActivity(phoneIntent);

            }
        });



    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(ActivityCovidHospital.this, ActivityLocationMap.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}
