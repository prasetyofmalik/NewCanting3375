package com.example.newcanting3375.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newcanting3375.R;
import com.example.newcanting3375.model.Publication;

import java.util.List;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {
    private List<Publication> publicationList;

    public PublicationAdapter(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    @NonNull
    @Override
    public PublicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publication, parent, false);
        return new PublicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationViewHolder holder, int position) {
        Publication publication = publicationList.get(position);
        holder.title.setText(publication.getTitle());
        holder.date.setText(publication.getDate());
        Glide.with(holder.itemView.getContext())
                .load(publication.getCover())
                .into(holder.coverImage);
    }

    @Override
    public int getItemCount() {
        return publicationList.size();
    }

    public class PublicationViewHolder extends RecyclerView.ViewHolder {
        TextView title, date;
        ImageView coverImage;

        public PublicationViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.publication_title);
            date = itemView.findViewById(R.id.publication_date);
            coverImage = itemView.findViewById(R.id.cover_image);
        }
    }
}

