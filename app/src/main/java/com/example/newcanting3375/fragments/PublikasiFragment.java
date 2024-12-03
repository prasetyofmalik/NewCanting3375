package com.example.newcanting3375.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newcanting3375.R;
import com.example.newcanting3375.adapter.PublicationAdapter;
import com.example.newcanting3375.api.ApiInterface;
import com.example.newcanting3375.model.Publication;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublikasiFragment extends Fragment {
    private RecyclerView recyclerView;
    private PublicationAdapter adapter;
    private List<Publication> publicationList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Publikasi Terbaru");
        }

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        publicationList = new ArrayList<>();
        adapter = new PublicationAdapter(publicationList);
        recyclerView.setAdapter(adapter);

        fetchPublications();

        return view;
    }

    private void fetchPublications() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://webapi.bps.go.id/v1/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        String apiUrl = "list/model/publication/lang/ind/domain/3375/key/0ec72fb7f38050b749edf6d3eb390e9a/";
        apiInterface.getPublications(apiUrl)
                .enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        JsonObject responseObject = response.body();
                        JsonArray publicationsArray = responseObject.getAsJsonArray("data").get(1).getAsJsonArray();
                        for (JsonElement publicationElement : publicationsArray) {
                            JsonObject publicationObject = publicationElement.getAsJsonObject();
                            Publication publication = new Gson().fromJson(publicationObject, Publication.class);
                            publicationList.add(publication);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    // Handle failure
                }
            });
    }
}