package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kuldeep.bunny.R;

import java.util.ArrayList;

import Models.PojoClass1;

/**
 * Created by kuldeep on 23/01/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PojoClass1> pojoClassArrayList1;
    private boolean isselected;

    public ListAdapter(Context context, ArrayList<PojoClass1> pojoClassArrayList1) {
        this.context = context;
        this.pojoClassArrayList1 = pojoClassArrayList1;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {

        holder.foodlayout.setText(pojoClassArrayList1.get(position).getFoodlayout());


        pojoClassArrayList1.get(position).setIsselected(true);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isselcted = changeBackgroundOnselect(pojoClassArrayList1.get(position).isIsselected(), holder.foodlayout);
                pojoClassArrayList1.get(position).setIsselected(isselcted);

            }
        });

    }

    private boolean changeBackgroundOnselect(boolean isselected, TextView foodlayout) {
        if (isselected) {
            isselected = false;
            foodlayout.setBackgroundResource(R.drawable.round_color);
            foodlayout.setTextColor(Color.parseColor("#ffffff"));
        } else if (!isselected) {
            isselected = true;
            foodlayout.setBackgroundResource(R.drawable.roundtext1);
            foodlayout.setTextColor(Color.parseColor("#bcbec2"));

        }
        return isselected;
    }

    @Override
    public int getItemCount() {
        return pojoClassArrayList1.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodlayout;


        public ViewHolder(View view) {
            super(view);

            foodlayout = view.findViewById(R.id.foodlayout);
        }
    }
}
