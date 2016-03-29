package com.apporio.tyretimes.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.apporio.tyretimes.Horizontal_ListView.HorizontalListView;
import com.apporio.tyretimes.ListInnerActivity;
import com.apporio.tyretimes.R;
import com.apporio.tyretimes.urlapi.Api_s;
import com.squareup.picasso.Picasso;

/**
 * Created by saifi45 on 1/23/2016.
 */
public class Exhibitionadapter extends BaseAdapter {
    public  static ArrayList<String> title11;
    public  static ArrayList<String> id;
    public  static ArrayList<String> dscvp;

    public  static ArrayList<String> img;

    LayoutInflater inflater;
    static Context ctc2;

    public Exhibitionadapter(Context activity, ArrayList<String> title11, ArrayList<String> exhibition_id, ArrayList<String> image11, ArrayList<String> description) {

        Log.e("ffff", "adapter method chla");
        this.ctc2=activity;

        this.title11=title11;
        this.id=exhibition_id;
        this.dscvp=description;
        this.img=image11;

        inflater = LayoutInflater.from(this.ctc2);
    }



    @Override
    public int getCount() {

        // int j = title11.size()+1;
//        if(title11.size()<6){
//            j=title11.size();
//        }
        return title11.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView title11,date,city,source1;
        public ImageView img,minus;
        public     TextView descp;
        public LinearLayout llforlist;
        public HorizontalListView hlv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;


        convertView = inflater.inflate(R.layout.eventsitem, null);
        holder = new Holder();
        convertView.setTag(holder);
        holder.title11 = (TextView) convertView.findViewById(R.id.title2234);


        holder.descp = (TextView) convertView.findViewById(R.id.descp);
        holder.img = (ImageView) convertView.findViewById(R.id.img);
        holder.title11.setText("" + Html.fromHtml("" + title11.get(position)).toString());

        holder.descp.setText("" + Html.fromHtml("" + dscvp.get(position)).toString());

        //il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img.get(i), viewHolder.img);
        Picasso.with(ctc2)
                .load(""+ Api_s.imageurl + img.get(position))
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(holder.img);
        holder.img.setTag(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.img.getTag();
                Intent in = new Intent(ctc2, ListInnerActivity2.class);

                in.putExtra("title", "Exhibition");
                in.putExtra("head", title11.get(pos));
                        in.putExtra("id", id.get(pos));
                in.putExtra("descp", dscvp.get(pos));
                in.putExtra("img", img.get(pos));



                in.putExtra("date", "");

                in.putExtra("src","");
                in.putExtra("plc","");

                in.putExtra("src_link","");


                ctc2.startActivity(in);


            }
        });




        return convertView;
    }
}
