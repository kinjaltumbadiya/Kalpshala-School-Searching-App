package com.example.afreen.kalpshala.retrofit;

import com.example.afreen.kalpshala.model.schoolPrimaryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("schoolPrimary.php")
    Call<List<schoolPrimaryModel>> getTopRatedMovies();

}
