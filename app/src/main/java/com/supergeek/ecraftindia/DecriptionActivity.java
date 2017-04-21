package com.supergeek.ecraftindia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DecriptionActivity extends AppCompatActivity {
ImageView image;
    TextView name,price,desc;
    Intent intent;
    String  rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decription);
        rs=getResources().getString(R.string.Rs);
        image=(ImageView)findViewById(R.id.image);
        name=(TextView)findViewById(R.id.name);
        price=(TextView)findViewById(R.id.price);
        desc=(TextView)findViewById(R.id.desc);

        intent=getIntent();

        name.setText(intent.getStringExtra("name"));
        price.setText(String.valueOf(rs+" "+intent.getIntExtra("price",1)));
        desc.setText(intent.getStringExtra("desc"));
        Glide.with(this).load(intent.getStringExtra("image"))
                .thumbnail(0.1f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);

    }
}
