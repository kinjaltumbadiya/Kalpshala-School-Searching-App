package com.example.afreen.kalpshala.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.adapter.CardAdapter;
import com.example.afreen.kalpshala.databasehandler.AppConfig;
import com.example.afreen.kalpshala.databasehandler.SchoolGetSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afreen on 6/10/2017.
 */
public class SchoolFragment extends Fragment {

    Context context;
    private List<SchoolGetSet> school;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Initializing our superheroes list
        school = new ArrayList<>();

        //Calling method to get data
        getData();


        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    //This method will get data from the web api
    private void getData(){
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(context,"Loading Data", "Please wait...",false,false);

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(AppConfig.URL_school,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing progress dialog
                        loading.dismiss();

                        //calling method to parse json array
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            SchoolGetSet superHero = new SchoolGetSet();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
           //     superHero.setImageUrl("http://hit-the-pic.000webhostapp.com/Images/"+json.getString(AppConfig.TAG_IMAGE_URL));
                //   Toast.makeText(getApplicationContext(),"image:"+json.getString(config.TAG_IMAGE_URL),Toast.LENGTH_LONG).show();
             //   superHero.setName(json.getString(AppConfig.TAG_NAME));
               /* superHero.setRank(json.getInt(config.TAG_RANK));
                superHero.setRealName(json.getString(config.TAG_REAL_NAME));
                superHero.setCreatedBy(json.getString(config.TAG_CREATED_BY));
                superHero.setFirstAppearance(json.getString(config.TAG_FIRST_APPEARANCE));*/

              /*  ArrayList<String> powers = new ArrayList<String>();

                JSONArray jsonArray = json.getJSONArray(config.TAG_POWERS);

                for(int j = 0; j<jsonArray.length(); j++){
                    powers.add(((String) jsonArray.get(j))+"\n");
                }
                superHero.setPowers(powers);*/


            } catch (JSONException e) {
                e.printStackTrace();
            }
            school.add(superHero);
        }

        //Finally initializing our adapter
        adapter = new CardAdapter(school, context);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

}
