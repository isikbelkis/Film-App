package com.example.FilmApp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.FilmApp.Aktiviteler.DetayActivity;
import com.example.FilmApp.Domian.ListFilm;
import com.example.trend_cati.R;

public class filmListAdapter extends RecyclerView.Adapter<com.example.FilmApp.Adapters.filmListAdapter.ViewHolder> {
    ListFilm ogeler;
    Context context;

    public filmListAdapter(ListFilm ogeler){
        this.ogeler=ogeler;
    }
    @NonNull
    @Override
    public filmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull filmListAdapter.ViewHolder holder, int position) {
        holder.baslikTxt.setText(ogeler.getData().get(position).getTitle());
        RequestOptions requestOptions=new RequestOptions();
        //köşeleri yuvarla
        requestOptions=requestOptions.transform(new CenterCrop(),new RoundedCorners(30));

        Glide.with(context)
                .load(ogeler.getData().get(position).getPoster())
                .apply(requestOptions)
                .into(holder.resim);

        holder.itemView.setOnClickListener(v->{
            //detaya geçiş
                Intent intent = new Intent(holder.itemView.getContext(), DetayActivity.class);
                //kimlik
                intent.putExtra("id",ogeler.getData().get(position).getId());
                context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return ogeler.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView baslikTxt;
        ImageView resim;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            baslikTxt=itemView.findViewById(R.id.baslikTxt);
            resim=itemView.findViewById(R.id.resim);
        }
    }
}
