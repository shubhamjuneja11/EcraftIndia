package com.supergeek.ecraftindia;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks {
    RecyclerView recyclerView;
    MainAdapter adapter;
    ArrayList<ModelClass> data;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        data=new ArrayList<>();
        adapter=new MainAdapter(this,data);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new Decoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        ConnectivityManager connectivity=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network=connectivity.getActiveNetworkInfo();
        if(network!=null&&network.isConnected())
        {
            try {
                LoaderManager loaderManager = getSupportLoaderManager();
                loaderManager.restartLoader(1, null, this).forceLoad();
            }
            catch (Exception e){}

        }
        else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Internet is not connected", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new MainLoader(this,data,2,getIntent().getStringExtra("key"));
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
