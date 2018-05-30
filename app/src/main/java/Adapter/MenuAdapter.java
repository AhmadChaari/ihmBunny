package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.kuldeep.bunny.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Models.LeftRoundedCornersBitmap;
import Models.PojoClass;

/**
 * Created by kuldeep on 23/01/18.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PojoClass> pojoClassArrayList;

    public MenuAdapter(Context context, ArrayList<PojoClass> pojoClassArrayList) {
        this.context = context;
        this.pojoClassArrayList = pojoClassArrayList;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        holder.text_name.setText(pojoClassArrayList.get(position).getText_name());
        holder.text_location.setText(pojoClassArrayList.get(position).getText_location());
        holder.text_menu1.setText(pojoClassArrayList.get(position).getText_menu1());
        holder.text_menu2.setText(pojoClassArrayList.get(position).getText_menu2());
        holder.text_menu3.setText(pojoClassArrayList.get(position).getText_menu3());
        holder.text_review.setText(pojoClassArrayList.get(position).getText_review());
        holder.indicate.setImageResource(pojoClassArrayList.get(position).getIndicate());
        holder.image_location.setImageResource(pojoClassArrayList.get(position).getImage_location());
        //Picasso.with(context).load(pojoClassArrayList.get(position).getImage()).transform(new LeftRoundedCornersBitmap()).into(holder.image);

        holder.ratingBar.setRating(pojoClassArrayList.get(position).getRating());
        //Picasso.with(context).load(pojoClassArrayList.get(position).getImage()).transform(new LeftRoundedCornersBitmap()).into(holder.image);

        if (pojoClassArrayList.get(position).getImage() != "R.drawable.img")
        {
            loadImgFromURL(pojoClassArrayList.get(position).getImage(),holder.image);
        }
        else {
            Picasso.with(context).load(R.drawable.img).transform(new LeftRoundedCornersBitmap()).into(holder.image);
        }

    }
    public void loadImgFromURL(String url,ImageView imgView){
        Picasso.with(context).load(url).placeholder(R.drawable.img).error(R.drawable.img).into(imgView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }


    @Override
    public int getItemCount()   {return pojoClassArrayList.size();}


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_name,text_location,text_menu1,text_menu2,text_menu3,text_review;
        ImageView image,image_location,indicate;
        RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);

            text_name =  view.findViewById(R.id.text_name);
            text_location =  view.findViewById(R.id.text_location);
            text_menu1 =  view.findViewById(R.id.text_menu1);
            text_menu2 =  view.findViewById(R.id.text_menu2);
            text_menu3 =  view.findViewById(R.id.text_menu3);
            text_review =  view.findViewById(R.id.text_review);
            image = view.findViewById(R.id.image);
            indicate = view.findViewById(R.id.indicate);
            image_location = view.findViewById(R.id.image_location);
            ratingBar = view.findViewById(R.id.myRatingBar);

        }
    }
}


