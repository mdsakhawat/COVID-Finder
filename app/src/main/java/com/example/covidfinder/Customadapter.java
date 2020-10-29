package com.example.covidfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.covidfinder.R;

public class Customadapter extends BaseAdapter {


    String []phnnum;
    Context context;
    LayoutInflater layoutInflater;

    Customadapter(Context context,String [] phnnum)
    {
    this.context=context;
    this.phnnum=phnnum;
    }

    @Override
    public int getCount() {
        return phnnum.length;
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
            view=layoutInflater.inflate(R.layout.sampleview,viewGroup,false);

        }

        TextView textView=view.findViewById(R.id.phn_text_view);
        textView.setText(phnnum[i]);
        return view;
    }
}
