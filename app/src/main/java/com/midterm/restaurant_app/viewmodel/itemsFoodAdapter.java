package com.midterm.restaurant_app.viewmodel;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.foodItem;

import java.util.List;

public class itemsFoodAdapter extends RecyclerView.Adapter<itemsFoodAdapter.ViewHolder> {

    private Context context;
    List<foodItem> foodItems;

    public itemsFoodAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<foodItem> items){
        this.foodItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        foodItem foodItem = foodItems.get(position);
        holder.tvTitle.setText(foodItem.getTitle());
        holder.tvCost.setText((String) foodItem.getCost());
    }

    @Override
    public int getItemCount() {
        if(foodItems != null){
            return foodItems.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCost = itemView.findViewById(R.id.tv_cost);
        }
    }

}