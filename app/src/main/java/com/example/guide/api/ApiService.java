package com.example.guide.api;

import com.example.guide.bases.Base;
import com.example.guide.hotels.Hotel;
import com.example.guide.sanatoriums.Sanatorium;
import com.example.guide.users.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("bases")
    Call<List<Base>> getBases();

    @GET("hotels")
    Call<List<Hotel>> getHotels();

    @GET("sanatoriums")
    Call<List<Sanatorium>> getSanatoriums();

    @GET("users")
    Call<List<User>> getUsers();
}
