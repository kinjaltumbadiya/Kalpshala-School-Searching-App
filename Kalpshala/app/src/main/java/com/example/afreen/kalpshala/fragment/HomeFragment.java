package com.example.afreen.kalpshala.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.adapter.SchoolSecAdapter;
import com.example.afreen.kalpshala.databasehandler.SchoolGetSet;
import com.example.afreen.kalpshala.model.schoolSecModel;
import com.example.afreen.kalpshala.retrofit.ApiClient;
import com.example.afreen.kalpshala.retrofit.ApiInterfaceSchoolSec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

   /* SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
*/
   Context context;
    private List<SchoolGetSet> school;
    List<schoolSecModel> schoolprimarydata = new ArrayList<schoolSecModel>();
    EditText search;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private SchoolSecAdapter mAdapter;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        school = new ArrayList<>();

        search = (EditText)view. findViewById( R.id.editTextsearch);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
                mAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getSchoolPrimaryDetailFromServer();
        return view;
    }
    public  void getSchoolPrimaryDetailFromServer()
    {
        ApiInterfaceSchoolSec apiService =
                ApiClient.getClient().create(ApiInterfaceSchoolSec.class);
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        Call<List<schoolSecModel>> call = apiService.getTopRatedMovies();
            call.enqueue(new Callback<List<schoolSecModel>>() {
            public void onResponse(Call<List<schoolSecModel>> call, Response<List<schoolSecModel>> response) {
                int statusCode = response.code();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                String stringresponse=response.body().size()+"";

                for(int i=0;i<response.body().size();i++)
                {
                    schoolSecModel primaryshcooldata=new schoolSecModel();
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
                adapter=new SchoolSecAdapter(schoolprimarydata, R.layout.list_item_movie, getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                mAdapter = new SchoolSecAdapter(schoolprimarydata);

            }

            @Override
            public void onFailure(Call<List<schoolSecModel>> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

            }


        });
    }


}
