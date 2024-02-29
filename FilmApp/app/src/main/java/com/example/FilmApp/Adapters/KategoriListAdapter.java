package com.example.FilmApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.FilmApp.Domian.GenresItem1;
import com.example.trend_cati.R;

import java.util.ArrayList;

public class KategoriListAdapter extends RecyclerView.Adapter<KategoriListAdapter.ViewHolder> {
    ArrayList<GenresItem1> ogeler;
    Context context;

    public KategoriListAdapter(ArrayList<GenresItem1> ogeler){
        this.ogeler=ogeler;
    }
    @NonNull
    @Override

    public KategoriListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_kategori,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriListAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(ogeler.get(position).getName());
        holder.itemView.setOnClickListener(v->{
        });
    }

    @Override
    public int getItemCount() {
        return ogeler.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.TitleTxt);
        }
    }
}
