package com.example.kuldeep.bunny;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceGroup;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.MenuAdapter;
import Models.PojoClass;

public class GetNearby extends AsyncTask<String, String, String> {
    Context context;
    Handler handler;
    ProgressBar progressBar;
    RecyclerView rv;
    TextView textWelcome;
    TextView textSearch;
    MenuAdapter menuAdapter;
    private ArrayList<PojoClass> pojoClassArrayList;

    List<HashMap<String, String>> nearbyPlaceList;

    public GetNearby(Context context, Handler handler ,ProgressBar progressBar,RecyclerView rv,MenuAdapter menuAdapter, TextView textWelcome
            ,TextView textSearch) {
        this.context = context;
        this.handler = handler;
        this.progressBar= progressBar;
        this.rv=rv;
        this.menuAdapter=menuAdapter;
        this.textSearch=textSearch;
        this.textWelcome=textWelcome;
    }

    @Override
    protected String doInBackground(String... objects) {
        String url = (String) objects[0];
        String googlePlacesData = "";
        DownloadURL downloadURL = new DownloadURL();
  //      Toast.makeText(context, "downloading url ...", Toast.LENGTH_SHORT).show();
        try {
            googlePlacesData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        DataParser parser = new DataParser(context);
        nearbyPlaceList = parser.parse(s);
        Log.d("nearbyplacesdata","called parse method");
        receiveJSONObject(nearbyPlaceList);
    }

    public List<HashMap<String, String>> getNearbyPlaceList() {
        return nearbyPlaceList;
    }


    public String[][] receiveJSONObject(List<HashMap<String, String>> nearbyPlaceList) {



        String msg="hello";
        progressBar.setVisibility(View.GONE);
        //Toast.makeText(context, "" + msg, Toast.LENGTH_LONG).show();




        pojoClassArrayList = new ArrayList<>();

        for(int i = 0; i < nearbyPlaceList.size(); i++){
            String photoURL="R.drawable.img";
            //photoURL="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4zIFE2vpdnB_b_qmWEa2NBKd_vFa_7f5XQpZUcyKs6vNNN85G";
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");

            if (googlePlace.get("photoURL") != photoURL ){
                photoURL="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+googlePlace.get("photoURL")+"&key=AIzaSyCxqeO6wvsCjouoZQBPvZHDaXAwXKKDWHw";
                Log.e("imageurl",photoURL);
            };
            String rat=googlePlace.get("rating");
            float rate=0;
            if (rat!="") {
                rate=Float.parseFloat(rat);
            }
            PojoClass pojoClass = new PojoClass();
            pojoClass.setText_name(googlePlace.get("place_name"));
            pojoClass.setImage(photoURL);
            pojoClass.setIndicate(R.drawable.newlogo);
            pojoClass.setImage_location(R.drawable.shape);
            pojoClass.setText_location(googlePlace.get("vicinity"));
            pojoClass.setText_menu1(googlePlace.get("rating"));
            pojoClass.setText_menu2("");
            pojoClass.setText_menu3("");
            pojoClass.setText_review("123");
            pojoClass.setRating(rate);

            pojoClassArrayList.add(pojoClass);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {


                menuAdapter = new MenuAdapter(context, pojoClassArrayList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                rv.setLayoutManager(mLayoutManager);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setAdapter(menuAdapter);
            }
        });
        //List<String> placesStrings = new ArrayList<>();





        return null;
    }

}
