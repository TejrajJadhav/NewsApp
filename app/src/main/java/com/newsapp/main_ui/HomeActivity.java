package com.newsapp.main_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.newsapp.NewsApplication;
import com.newsapp.R;

import Entity.MainSource;
import adapter.SourceAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import util.Constants;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rv_source_list;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_home);

        rv_source_list = findViewById(R.id.rv_source_list);
        rv_source_list.hasFixedSize();
        rv_source_list.setLayoutManager(new LinearLayoutManager(this));

        initApi();
    }

    private void initApi() {
        compositeDisposable.add(NewsApplication.getApi().getSources(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MainSource>() {
                    @Override
                    public void accept(MainSource mainSource) throws Exception {
                        Log.e("mainSource1: ","= "+new Gson().toJson(mainSource));
                        Log.e("mainSource2: ","= "+new Gson().toJson(mainSource.sources));
                        initAdapter(mainSource);
                    }
                }));
    }

    private void initAdapter(final MainSource mainSources) {
        final SourceAdapter sourceAdapter = new SourceAdapter(this, mainSources.sources, new SourceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(HomeActivity.this, mainSources.sources.get(position).name+" clicked",Toast.LENGTH_SHORT).show();
            }
        });
        rv_source_list.setAdapter(sourceAdapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
