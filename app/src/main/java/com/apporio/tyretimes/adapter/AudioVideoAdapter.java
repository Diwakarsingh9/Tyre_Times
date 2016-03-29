package com.apporio.tyretimes.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.apporio.tyretimes.AudioVideo;
import com.apporio.tyretimes.Photoinneractivity;
import com.apporio.tyretimes.R;
import com.apporio.tyretimes.urlapi.Api_s;
import com.squareup.picasso.Picasso;

/**
 * Created by gaurav on 11/21/2015.
 */
public class AudioVideoAdapter extends BaseAdapter {


    ArrayList<String> Title= new ArrayList<String>();
    ArrayList<String> galleryid= new ArrayList<String>();

    ArrayList<String> Date= new ArrayList<String>();
    ArrayList<String> images= new ArrayList<String>();
    ArrayList<String> key= new ArrayList<String>();
    ArrayList<String> descp= new ArrayList<String>();

    Context ctc;

    LayoutInflater inflater;



    public AudioVideoAdapter(Context activity, ArrayList<String> gallery_id,
                             ArrayList<String> category,
                             ArrayList<String> image1, ArrayList<String> title1,
                             ArrayList<String> video_key, ArrayList<String> video_date,
                             ArrayList<String> descp) {
        this.ctc=activity;
        this.Title=title1;
        this.galleryid=gallery_id;
        this.Date= video_date;
        this.images = image1;
        this.key = video_key;
        this.descp = descp;
        inflater = LayoutInflater.from(this.ctc);
    }

    @Override
    public int getCount() {
        return Title.size();
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

        TextView tvTitle,tvSource,tvDate;
        ImageView image;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.gridlistitems,null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }


        holder.tvTitle = (TextView)convertView.findViewById(R.id.title);
        holder.tvTitle.setText(Title.get(position));



        holder.tvDate = (TextView)convertView.findViewById(R.id.date);
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String inputDateStr=""+Date.get(position);
        java.util.Date dateaa = null;
        String outputDateStr="";
        try {
            dateaa = inputFormat.parse(inputDateStr);
             outputDateStr = outputFormat.format(dateaa);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder. tvDate.setText("" + Date.get(position));

        holder.image = (ImageView)convertView.findViewById(R.id.bannerimage);
        Picasso.with(ctc)
                .load(""+  images.get(position))
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(holder.image);
        holder.image.setTag(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.image.getTag();
                Intent in = new Intent(ctc,Photoinneractivity.class);

                in.putExtra("title", "Videos");
                in.putExtra("head", Title.get(pos));
                in.putExtra("key", key.get(pos));
                in.putExtra("date", Date.get(pos));
                in.putExtra("descp", descp.get(pos));
                in.putExtra("img", images.get(pos));
                in.putExtra("src","");
                in.putExtra("plc","");
                in.putExtra("src_link","");
                in.putExtra("id", AudioVideo.gallery_id.get(pos));
                ctc.startActivity(in);

//                in.putExtra("src", source2.get(pos));
//                in.putExtra("plc", city2.get(pos));
            }
        });
        return convertView;
    }

}