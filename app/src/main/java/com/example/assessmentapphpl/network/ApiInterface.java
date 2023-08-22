package com.example.assessmentapphpl.network;

import com.example.assessmentapphpl.model.UnlinkRegistryModel;
import com.example.assessmentapphpl.model.UserRegistrationModel;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiInterface {

    @POST("Registration")
    Call<String> registration(@Body UserRegistrationModel userRegistrationModel);

    @POST("UnlinkRegistry")
    Call<String> unlinkRegistry(@Body UnlinkRegistryModel unlinkRegistryModel);


}

