package com.supergeek.ecraftindia;


import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Junejas on 4/21/2017.
 */

public class MainLoader extends AsyncTaskLoader<ModelClass> {
    ArrayList<ModelClass> data;
    String response,url="http://geekyboy.16mb.com/getitems.php";
    String item;
    int decide;
    public MainLoader(Context context, ArrayList<ModelClass> data,int decide,String item) {
        super(context);
        this.data=data;
        this.decide=decide;
        this.item=item;
    }

    @Override
    public ModelClass loadInBackground() {
        try {
            URL url1 = new URL(url);
            InputStream inputstream;
            HttpURLConnection connection;
            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(10000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if(decide==2) {
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("item",item);

                String query = builder.build().getEncodedQuery();

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
            }

            connection.connect();

            inputstream = connection.getInputStream();
            response = readfromstream(inputstream);
            getdata();
            Log.e("abcd",response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  String readfromstream(InputStream inputstream) {
        Log.e("abcd", "103");
        StringBuilder string = new StringBuilder();
        if (inputstream != null) {
            InputStreamReader inputreader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputreader);
            String line = null;
            try {
                line = reader.readLine();
                while (line != null) {
                    string.append(line);
                    line = reader.readLine();
                }

                Log.e("abcd", "200");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("abcd", "201");
            }

        }
        return string.toString();

    }
    public void getdata(){
        try {String name,image,desc;
            int price;
            JSONObject object=new JSONObject(response);
            JSONArray array=object.getJSONArray("item");
            for(int i=0;i<array.length();i++){
                JSONObject object1=array.getJSONObject(i);
                name=object1.getString("name");
                image=object1.getString("image");
		image = image.replace("\\","");
                price=object1.getInt("price");
                desc=object1.getString("desc");
                data.add(new ModelClass(name,price,image,desc));
                Log.e("abcde",image);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
