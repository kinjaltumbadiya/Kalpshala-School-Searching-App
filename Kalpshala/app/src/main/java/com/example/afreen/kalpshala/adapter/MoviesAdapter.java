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

import com.bumptech.glide.Glide;
import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.model.schoolPrimaryModel;

import java.util.ArrayList;
import java.util.List;



public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> implements Filterable{

    private List<schoolPrimaryModel> schoolPrimaryModelList;
    private int rowLayout;
    private Context context;

    private List<schoolPrimaryModel> mFilteredList;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = schoolPrimaryModelList;
                } else {

                    List<schoolPrimaryModel> filteredList = new ArrayList<>();

                    for (schoolPrimaryModel list : schoolPrimaryModelList) {

                        if (list.getName().toLowerCase().contains(charString) || list.getAddress().toLowerCase().contains(charString) || list.getCity().toLowerCase().contains(charString)) {

                            filteredList.add(list);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }
                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    mFilteredList = (List<schoolPrimaryModel>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
    }


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

    public MoviesAdapter(List<schoolPrimaryModel> schoolPrimaryModelList, int rowLayout, Context context) {
        this.schoolPrimaryModelList = schoolPrimaryModelList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(schoolPrimaryModelList.get(position).getName());
        holder.address.setText(schoolPrimaryModelList.get(position).getAddress());
        holder.city.setText(schoolPrimaryModelList.get(position).getCity());
        holder.phone.setText(schoolPrimaryModelList.get(position).getPhone());
        if((schoolPrimaryModelList.get(position).getMobile())=="")
        {
            holder.mobile.setText("Not Available");
        }
        else
        {
            holder.mobile.setText(schoolPrimaryModelList.get(position).getMobile());
        }

        if((schoolPrimaryModelList.get(position).getWebsite())=="")
        {
            holder.website.setText("Not Available");
        }
        else
        {
            holder.website.setText(schoolPrimaryModelList.get(position).getWebsite());
        }

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+schoolPrimaryModelList.get(position).getPhone()));

                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(callIntent);
            }
        });

        Glide.with(context)
                .load("http://kalpshala.000webhostapp.com/Images/"+schoolPrimaryModelList.get(position).getPhoto())
                .placeholder(R.drawable.camera)
                .into(holder.schoolimage);

     }

    @Override
    public int getItemCount() {
        return schoolPrimaryModelList.size();
    }
}