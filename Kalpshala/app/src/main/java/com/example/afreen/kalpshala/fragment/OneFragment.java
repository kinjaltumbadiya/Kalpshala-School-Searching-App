package com.example.afreen.kalpshala.fragment;

/**
 * Created by Afreen on 6/10/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.adapter.MoviesAdapter;
import com.example.afreen.kalpshala.databasehandler.SchoolGetSet;
import com.example.afreen.kalpshala.model.schoolPrimaryModel;
import com.example.afreen.kalpshala.retrofit.ApiClient;
import com.example.afreen.kalpshala.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OneFragment extends Fragment{

    Context context;
    private List<SchoolGetSet> school;
    List<schoolPrimaryModel> schoolprimarydata = new ArrayList<>();
    public EditText search;
    private List<String> list = new ArrayList<String>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public OneFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one, container, false);

       /* search = (EditText)view. findViewById( R.id.editTextsearch);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(context, "newText="+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
*/        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        school = new ArrayList<>();
        getSchoolPrimaryDetailFromServer();
        return view;
    }



    public  void getSchoolPrimaryDetailFromServer()
    {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        Call<List<schoolPrimaryModel>> call = apiService.getTopRatedMovies();
        call.enqueue(new Callback<List<schoolPrimaryModel>>() {
            public void onResponse(Call<List<schoolPrimaryModel>> call, Response<List<schoolPrimaryModel>> response) {
                int statusCode = response.code();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                String stringresponse=response.body().size()+"";

                for(int i=0;i<response.body().size();i++)
                {
                    schoolPrimaryModel primaryshcooldata=new schoolPrimaryModel();
                    primaryshcooldata.setId(response.body().get(i).getId());
                    primaryshcooldata.setName(response.body().get(i).getName());
                    primaryshcooldata.setAddress(response.body().get(i).getAddress());
                    primaryshcooldata.setCity(response.body().get(i).getCity());
                    primaryshcooldata.setPhone(response.body().get(i).getPhone());
                    primaryshcooldata.setMobile(response.body().get(i).getMobile());
                    primaryshcooldata.setWebsite(response.body().get(i).getWebsite());
                    primaryshcooldata.setPhoto(response.body().get(i).getPhoto());
                    schoolprimarydata.add(primaryshcooldata);

                    Log.e("id",response.body().get(i).getName());
                }
                adapter=new MoviesAdapter(schoolprimarydata, R.layout.list_item_movie, getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<schoolPrimaryModel>> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

            }

        });
    }

}