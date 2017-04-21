package com.supergeek.ecraftindia;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
RecyclerView recyclerView;
    MainAdapter adapter;
    ArrayList<ModelClass> data;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress=(ProgressBar)findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        data=new ArrayList<>();
        adapter=new MainAdapter(this,data);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new Decoration(this,LinearLayoutManager.HORIZONTAL));
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
        else {progress.setVisibility(View.GONE);
            Toast.makeText(this, "Internet is not connected", Toast.LENGTH_SHORT).show();}

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new MainLoader(this,data,1,"");
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
            adapter.notifyDataSetChanged();
        progress.setVisibility(View.GONE);
        Log.e("abcdrf",this.data.size()+"");
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("key",query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
