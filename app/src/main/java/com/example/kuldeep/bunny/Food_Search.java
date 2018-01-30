package com.example.kuldeep.bunny;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import Adapter.ListAdapter;
import Models.PojoClass1;

public class Food_Search extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rv1;
    public ImageButton imageButton;
    public ImageView cancel_action;
    private ArrayList<PojoClass1> pojoClassArrayList1;
    private SeekBar chilliseekBar, seekBarkms;
    private String[] foodlayout = {"Maxican", "Panjabi", "Italiyan", "Gujarati"};
    private LinearLayout tagLinearLayout, popularLinearLayout, newLinearLayout, featuredLinearLayout;
    private TextView popularTextView, newTextView, featuredTextView, vegtextView, eggtextView, nonvegtextView, seekbartext;
    private ImageView imageView2, imageView3, imageView4;
    private ListAdapter listAdapter;
    private ArrayList<TextView> textViewArrayList = new ArrayList<>();
    private ArrayList<TextView> foodTextViewArrayList = new ArrayList<>();
    private ArrayList<ImageView> imageArralist = new ArrayList<>();
    boolean vegisselect = true;
    boolean nonvegisselect = true;
    boolean eggisselect = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__search);
        init();
        init1();

        chilliseekBar = new SeekBar(Food_Search.this);


        seekBarkms.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                seekbartext.setText(progress + "kms");
                seekbartext.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void init() {

        ImageButton btn = (ImageButton) findViewById(R.id.imageButton);
        tagLinearLayout = findViewById(R.id.taglayout);
        popularLinearLayout = findViewById(R.id.populatlayout);
        newLinearLayout = findViewById(R.id.newlayout);
        featuredLinearLayout = findViewById(R.id.featuredlayout);
        popularTextView = findViewById(R.id.textView3);
        newTextView = findViewById(R.id.textView4);
        seekbartext = findViewById(R.id.seekbartext);
        featuredTextView = findViewById(R.id.textView5);
        vegtextView = findViewById(R.id.textView6);
        eggtextView = findViewById(R.id.textView7);
        nonvegtextView = findViewById(R.id.textView8);
        textViewArrayList.add(popularTextView);
        textViewArrayList.add(newTextView);
        textViewArrayList.add(featuredTextView);
        chilliseekBar = findViewById(R.id.seekBar);
        seekBarkms = findViewById(R.id.seekBarkms);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);


        foodTextViewArrayList.add(vegtextView);
        foodTextViewArrayList.add(eggtextView);
        foodTextViewArrayList.add(nonvegtextView);


        imageArralist.add(imageView2);
        imageArralist.add(imageView3);
        imageArralist.add(imageView4);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent contact = new Intent(Food_Search.this, MainActivity.class);
                startActivity(contact);

            }
        });

        popularLinearLayout.setOnClickListener(this);
        newLinearLayout.setOnClickListener(this);
        featuredLinearLayout.setOnClickListener(this);
        vegtextView.setOnClickListener(this);
        eggtextView.setOnClickListener(this);
        nonvegtextView.setOnClickListener(this);
        pojoClassArrayList1 = new ArrayList<>();

        for (int i = 0; i < foodlayout.length; i++) {

            PojoClass1 pojoClass1 = new PojoClass1();
            pojoClass1.setFoodlayout(foodlayout[i]);

            pojoClassArrayList1.add(pojoClass1);
        }

        rv1 = (RecyclerView) findViewById(R.id.rv1);

        listAdapter = new ListAdapter(Food_Search.this, pojoClassArrayList1);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(Food_Search.this, LinearLayoutManager.HORIZONTAL, false);
        rv1.setLayoutManager(mLayoutManager1);
        rv1.setItemAnimator(new DefaultItemAnimator());
        rv1.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.populatlayout:

                changeBackground(0, popularLinearLayout);
                imageView2.setImageResource(R.drawable.firewhite);
                imageView3.setImageResource(R.drawable.time);
                imageView4.setImageResource(R.drawable.star0);
                break;
            case R.id.newlayout:

                changeBackground(1, newLinearLayout);
                imageView2.setImageResource(R.drawable.fire1);
                imageView3.setImageResource(R.drawable.clockwhite);
                imageView4.setImageResource(R.drawable.star0);

                break;
            case R.id.featuredlayout:

                changeBackground(2, featuredLinearLayout);
                imageView2.setImageResource(R.drawable.fire1);
                imageView3.setImageResource(R.drawable.time);
                imageView4.setImageResource(R.drawable.starwhite);

                break;
            case R.id.textView6:

                vegtextView.setBackgroundResource(R.drawable.round_colorgreen);
                vegtextView.setTextColor(Color.parseColor("#ffffff"));


                eggtextView.setBackgroundResource(R.drawable.roundtext1);
                eggtextView.setTextColor(Color.parseColor("#f5c56b"));

                nonvegtextView.setBackgroundResource(R.drawable.roundtext1);
                nonvegtextView.setTextColor(Color.parseColor("#E65100"));

                break;
            case R.id.textView7:

                vegtextView.setBackgroundResource(R.drawable.roundtext1);
                vegtextView.setTextColor(Color.parseColor("#7CB342"));


                eggtextView.setBackgroundResource(R.drawable.round_coloryello);
                eggtextView.setTextColor(Color.parseColor("#ffffff"));

                nonvegtextView.setBackgroundResource(R.drawable.roundtext1);
                nonvegtextView.setTextColor(Color.parseColor("#E65100"));

                break;
            case R.id.textView8:

                vegtextView.setBackgroundResource(R.drawable.roundtext1);
                vegtextView.setTextColor(Color.parseColor("#7CB342"));

                eggtextView.setBackgroundResource(R.drawable.roundtext1);
                eggtextView.setTextColor(Color.parseColor("#f5c56b"));

                nonvegtextView.setBackgroundResource(R.drawable.round_colorred);
                nonvegtextView.setTextColor(Color.parseColor("#ffffff"));


                break;

        }
    }

    private boolean changeBackgroundOnselect(boolean isselected, TextView foodlayout, String color1, String color2) {
        if (isselected) {
            isselected = false;
            foodlayout.setBackgroundResource(R.drawable.round_color);
            foodlayout.setTextColor(Color.parseColor(color1));
        } else if (!isselected) {
            isselected = true;
            foodlayout.setBackgroundResource(R.drawable.roundtext1);
            foodlayout.setTextColor(Color.parseColor(color2));

        }
        return isselected;
    }

    private void changeBackground(int pos, LinearLayout linearinearLayout) {
        for (int i = 0; i < tagLinearLayout.getChildCount(); i++) {
            if (i == pos) {
                Log.e("select", "select");
                linearinearLayout.setBackground(getResources().getDrawable(R.drawable.roundtext2));
                textViewArrayList.get(i).setTextColor(Color.parseColor("#ffffff"));
            } else {
                Log.e("here", "here");
                tagLinearLayout.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.roundtext1));
                textViewArrayList.get(i).setTextColor(Color.parseColor("#bcbec2"));
            }
        }
    }

    private void changeFoodTextcolor(int pos) {
        for (int i = 0; i < foodTextViewArrayList.size(); i++) {

        }


    }

    public void init1() {

        ImageView btn1 = (ImageView) findViewById(R.id.cancel_action);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent contact = new Intent(Food_Search.this, MainActivity.class);
                startActivity(contact);

            }
        });
    }


}
