package com.example.afreen.kalpshala.adapter;

/**
 * Created by Afreen on 4/29/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.databasehandler.SchoolGetSet;

import java.util.List;

/**
 * Created by Belal on 11/9/2015.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<SchoolGetSet> school;


    public CardAdapter(List<SchoolGetSet> superHeroes, Context context) {
        super();
        //Getting all the superheroes
        this.school = superHeroes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timeline, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SchoolGetSet sc = school.get(position);

        //imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
       // imageLoader.get(sc.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

     //   holder.imageView.setImageUrl(sc.getImageUrl(), imageLoader);
        holder.textViewName.setText(sc.getName());

      /*  Glide.with(context)
                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .into(holder.imageView);
*/
    }

    @Override
    public int getItemCount() {
        return school.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView imageView;
        public TextView textViewName;
        public ImageView heart;
        public TextView textViewRealName;
        public TextView textViewCreatedBy;
        public TextView textViewFirstAppearance;
        public TextView textViewPowers;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.image);
        /*    heart = (ImageView) itemView.findViewById(R.id.hit);
            textViewName = (TextView) itemView.findViewById(R.id.name);
*/
            heart.setOnClickListener(new View.OnClickListener() {

                int c = 0;

                public void onClick(View v) {
                    if (c == 0) {
                     //   heart.setImageResource(R.drawable.heart_click);
                        c = 1;
                    }

                     /*   ////////////////////////////////////////////////
                        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_like, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Log.d("Response", s.toString());
                                try {
                                    JSONObject jObj = new JSONObject(s);
                                    boolean error = jObj.getBoolean("error");
                                    if (!error) {
                                        Toast.makeText(context, "Upload successfully.", Toast.LENGTH_LONG).show();

                                    } else {
                                        String errormsg = jObj.getString("error_msg");
                                        Toast.makeText(context, errormsg + "", Toast.LENGTH_LONG).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(context, "Json error:" + e.getMessage(), Toast.LENGTH_LONG).show();

                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(context, "volleyError error:" + volleyError.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("email", "1");

                                return params;
                            }
                        };
                        AppController.getInstance().addToRequestQueue(strReq);
                    }

                    ////////////////////////////////////////////////////////*/
                    else {
                        heart.setImageResource(R.drawable.heart);
                        c = 0;
                    }
                }
            });

           /* textViewRank= (TextView) itemView.findViewById(R.id.textViewRank);
            textViewRealName= (TextView) itemView.findViewById(R.id.textViewRealName);
            textViewCreatedBy= (TextView) itemView.findViewById(R.id.textViewCreatedBy);
            textViewFirstAppearance= (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
            textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);*/
        }


    }
}









