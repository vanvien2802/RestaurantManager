package com.midterm.restaurant_app.viewmodel;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.FoodItem;

import java.util.List;

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.ViewHolder> {

    private Context context;
    List<FoodItem> foodItems;

    public FoodOrderAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<FoodItem> items){
        this.foodItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_food,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.tvTitle.setText(foodItem.getTitle());
        holder.tvCostFood.setText((String) foodItem.getCost());
        holder.tvDescrip.setText(foodItem.getCost());
        if(foodItem.isStatus()){
            holder.tvStatusServe.setText("Done");
        }
        else{
            holder.tvStatusServe.setText("Not done");
        }
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
        private TextView tvCostFood;
        private TextView tvStatusServe;
        private TextView tvDescrip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_nameFood);
            tvCostFood = itemView.findViewById(R.id.tv_costfood);
            tvStatusServe = itemView.findViewById(R.id.tv_statusserve);
            tvDescrip = itemView.findViewById(R.id.tv_details);
        }
    }

}
