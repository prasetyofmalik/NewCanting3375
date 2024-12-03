package com.example.newcanting3375.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newcanting3375.R;
import com.example.newcanting3375.model.TableData;

import java.util.ArrayList;
import java.util.List;

public class TableDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private List<TableData> tableDataList;
    private List<String> months;

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView headerCity;
        public List<TextView> headerMonths;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerCity = itemView.findViewById(R.id.header_city);
            headerMonths = new ArrayList<>();
            headerMonths.add((TextView) itemView.findViewById(R.id.header_januari));
            headerMonths.add((TextView) itemView.findViewById(R.id.header_februari));
            headerMonths.add((TextView) itemView.findViewById(R.id.header_maret));
            headerMonths.add((TextView) itemView.findViewById(R.id.header_april));
            headerMonths.add((TextView) itemView.findViewById(R.id.header_mei));
            headerMonths.add((TextView) itemView.findViewById(R.id.header_juni));
            headerMonths.add((TextView) itemView.findViewById(R.id.header_juli));
            headerMonths.add((TextView) itemView.findViewById(R.id.header_agustus));
        }
    }

    public static class TableDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView city;
        public List<TextView> monthValues;

        public TableDetailViewHolder(View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city);
            monthValues = new ArrayList<>();
            monthValues.add((TextView) itemView.findViewById(R.id.januari));
            monthValues.add((TextView) itemView.findViewById(R.id.februari));
            monthValues.add((TextView) itemView.findViewById(R.id.maret));
            monthValues.add((TextView) itemView.findViewById(R.id.april));
            monthValues.add((TextView) itemView.findViewById(R.id.mei));
            monthValues.add((TextView) itemView.findViewById(R.id.juni));
            monthValues.add((TextView) itemView.findViewById(R.id.juli));
            monthValues.add((TextView) itemView.findViewById(R.id.agustus));
        }
    }

    public TableDetailAdapter(List<TableData> tableDataList, List<String> months) {
        this.tableDataList = tableDataList;
        this.months = months;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_data_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_data, parent, false);
            return new TableDetailViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_HEADER) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            // Set header data
            headerHolder.headerCity.setText("9 Kota SBH");
            for (int i = 0; i < months.size(); i++) {
                headerHolder.headerMonths.get(i).setText(months.get(i));
            }
        } else {
            TableDetailViewHolder itemHolder = (TableDetailViewHolder) holder;
            TableData tableData = tableDataList.get(position - 1); // Karena header ada di posisi 0
            itemHolder.city.setText(tableData.getCity());
            for (int i = 0; i < tableData.getMonthlyValues().size(); i++) {
                itemHolder.monthValues.get(i).setText(String.valueOf(tableData.getMonthlyValues().get(i)));
            }
        }
    }

    @Override
    public int getItemCount() {
        return tableDataList.size()+ 1; // +1 untuk header
    }
}