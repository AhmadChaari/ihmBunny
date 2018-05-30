package com.example.kuldeep.bunny;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.MenuAdapter;
import Models.PojoClass;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnClickListener {

    public ImageButton imageButton;
    LocationListener locationListener;

    private RecyclerView rv, rv1;
    private ArrayList<PojoClass> pojoClassArrayList;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;

    private String[] text_name = {"The Secret Kitchen", "Work On Fire", "Vibes - The Bistro", "Nosh Farmaiye", "helloooo"};
    private String[] text_location = {"MG Road", "Diwalipura", "sayajiganj", "Alkapuri", "Nosh Farmaiye"};
    private String[] text_menu1 = {"Panjabi", "Asian", "Italian", "Mughisi", "Nosh Farmaiye"};
    private String[] text_menu2 = {"Maxican", "Chinese", "Maxican", "Indian", "Nosh Farmaiye"};
    private String[] text_menu3 = {"Thai", "Thai", "Thai", "Thai", "Nosh Farmaiye"};
    private String[] text_review = {"(215)", "(415)", "(105)", "(232)", "543"};
    private Integer[] image = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};
    private Integer[] indicate = {R.drawable.newlogo, R.drawable.fire, R.drawable.newlogo, R.drawable.fire, R.drawable.fire};
    private Integer[] image_location = {R.drawable.shape, R.drawable.shape, R.drawable.shape, R.drawable.shape, R.drawable.shape};
    String[][] places;
     MenuAdapter menuAdapter;
    Handler handler;
    ImageView imageBTN;
    ProgressBar progressBar;
    List<HashMap<String, String>> nearbyPlaceList;
    TextView textWelcome;
    TextView textSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageBTN = (ImageView) findViewById(R.id.imageBTN);
        imageBTN.setOnClickListener(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textWelcome = (TextView) findViewById(R.id.textWelcome);
        textSearch = (TextView) findViewById(R.id.textKeyword);

        progressBar.setVisibility(View.GONE);





        DataParser dd = new DataParser(this);
        handler = new Handler();
        //places = receiveJSONObject();
        //init();
        //places = getPlaces("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=34.742575,%2010.755711&radius=10&key=AIzaSyDKX70dembrzwdhqNOeUyS5fvngAMFS_a4&place_id=ChIJude879XSARMReCS-f8xTj_Y");

        ImageButton btn = (ImageButton) findViewById(R.id.imageButton);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent contact = new Intent(MainActivity.this, Food_Search.class);
                startActivity(contact);

            }
        });

    }





    private String[][] getPlaces(String url) {
        //Toast.makeText(this, "getting places...", Toast.LENGTH_SHORT).show();
        Object dataTransfer[] = new Object[1];
        dataTransfer[0]=url;
        GetNearby getNearby= new GetNearby(this,handler,progressBar,rv,menuAdapter,textWelcome,textSearch);
        getNearby.execute(url);



        return null;
        // return receiveJSONObject();
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + latitude + "," + longitude);
        googlePlaceUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlaceUrl.append("&keyword=" + nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + "AIzaSyCugx9gfu2dh7LjdRi6fjC4IWDW9rCAZOM");

        Log.d("MapsActivity", "url = " + googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

       /* latitude = location.getLatitude();
        longitude = location.getLongitude();*/

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onClick(View view) {
        if (view == imageBTN){
            textSearch.setVisibility(view.GONE);
            textWelcome.setVisibility(view.GONE);
            progressBar.setVisibility(View.VISIBLE);

            EditText editText = (EditText) findViewById(R.id.editText_19);

            String keyword = editText.getText().toString();
           // Toast.makeText(this, "u pressed this", Toast.LENGTH_SHORT).show();


            /*

            LocationManager locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
             LocationListener locationListener= new LocationListener() {
               @Override
               public void onLocationChanged(Location location) {


               }
           };
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling

                return;
            }


            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();

             */


            latitude=34.758272;
            longitude=10.743396;
            //Toast.makeText(this, ""+keyword+latitude+longitude, Toast.LENGTH_SHORT).show();
            String url = getUrl(latitude,longitude,keyword);
            //Toast.makeText(this, ""+url, Toast.LENGTH_SHORT).show();
            getPlaces(url);

        }
    }
    public void loadImgFromURL(String url,ImageView imgView){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imgView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }

    public String[][] receiveJSONObject(List<HashMap<String, String>> nearbyPlaceList) {



        String msg="hello";
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_LONG).show();




        pojoClassArrayList = new ArrayList<>();

        for(int i = 0; i < nearbyPlaceList.size(); i++){
            String photoURL="R.drawable.img";

            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");

            if (places[i][5]!= photoURL){
                photoURL="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+places[i][5]+"&key=AIzaSyCxqeO6wvsCjouoZQBPvZHDaXAwXKKDWHw";

            };

            PojoClass pojoClass = new PojoClass();
            pojoClass.setText_name(googlePlace.get("place_name"));
            pojoClass.setImage(googlePlace.get("photoURL"));
            pojoClass.setIndicate(R.drawable.newlogo);
            pojoClass.setImage_location(R.drawable.shape);
            pojoClass.setText_location(googlePlace.get("vicinity"));
            pojoClass.setText_menu1(googlePlace.get("rating"));
            pojoClass.setText_menu2("");
            pojoClass.setText_menu3("");
            pojoClass.setText_review("123");
            pojoClass.setRating(Float.parseFloat(googlePlace.get("rating")));

            pojoClassArrayList.add(pojoClass);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                rv = (RecyclerView) findViewById(R.id.rv);

                menuAdapter = new MenuAdapter(MainActivity.this, pojoClassArrayList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
                rv.setLayoutManager(mLayoutManager);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setAdapter(menuAdapter);
            }
        });
        //List<String> placesStrings = new ArrayList<>();





        return places;
    }

}
