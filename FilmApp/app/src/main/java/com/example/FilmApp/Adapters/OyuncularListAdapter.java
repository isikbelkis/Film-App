package com.example.FilmApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trend_cati.R;

import java.util.List;

public class OyuncularListAdapter extends RecyclerView.Adapter<OyuncularListAdapter.ViewHolder> {
List<String> images;
Context context;

    public OyuncularListAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public OyuncularListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_oyuncular,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull OyuncularListAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(images.get(position))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.itemImages);
        }
    }
}
