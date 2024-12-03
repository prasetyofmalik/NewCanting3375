package com.example.newcanting3375.fragments;

import android.os.Build;
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
import com.example.newcanting3375.adapter.TableDetailAdapter;
import com.example.newcanting3375.api.ApiClient;
import com.example.newcanting3375.api.ApiInterface;
import com.example.newcanting3375.api.ApiResponse;
import com.example.newcanting3375.model.TableData;
import com.example.newcanting3375.model.TurvarData;
import com.example.newcanting3375.model.VervarData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableDetailFragment extends Fragment {
    private RecyclerView recyclerView;
    private TableDetailAdapter adapter;
    private List<TableData> tableDataList;
    private List<String> months;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_detail, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        String tableTitle = getArguments().getString("tableTitle");

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tableTitle);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tableDataList = new ArrayList<>();
        months = new ArrayList<>();
        adapter = new TableDetailAdapter(tableDataList, months);
        recyclerView.setAdapter(adapter);

        String apiUrl = getArguments().getString("apiUrl");
        if (!apiUrl.isEmpty()) {
            fetchTableData(apiUrl);
        } else {
            // Tampilkan pesan atau penanganan lainnya jika apiUrl kosong
        }

        return view;
    }

    private void fetchTableData(String apiUrl) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiService.getInflationData(apiUrl); // Pastikan metode getInflationData dapat menerima URL dinamis
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    parseTableData(apiResponse);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void parseTableData(ApiResponse apiResponse) {
        if (apiResponse != null && "available".equals(apiResponse.getDataAvailability())) {
            Map<String, Double> dataContent = apiResponse.getDatacontent();
            List<TableData> parsedData = new ArrayList<>();

            // Simpan daftar bulan
            for (TurvarData turvar : apiResponse.getTurvar()) {
                months.add(turvar.getLabel());
            }

            for (VervarData vervar : apiResponse.getVervar()) {
                List<Double> monthlyValues = new ArrayList<>();
                for (TurvarData turvar : apiResponse.getTurvar()) {
                    String key = vervar.getVal() + "398" + String.valueOf(turvar.getVal()) + "1240";
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        monthlyValues.add(dataContent.getOrDefault(key, 0.0));
                    }
                }
                parsedData.add(new TableData(vervar.getLabel(), monthlyValues));
            }

            tableDataList.clear();
            tableDataList.addAll(parsedData);
            adapter.notifyDataSetChanged();
        } else {
            // Tampilkan pesan atau penanganan lainnya jika data tidak tersedia
        }
    }
}