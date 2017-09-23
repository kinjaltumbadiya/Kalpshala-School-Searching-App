package com.example.afreen.kalpshala.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.model.schoolSecModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afreen on 6/14/2017.
 */



public class SchoolSecAdapter extends RecyclerView.Adapter<SchoolSecAdapter.MovieViewHolder> implements Filterable{

    private List<schoolSecModel> schoolSecModelList;
    private List<schoolSecModel> mFilteredList;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle,address,city,phone,mobile,website;
        ImageView schoolimage;
        Button call;



        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.schoolnametv);
            schoolimage=(ImageView)v.findViewById(R.id.imageschool);

            address = (TextView) v.findViewById(R.id.address);
            city = (TextView) v.findViewById(R.id.city);
            phone = (TextView) v.findViewById(R.id.phone);
            mobile = (TextView) v.findViewById(R.id.mobile);
            website = (TextView) v.findViewById(R.id.website);

            call=(Button)v.findViewById(R.id.call);
        }
    }

    public SchoolSecAdapter(List<schoolSecModel> arrayList) {
        schoolSecModelList = arrayList;
        mFilteredList = arrayList;
    }


    public SchoolSecAdapter(List<schoolSecModel> schoolSecModelList, int rowLayout, Context context) {
        this.schoolSecModelList = schoolSecModelList;
        this.mFilteredList=schoolSecModelList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public SchoolSecAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public Filter getFilter() {
        System.out.println("call kairu");
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = schoolSecModelList;
                } else {

                    List<schoolSecModel> filteredList = new ArrayList<>();

                    for (schoolSecModel androidVersion : schoolSecModelList) {

                        if (androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getName().toLowerCase().contains(charString) ) {
                            System.out.println("added====="+androidVersion.getName()+"aa che");
                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                Filter.FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (List<schoolSecModel>) filterResults.values;
                notifyDataSetChanged();
                System.out.println("notify thyu");
            }
        };
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(mFilteredList.get(position).getName());
        holder.address.setText(mFilteredList.get(position).getAddress());
        holder.city.setText(mFilteredList.get(position).getCity());
        holder.phone.setText(mFilteredList.get(position).getPhone());
        if((mFilteredList.get(position).getMobile())=="")
        {
            holder.mobile.setText("Not Available");
        }
        else
        {
            holder.mobile.setText(mFilteredList.get(position).getMobile());
        }

        if((mFilteredList.get(position).getWebsite())=="")
        {
            holder.website.setText("Not Available");
        }
        else
        {
            holder.website.setText(mFilteredList.get(position).getWebsite());
        }

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0377778888"));

                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(callIntent);
            }
        });

        Glide.with(context)
                .load("http://kalpshala.000webhostapp.com/Images/"+mFilteredList.get(position).getPhoto())
                .placeholder(R.drawable.camera)
                .into(holder.schoolimage);

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }
}