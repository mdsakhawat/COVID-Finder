package com.example.covidfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.covidfinder.R;

public class CustomAdapter2 extends BaseAdapter {

    Context context;
    String [] hosptal,phn;
    LayoutInflater layoutInflater;

    CustomAdapter2(Context context,String [] hosptal,String [] phn)
    {
        this.context=context;
        this.hosptal=hosptal;
        this.phn=phn;
    }
    @Override
    public int getCount() {
        return hosptal.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.sampleview2,viewGroup,false);

        }

        TextView textView1=view.findViewById(R.id.text_view_hospital);
        TextView textView2=view.findViewById(R.id.text_view_phn);
        textView1.setText(hosptal[i]);
        textView2.setText(phn[i]);
        return view;
    }
}
