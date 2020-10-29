package com.example.covidfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.covidfinder.R;

import java.util.ArrayList;

public class ActivityCovidTestinfo extends AppCompatActivity {
    ListView listView;
    Spinner spinner;
    ArrayAdapter<Divisions> adapter;
    String [] divisions={"Chattagram","Barisal","Dhaka","Khulna","Rajshahi","Rangpur","Sylhet","Mymensingh"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covit_testinfo);
        spinner=findViewById(R.id.covid_test_spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,divisions));
        listView=findViewById(R.id.covid_test_listview);
        listView.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,getDivision()));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i>=0 && i<divisions.length)
                {
                    getSelectCatagoryData(i);
                    listaction();
                }
                else
                {
                    Toast.makeText(ActivityCovidTestinfo.this, "Divisions Doen't Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ActivityCovidTestinfo.this, ActivityLocationMap.class);
        startActivity(intent);
        finish();
    }

    private ArrayList<Divisions>getDivision()
    {
        ArrayList<Divisions>data=new ArrayList<>();
        data.clear();

        data.add(new Divisions("Chattagram , 031-2780426",0));
        data.add(new Divisions("Cox's Bazar , 01821-431144",0));
        data.add(new Divisions("Cumilla , 0816-5562",0));
        data.add(new Divisions("Chattagram , 031-659492",0));
        data.add(new Divisions("Not Available",1));
        data.add(new Divisions("Dhaka , 02-9898796",2));
        data.add(new Divisions("Dhaka , 028821361",2));
        data.add(new Divisions("Dhaka , 09666-771100",2));
        data.add(new Divisions("Dhaka , 01793-163304",2));
        data.add(new Divisions("Dhaka , 01769016616",2));
        data.add(new Divisions("Dhaka , 02-48110117",2));
        data.add(new Divisions("Dhaka , 01866-637482",2));
        data.add(new Divisions("Dhaka , 0255165088",2));
        data.add(new Divisions("Dhaka , 02-9139817",2));
        data.add(new Divisions("Dhaka , 01769010200",2));
        data.add(new Divisions("Dhaka , 09610678678",2));
        data.add(new Divisions("Dhaka , 09610-010616",2));
        data.add(new Divisions("Savar ,01705763586 ",2));
        data.add(new Divisions("Dhaka , 0431-2173547",2));
        data.add(new Divisions("Dhaka , 02-57315076",2));
        data.add(new Divisions("Dhaka , 01921-532339",2));
        data.add(new Divisions("Dhaka , 01796500117",2));
        data.add(new Divisions("Mahakhali , 02-9842275",2));
        data.add(new Divisions("Mahakhali , 01712-017973",2));
        data.add(new Divisions("Narayangaj , 02-9566080",2));
        data.add(new Divisions("Faridpur , 0631-63331",2));
        data.add(new Divisions("Khulna , 041-760350",3));
        data.add(new Divisions("Kustia , 01755-201950",3));
        data.add(new Divisions("Jasor , 0421-61333",3));
        data.add(new Divisions("Rajshahi , 0721-772150",4));
        data.add(new Divisions("Bogura , 051-69965",4));
        data.add(new Divisions("Rangpur , 0521-63388",5));
        data.add(new Divisions("Dinajpur , 0531-64787",5));
        data.add(new Divisions("Sylhet , 0821-713667",6));
        data.add(new Divisions("Mymensingh , 091-66063",7));

        return  data;
    }


    private  void getSelectCatagoryData(int ID)
    {
        ArrayList<Divisions>divisions=new ArrayList<>();

        for(Divisions divisions1:getDivision())
        {
            if(divisions1.getID()==ID)
            {
                divisions.add(divisions1);
            }
        }
        adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,divisions);

        listView.setAdapter(adapter);
    }

    public  void listaction()
    {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String value=adapterView.getAdapter().getItem(i).toString();
                String st="";
                for(int j=0;j<value.length();j++) {

                    if((value.charAt(j))=='0' || (value.charAt(j))=='1' ||(value.charAt(j))=='2' ||(value.charAt(j))=='3'
                            ||(value.charAt(j))=='4' ||(value.charAt(j))=='5' ||(value.charAt(j))=='6'
                            ||(value.charAt(j))=='7' ||(value.charAt(j))=='8' ||(value.charAt(j))=='9')
                    {
                        st+=value.charAt(j);
                    }
                    if(value.charAt(j)==',')
                    {
                        st+=" ";
                    }
                }
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                        "tel", st, null));
                startActivity(phoneIntent);

            }
        });
    }


    class Divisions
    {

        private  String name;
        private  int ID;

        public String getName()
        {
            return  name;
        }

        public  int getID()
        {
            return  ID;
        }

        public  Divisions(String name,int ID)
        {
            this.name=name;
            this.ID=ID;
        }

        public  String toString()
        {
            return  name;
        }

    }
}
