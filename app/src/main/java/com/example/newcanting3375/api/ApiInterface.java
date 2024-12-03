package com.example.newcanting3375.api;

import com.example.newcanting3375.model.TableData;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET
    Call<ApiResponse> getInflationData(@Url String url);

    @GET
    Call<JsonObject> getPublications(@Url String url);
}

