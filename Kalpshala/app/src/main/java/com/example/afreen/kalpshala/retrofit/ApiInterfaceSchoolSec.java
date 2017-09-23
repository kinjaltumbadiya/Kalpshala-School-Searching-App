package com.example.afreen.kalpshala.retrofit;

import com.example.afreen.kalpshala.model.schoolSecModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Afreen on 6/14/2017.
 */
public interface ApiInterfaceSchoolSec {
    @GET("schoolSec.php")
    Call<List<schoolSecModel>> getTopRatedMovies();
}

