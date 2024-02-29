package com.example.FilmApp.Aktiviteler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;


import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.FilmApp.Adapters.KategoriListAdapter;
import com.example.FilmApp.Adapters.filmListAdapter;
import com.example.FilmApp.Adapters.kaydiriciAdaptorler;
import com.example.FilmApp.Domian.GenresItem1;
import com.example.FilmApp.Domian.ListFilm;
import com.example.FilmApp.Domian.kaydiriciOgeler;
import com.example.trend_cati.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapterEnIyiFilmler,adapterGelecekFilmler,adapterKategori;
private RecyclerView recyclerViewEnIyiFilmler,recyclerViewGelecekFilmler,recyclerViewKategori;
private RequestQueue mrequestQueue;
private StringRequest mstringRequest, mstringRequest2, mstringRequest3;
private ProgressBar loading1, loading2,loading3;
private ViewPager2 viewPager2;
private Handler slaytHander=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        banners();
        sendRequestEnIyiFilmler();
        sendRequestGelecekFilmler();
        sendRequestKategori();
    }

    private void sendRequestEnIyiFilmler() {
        mrequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mstringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            try {
                //Gsonu json formatından Javaya
                Gson gson = new Gson();
                loading1.setVisibility(View.GONE);
                ListFilm ogeler = gson.fromJson(response, ListFilm.class);
                adapterEnIyiFilmler = new filmListAdapter(ogeler);
                //recyclerView'a oluşturulan adaptörü atar ve verilerin görüntülenmesini sağlar.
                recyclerViewEnIyiFilmler.setAdapter(adapterEnIyiFilmler);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }, error -> {
            loading1.setVisibility(View.GONE);
            Log.i("UILOver", "onErrorResponse" + error.toString());
        });
        mrequestQueue.add(mstringRequest);
    }

    private void sendRequestGelecekFilmler() {
        mrequestQueue = Volley.newRequestQueue(this);
        loading3.setVisibility(View.VISIBLE);
        mstringRequest3 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", response -> {
            try {
                Gson gson = new Gson();
                loading3.setVisibility(View.GONE);
                ListFilm ogeler = gson.fromJson(response, ListFilm.class);
                adapterGelecekFilmler = new filmListAdapter(ogeler);
                recyclerViewGelecekFilmler.setAdapter(adapterGelecekFilmler);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }, error -> {
            loading3.setVisibility(View.GONE);
            Log.i("UILOver", "onErrorResponse" + error.toString());
        });
        mrequestQueue.add(mstringRequest3);
    }

    private void sendRequestKategori() {
        // Gsonu json formatından Javaya dönüştürmek öğrenn
        try {
            mrequestQueue = Volley.newRequestQueue(this);
            loading2.setVisibility(View.VISIBLE);
            mstringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres", response -> {
                //gson.fromJson(response, new TypeToken<ArrayList<GenresItem1>>() {}.getType()) ifadesi, JSON yanıtını ArrayList<GenresItem1> türündeki bir Java nesnesine dönüştürür ve
                // bu nesneyi catList değişkenine atar.
                // Bu sayede, JSON yanıtındaki verileri daha kolay bir şekilde kullanabilirsiniz.
                try {
                    Gson gson = new Gson();
                    loading2.setVisibility(View.GONE);
                    ArrayList<GenresItem1> catList = gson.fromJson(response, new TypeToken<ArrayList<GenresItem1>>() {
                    }.getType());
                    adapterKategori = new KategoriListAdapter(catList);
                    recyclerViewKategori.setAdapter(adapterKategori);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, error -> {
                try {
                    loading2.setVisibility(View.GONE);
                    Log.i("UILOver", "onErrorResponse" + error.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            mrequestQueue.add(mstringRequest2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void banners() {
        List<com.example.FilmApp.Domian.kaydiriciOgeler> kaydiriciOgelers=new ArrayList<kaydiriciOgeler>();
        kaydiriciOgelers.add(new com.example.FilmApp.Domian.kaydiriciOgeler(R.drawable.wide));
        kaydiriciOgelers.add(new com.example.FilmApp.Domian.kaydiriciOgeler(R.drawable.wide4));
        kaydiriciOgelers.add(new com.example.FilmApp.Domian.kaydiriciOgeler(R.drawable.wide1));
        kaydiriciOgelers.add(new com.example.FilmApp.Domian.kaydiriciOgeler(R.drawable.wide3));
        kaydiriciOgelers.add(new com.example.FilmApp.Domian.kaydiriciOgeler(R.drawable.wide5));

    viewPager2.setAdapter(new kaydiriciAdaptorler(kaydiriciOgelers,viewPager2));
    //kenar boslukları
    viewPager2.setClipToPadding(false);
    viewPager2.setClipChildren(false);
    //önceden yüklenen sayfa sayısı
    viewPager2.setOffscreenPageLimit(3);
    //aşırı kaydırma
    viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//şimdi viewPger nesnelerini özelleştirmek için Cpt kullan

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        //kenar boşluğu
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            //konum ölçek
            //y ekseninde konumunuda yaz
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r+0.15f);
            }
        });
        //sayfa değişikliğini özelleştir
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //durdur
                slaytHander.removeCallbacks(slaytRunnable);
            }
        });
    }
    //run() fotolar arası geçiş
    private Runnable slaytRunnable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
//iptal
    @Override
    protected void onPause() {
        super.onPause();
        slaytHander.removeCallbacks(slaytRunnable);
    }
  //otomatik
    @Override
    protected void onResume() {
        super.onResume();
        slaytHander.postDelayed(slaytRunnable,2000);
    }

    private void initView() {
    //başlat
        viewPager2=findViewById(R.id.viewPagerSlayt);
        recyclerViewEnIyiFilmler=findViewById(R.id.view1);
        recyclerViewEnIyiFilmler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewGelecekFilmler=findViewById(R.id.view3);
        recyclerViewGelecekFilmler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewKategori=findViewById(R.id.view2);
        recyclerViewKategori.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        loading1=findViewById(R.id.progressBar1);
        loading2=findViewById(R.id.progressBar2);
        loading3=findViewById(R.id.progressBar3);
    }
}