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

public class ActivityAmbulance extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    ArrayAdapter<District> adapter;
    String [] districts={"Barisal","Bogura","Chattogram","Dhaka","Feni","Gazipur","Gopalganj","Jashor","Khulna","Kustia","Manikganj","Narayanganj",

                          "Pabna","Rajshahi","Rangpur","Sylhet"
                       };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ambulance);

        spinner=findViewById(R.id.spinner_ambulance);
        spinner.setAdapter(new ArrayAdapter<>(ActivityAmbulance.this, R.layout.support_simple_spinner_dropdown_item,districts));
        listView=findViewById(R.id.listview_ambulance);
        listView.setAdapter(new ArrayAdapter<District>(ActivityAmbulance.this,R.layout.support_simple_spinner_dropdown_item,getDistrict()));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i>=0 && i<districts.length)
                {
                     getSelectCatagoryData(i);

                    listaction();
                }
                else
                {
                    Toast.makeText(ActivityAmbulance.this, "Divisions Doen't Exist", Toast.LENGTH_SHORT).show();
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
        Intent intent=new Intent(ActivityAmbulance.this, ActivityLocationMap.class);
        startActivity(intent);
        finish();
    }




    private ArrayList<District>getDistrict()
    {


        ArrayList<District>data=new ArrayList<>();
        data.clear();

        data.add(new District(" Barishal, 01701777782",0));
        data.add(new District("Bogura sadar , 01713229988",1));
        data.add(new District("Bogura sadar , 05191069",1));
        data.add(new District("Bogura sadar , 01713042500",1));
        data.add(new District("Dorantana Road , 042168366",2));
        data.add(new District("Panchlaish , 031657574",2));
        data.add(new District("Panchlaish , 031650000",2));
        data.add(new District("Khulshi Road  , 031620025",2));
        data.add(new District("Postogola , 01713488425",3));
        data.add(new District("Moghbazar ,01713488412",3));
        data.add(new District("Pallabi , 01711946461",3));
        data.add(new District("Panthapath , 01720448666",3));
        data.add(new District("Dhanmondi , 01715763741",3));
        data.add(new District("Mohammadpur , 01550020871",3));
        data.add(new District("shyamoli , 029127867",3));
        data.add(new District("Kakrayl , 029336611",3));
        data.add(new District("Uttara , 01788888654",3));
        data.add(new District("Green Road , 0133972986",3));
        data.add(new District("Savar , 017682583",3));
        data.add(new District("Gulshan , 01914001234",3));
        data.add(new District("Shahbag , 01711242194",3));
        data.add(new District("Agargaon , 028116061",3));
        data.add(new District("Sher E Bangla , 01834697812",3));
        data.add(new District("Mohakhali , 881175160",3));
        data.add(new District("Banani , 029896697",3));
        data.add(new District("Shahbag , 01713003004",3));
        data.add(new District("Mirpur , 029122560",3));
        data.add(new District("Adabar , 01971191592",3));
        data.add(new District("Moghbazar , 01708456088",3));
        data.add(new District("Ramna , 028318135",3));
        data.add(new District("Feni Sadar , 01671790190",4));
        data.add(new District("Tetulbari , 01791987466",5));
        data.add(new District("Gopalganj Sadar , 01701777782",6));
        data.add(new District("Jashor Sadar , 01713488455",7));
        data.add(new District("Jashar Sadar , 01713488460",7));
        data.add(new District("Sheikh Mujib Road , 042163026",7));
        data.add(new District("Khulna Sadar , 01713488422",7));
        data.add(new District("Khulna Medical , 01911504277",8));
        data.add(new District("Sonadanga , 01779656810",8));
        data.add(new District("Kustia Sadar , 01713488427",9));
        data.add(new District("Manikganj Sadar , 01730859284",10));
        data.add(new District("Narayanganj Sadar , 01701777782",11));
        data.add(new District("Pabna Sadar , 01711170302",11));
        data.add(new District("Pabna Sadar , 01711373722",12));
        data.add(new District("Pabna Sadar , 073165052",12));
        data.add(new District("Police Line , 0721774308",13));
        data.add(new District("RMC , 0721750169",13));
        data.add(new District("Rangpur Sadar , 01715773382",14));
        data.add(new District("Kajol Shah , 01712061539",15));
        data.add(new District("Sylhet Sadar , 0821713041",15));
        data.add(new District("Subhani , 01925616778",15));
        data.add(new District("Sylhet Sadar , 01795871929",15));
        data.add(new District("Medical , 0821717055",15));
        data.add(new District("Metropolitan , 01711069486",15));
        data.add(new District("Dargah Gate , 0821714123",15));
        data.add(new District("South Surma , 01787487876",15));
        data.add(new District("Sylhet sadar , 01739454906",15));
        data.add(new District("Zindabazar , 0821713632",15));
        data.add(new District("Subhani , 01925616778",15));
        return  data;
    }




    private  void getSelectCatagoryData(int ID)
    {
        ArrayList<District> district=new ArrayList<>();

        for(District district1:getDistrict())
        {
            if(district1.getID()==ID)
            {
                district.add(district1);
            }
        }
        adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,district);

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



    class District
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

        public  District(String name,int ID)
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
