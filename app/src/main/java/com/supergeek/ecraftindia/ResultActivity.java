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

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks {
    RecyclerView recyclerView;
    MainAdapter adapter;
    ArrayList<ModelClass> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new MainLoader(this,data,2,getIntent().getStringExtra("key"));
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        adapter.notifyDataSetChanged();
        Log.e("abcdrf",this.data.size()+"");
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
