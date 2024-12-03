package com.example.newcanting3375.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newcanting3375.R;
import com.example.newcanting3375.model.Indicator;

import java.util.List;

public class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.IndicatorViewHolder> {
    private List<Indicator> indicatorList;
    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class IndicatorViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView stat;
        public TextView desc;

        public IndicatorViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.indicator_icon);
            title = itemView.findViewById(R.id.indicator_title);
            stat = itemView.findViewById(R.id.indicator_stat);
            desc = itemView.findViewById(R.id.indicator_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public IndicatorAdapter(List<Indicator> indicatorList) {
        this.indicatorList = indicatorList;
    }

    @Override
    public IndicatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_indicator, parent, false);
        return new IndicatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IndicatorViewHolder holder, int position) {
        Indicator indicator = indicatorList.get(position);
        holder.icon.setImageResource(indicator.getIcon());
        holder.title.setText(indicator.getTitle());
        holder.stat.setText(indicator.getStat());
        holder.desc.setText(indicator.getDesc());
    }

    @Override
    public int getItemCount() {
        return indicatorList.size();
    }
}

