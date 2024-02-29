package com.example.FilmApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.FilmApp.Domian.kaydiriciOgeler;
import com.example.trend_cati.R;

import java.util.List;
//Liste oluştur viewpager tanımla görünüm sınıfına extends et otomatik oluşcak metotlar
public class kaydiriciAdaptorler extends RecyclerView.Adapter<kaydiriciAdaptorler.KaydiriciGorunumTutucusu>{
private List<com.example.FilmApp.Domian.kaydiriciOgeler> KaydiriciOgeler;
private ViewPager2 viewPager2;
private Context context;

    public kaydiriciAdaptorler(List<kaydiriciOgeler> kaydiriciOgeler, ViewPager2 viewPager2) {
        KaydiriciOgeler = kaydiriciOgeler;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    //XML dosyasından bir görünüm öğesi oluşturarak ve bu görünümü içeren bir KaydiriciGorunumTutucusu örneği döndürerek,
    // RecyclerView'nin her bir öğesini temsil eden bir görünüm tutucusu oluşturur
    public kaydiriciAdaptorler.KaydiriciGorunumTutucusu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new KaydiriciGorunumTutucusu(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_slayt_ogesi,parent,false
        ));
    }

    @Override
    //KaydiriciOgeler listesindeki belirli bir konumdaki öğeyi görünüm tutucusuna bağlar.
    // Bu, görünüm tutucusunda bulunan ImageView'e resim yüklemek için kullanılır.
    public void onBindViewHolder(@NonNull kaydiriciAdaptorler.KaydiriciGorunumTutucusu holder, int position) {
        holder.setImageView(KaydiriciOgeler.get(position));
        if (position==KaydiriciOgeler.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {

        return KaydiriciOgeler.size();
    }

    public class KaydiriciGorunumTutucusu extends RecyclerView.ViewHolder {
        //Bu kod parçası, RecyclerView'nin görünüm tutucusunu temsil eden KaydiriciGorunumTutucusu sınıfını tanımlar.
        // Bu sınıf, görünüm tutucusunda bulunan ImageView'e resim yüklemek için Glide kütüphanesini kullanır.
        // Ayrıca, belirli bir işlemi gerçekleştirmek için runnable değişkenini kullanır.
        private ImageView imageView;
        public KaydiriciGorunumTutucusu(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.resimSlayt);
        }
        void setImageView(kaydiriciOgeler KaydiriciOgeler){
            RequestOptions requestOptions=new RequestOptions();
            requestOptions=requestOptions.transforms(new CenterCrop(),new RoundedCorners(60));

            Glide.with(context)
                    .load(KaydiriciOgeler.getResim())
                    .apply(requestOptions)
                    .into(imageView);
            }
        }
        private Runnable runnable=new Runnable() {
        //döngü
            @Override
            public void run() {
                KaydiriciOgeler.addAll(KaydiriciOgeler);
                notifyDataSetChanged();

            }
        };
    }



