package com.example.weatherapplication.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=29bce40c881884cdc533a68717231f29&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);
}
