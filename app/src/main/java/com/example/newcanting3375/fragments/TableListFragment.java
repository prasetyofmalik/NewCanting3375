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
import com.example.newcanting3375.adapter.TableAdapter;

import java.util.List;

public class TableListFragment extends Fragment {
    private RecyclerView recyclerView;
    private TableAdapter adapter;
    private List<String> tableList;
    private String indicatorTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_list, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        indicatorTitle = getArguments().getString("indicatorTitle");

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(indicatorTitle);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tableList = getArguments().getStringArrayList("tableList");
        adapter = new TableAdapter(tableList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String tableTitle = tableList.get(position);
                String apiUrl = getApiUrlByTableTitle(tableTitle);

                TableDetailFragment tableDetailFragment = new TableDetailFragment();
                Bundle args = new Bundle();
                args.putString("tableTitle", tableTitle);
                args.putString("apiUrl", apiUrl);
                tableDetailFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, tableDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private String getApiUrlByTableTitle(String tableTitle) {
        String apiUrl;
        switch (tableTitle) {
            case "Inflasi 9 Kota":
                apiUrl = "https://webapi.bps.go.id/v1/api/list/model/data/lang/ind/domain/3375/var/398/key/0ec72fb7f38050b749edf6d3eb390e9a";
                break;
            // Tambahkan case lainnya untuk judul tabel lain dengan var yang berbeda
            default:
                apiUrl = ""; // Atur URL default atau kasus lainnya
                break;
        }
        return apiUrl;
    }
}