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
                "",
                ""
//                "0.19 persen",
//                "Inflasi Oktober 2024 naik dibandingkan September 2024"
        ));
        indicatorList.add(new Indicator(
                R.drawable.ic_konstruksi,
                "Indeks Kemahalan Konstruksi/IKK",
                "",
                ""
        ));
        indicatorList.add(new Indicator(
                R.drawable.ic_petani,
                "Nilai Tukar Petani/NTP",
                "",
                ""
//                "113.54",
//                "Nilai Tukar Petani / NTP Oktober 2024 turun -0.23 % dibandingkan September 2024"
        ));
        indicatorList.add(new Indicator(
                R.drawable.ic_ekonomi,
                "Pertumbuhan Ekonomi",
                "",
                ""
        ));
        indicatorList.add(new Indicator(
                R.drawable.ic_kemiskinan,
                "Kemiskinan",
                "",
                ""
        ));
        indicatorList.add(new Indicator(
                R.drawable.ic_pengangguran,
                "Tingkat Pengangguran Terbuka/TPT",
                "",
                ""
        ));
        indicatorList.add(new Indicator(
                R.drawable.ic_pembangunan,
                "Indeks Pembangunan Manusia/IPM",
                "",
                ""
        ));
    }

    private List<String> getTableListByIndicator(String indicatorTitle) {
        List<String> tableList = new ArrayList<>();
        switch (indicatorTitle) {
            case "Inflasi":
                tableList.add("Inflasi 9 Kota");
                tableList.add("IHK 9 Kota");
                break;
            case "Indeks Kemahalan Konstruksi/IKK":
                tableList.add("IKK Kota Pekalongan");
            case "Nilai Tukar Petani/NTP":
                tableList.add("NTP Provinsi");
            case "Pertumbuhan Ekonomi":
                tableList.add("PDRB Kota Pekalongan");
                tableList.add("Distribusi LU");
                tableList.add("Distribusi E");
                tableList.add("LPE E");
                tableList.add("LPE LU");
            case "Kemiskinan":
                tableList.add("Kemiskinan Kota Pekalongan");
                tableList.add("Indeks Kedalaman Kemiskinan");
                tableList.add("Indeks Keparahan Kemiskinan");
                tableList.add("Distribusi Pengeluaran");
            case "Tingkat Pengangguran Terbuka/TPT":
                tableList.add("TPT");
                tableList.add("TPAK");
            case "Indeks Pembangunan Manusia/IPM":
                tableList.add("IPM Kota Pekalongan");
                tableList.add("IPM Sekitar");
        }
        return tableList;
    }
}

