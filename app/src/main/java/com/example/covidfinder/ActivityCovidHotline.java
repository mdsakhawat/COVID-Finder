package com.example.covidfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidfinder.R;

public class ActivityCovidHotline extends AppCompatActivity {


    ListView listView;
    String[] phnnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_covid_hotline);

        phnnum=getResources().getStringArray(R.array.phn_num);

        listView= this.<ListView>findViewById(R.id.list_view_id);

      Customadapter customadapter=new Customadapter(this,phnnum);
      listView.setAdapter(customadapter);

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

        Intent intent=new Intent(ActivityCovidHotline.this, ActivityLocationMap.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
