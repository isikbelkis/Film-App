package com.example.FilmApp.Aktiviteler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.FilmApp.Adapters.KategoriHerFilmListAdapter;
import com.example.FilmApp.Adapters.OyuncularListAdapter;
import com.example.FilmApp.Domian.FilmItem;
import com.example.trend_cati.R;
import com.google.gson.Gson;

public class DetayActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, filmDegerlendirmeTxt, filmZamanTxt, filmOyuncularTxt, filmOzetTxt;
    private int idFilm;
    private ImageView pic2, backImg;
    private RecyclerView.Adapter adapterOyuncularListesi,adapterKategori;
    private RecyclerView recyclerViewOyuncular, recyclerViewKategori;
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            try {
                Gson gson = new Gson();
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

                FilmItem item = gson.fromJson(response, FilmItem.class);
                // ImageView'e yükle
                Glide.with(DetayActivity.this)
                        .load(item.getPoster())
                        .into(pic2);
                // FilmItem verileri yerleştir
                titleTxt.setText(item.getTitle());
                filmDegerlendirmeTxt.setText(item.getImdbRating());
                filmZamanTxt.setText(item.getRuntime());
                filmOzetTxt.setText(item.getPlot());
                filmOyuncularTxt.setText(item.getActors());
                if (item.getImages() != null) {
                    adapterOyuncularListesi = new OyuncularListAdapter(item.getImages());
                    recyclerViewKategori.setAdapter(adapterOyuncularListesi);
                }
                if (item.getGenres() != null) {
                    adapterKategori = new KategoriHerFilmListAdapter(item.getGenres());
                    recyclerViewKategori.setAdapter(adapterKategori);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> progressBar.setVisibility(View.GONE));

        mRequestQueue.add(mStringRequest);
    }



    private void initView() {
        titleTxt=findViewById(R.id.filmIsimTxt);
        progressBar=findViewById(R.id.progressBarDetay);
        scrollView=findViewById(R.id.scrollView3);
        pic2=findViewById(R.id.picDetay);
        filmDegerlendirmeTxt=findViewById(R.id.filmYildiz);
        filmZamanTxt=findViewById(R.id.filmZaman);
        filmOzetTxt=findViewById(R.id.filmOzet);
        filmOyuncularTxt=findViewById(R.id.filmOyuncular);
        backImg=findViewById(R.id.backImg);
        recyclerViewKategori=findViewById(R.id.genreView);
        recyclerViewOyuncular=findViewById(R.id.imagesRecycler);
        recyclerViewOyuncular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewKategori.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    //geri tuşu tıkla
        backImg.setOnClickListener(v ->
        finish());
    }
}
