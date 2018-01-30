package com.example.kuldeep.bunny;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;

import Adapter.MenuAdapter;
import Models.PojoClass;

public class MainActivity extends AppCompatActivity {

    public ImageButton imageButton;
    private RecyclerView rv, rv1;
    private ArrayList<PojoClass> pojoClassArrayList;


    private String[] text_name = {"The Secret Kitchen", "Work On Fire", "Vibes - The Bistro", "Nosh Farmaiye"};
    private String[] text_location = {"MG Road", "Diwalipura", "sayajiganj", "Alkapuri"};
    private String[] text_menu1 = {"Panjabi", "Asian", "Italian", "Mughisi"};
    private String[] text_menu2 = {"Maxican", "Chinese", "Maxican", "Indian"};
    private String[] text_menu3 = {"Thai", "Thai", "Thai", "Thai"};
    private String[] text_review = {"(215)", "(415)", "(105)", "(232)"};
    private Integer[] image = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};
    private Integer[] indicate = {R.drawable.newlogo, R.drawable.fire, R.drawable.newlogo, R.drawable.fire};
    private Integer[] image_location = {R.drawable.shape, R.drawable.shape, R.drawable.shape, R.drawable.shape};

    private MenuAdapter menuAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }



    public void init() {

        ImageButton btn = (ImageButton) findViewById(R.id.imageButton);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent contact = new Intent(MainActivity.this, Food_Search.class);
                startActivity(contact);

            }
        });


        pojoClassArrayList = new ArrayList<>();

        for (int i = 0; i < text_name.length; i++) {

            PojoClass pojoClass = new PojoClass();
            pojoClass.setText_name(text_name[i]);
            pojoClass.setImage(image[i]);
            pojoClass.setIndicate(indicate[i]);
            pojoClass.setImage_location(image_location[i]);
            pojoClass.setText_location(text_location[i]);
            pojoClass.setText_menu1(text_menu1[i]);
            pojoClass.setText_menu2(text_menu2[i]);
            pojoClass.setText_menu3(text_menu3[i]);
            pojoClass.setText_review(text_review[i]);

            pojoClassArrayList.add(pojoClass);
        }

        rv = (RecyclerView) findViewById(R.id.rv);

        menuAdapter = new MenuAdapter(MainActivity.this, pojoClassArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(menuAdapter);
    }
}



