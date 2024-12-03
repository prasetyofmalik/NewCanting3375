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
import com.example.newcanting3375.adapter.IndicatorAdapter;
import com.example.newcanting3375.model.Indicator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private IndicatorAdapter adapter;
    private List<Indicator> indicatorList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("New Canting 3375");
        }

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeData();
        adapter = new IndicatorAdapter(indicatorList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new IndicatorAdapter.OnItemClickListener() {
            @Override public void onItemClick(int position) {
                Indicator indicator = indicatorList.get(position);
                List<String> tableList = getTableListByIndicator(indicator.getTitle());

                TableListFragment tableListFragment = new TableListFragment();
                Bundle args = new Bundle();
                args.putStringArrayList("tableList", (ArrayList<String>) tableList);
                args.putString("indicatorTitle", indicator.getTitle()); // Pastikan ini diteruskan
                tableListFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, tableListFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void initializeData() {
        indicatorList = new ArrayList<>();
        indicatorList.add(new Indicator(
                R.drawable.ic_inflasi,
                "Inflasi",
                "0.19 persen",
                "Inflasi Oktober 2024 naik dibandingkan September 2024"
        ));
        indicatorList.add(new Indicator(
                R.drawable.ic_petani,
                "Nilai Tukar Petani / NTP",
                "113.54",
                "Nilai Tukar Petani / NTP Oktober 2024 turun -0.23 % dibandingkan September 2024"
        ));
        // Tambahkan data indikator lainnya di sini
    }

    private List<String> getTableListByIndicator(String indicatorTitle) {
        List<String> tableList = new ArrayList<>();
        switch (indicatorTitle) {
            case "Inflasi":
                tableList.add("Inflasi Jawa Tengah");
                tableList.add("Inflasi Menurut Kelompok");
                tableList.add("Komoditas Penyumbang Inflasi");
                tableList.add("Inflasi 9 Kota");
                tableList.add("Inflasi Ibu Kota Provinsi");
                break;
            case "Nilai Tukar Petani / NTP":
                tableList.add("NTP Provinsi");
                tableList.add("Komoditas Penyumbang NTP");
                tableList.add("NTP Provinsi di Pulau Jawa");
                tableList.add("NTUP");
                tableList.add("Series NTP dan NTUP");
        }
        return tableList;
    }
}

