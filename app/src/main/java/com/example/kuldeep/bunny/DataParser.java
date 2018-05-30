package com.example.kuldeep.bunny;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Priyanka
 */

public class DataParser {
    private static final String TAG = "hello";
    Context c;
    String msg;
    public DataParser(Context c) {
        this.c = c;
    }

    public HashMap<String, String> getPlace(JSONObject googlePlaceJson) {
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = "--NA--";
        String vicinity = "--NA--";
        String latitude = "";
        String longitude = "";
        String reference = "";
        String photoURL= "R.drawable.img";
        String rating = "" ;


        Log.v("DataParser", "jsonobject =" + googlePlaceJson.toString());


        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }

            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }

            //if (!googlePlaceJson.isNull("photos")) {

            if (!googlePlaceJson.isNull("rating")) {
                rating = googlePlaceJson.getString("rating");
            }

           // photoURL = googlePlaceJson.getJSONObject("photos").getString("photo_reference");
            //rating = googlePlaceJson.getString("rating");
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");

            reference = googlePlaceJson.getString("reference");

            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);
            googlePlaceMap.put("rating",rating);
            Log.e("photoReferencelog",googlePlaceJson.toString());

            String[] array = {"a","b","c","d"};
            JSONArray jsonArray = new JSONArray(array);
            if ( !googlePlaceJson.getJSONArray("photos").toJSONObject(jsonArray).getJSONObject("a").isNull("photo_reference")){
               // Toast.makeText(c, "Hello, i m getting the photo reference", Toast.LENGTH_SHORT).show();
               // Log.e("photoReferencelog","shit");
                photoURL = googlePlaceJson.getJSONArray("photos").toJSONObject(jsonArray).getJSONObject("a").getString("photo_reference");
              //  Toast.makeText(c, "Photo Reference:\n"+photoURL, Toast.LENGTH_SHORT).show();
              //  Log.e("photoReferencelog",photoURL);
            }


            Log.e("photoReference",photoURL);
            //Log.e("photooos",googlePlaceJson.getJSONArray("photos").toJSONObject(jsonArray).getJSONObject("a").getString("photo_reference"));
          /* if ( googlePlaceJson.getJSONObject("photos")!= null ){
               Log.e("hii","byeeee");
                Log.e("photooos",googlePlaceJson.getJSONObject("photos").toString());
            }
            else Log.e("oops","ma d5alcch");*/
           googlePlaceMap.put("photoURL",photoURL);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;

    }

    public List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        int count = jsonArray.length();
        List<HashMap<String, String>> placelist = new ArrayList<>();
        HashMap<String, String> placeMap = null;
        //Toast.makeText(c, "getting places in DataPArser ...", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < count; i++) {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placelist.add(placeMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject test = new JSONObject();
        try {
            test.put("places", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("json-data-test ", test.toString());
        //postJSONObject("http://197.26.55.141:5901/savePlaces.php",test);

        Log.d(TAG, "" + test.toString());
        //Toast.makeText(c, "" + test.toString(), Toast.LENGTH_SHORT).show();
        //sendJsonMQTT(test);
        //receiveJSONObject();
        //Toast.makeText(c, "Hello\n"+placelist, Toast.LENGTH_SHORT).show();
        Log.e("linux",placelist.toString());
        return placelist;

    }

    public List<HashMap<String, String>> parse(String jsonData) {
        JSONArray jsonArray = null;
        JSONObject jsonObject;
       // Toast.makeText(c, "parsing...", Toast.LENGTH_SHORT).show();
        Log.d("json data", jsonData);
        //Toast.makeText(c, ""+jsonData, Toast.LENGTH_SHORT).show();
        Log.v("json data", jsonData);
        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }



}