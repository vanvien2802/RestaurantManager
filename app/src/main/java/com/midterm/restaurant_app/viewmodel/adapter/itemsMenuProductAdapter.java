package com.midterm.restaurant_app.viewmodel.adapter;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemMenuProductsBinding;
import com.midterm.restaurant_app.databinding.ItemProductsBinding;
import com.midterm.restaurant_app.databinding.LayoutDialogAddFoodForTableBinding;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.view.ServeFragment;

import java.util.ArrayList;
import java.util.List;

public class itemsMenuProductAdapter extends RecyclerView.Adapter<itemsMenuProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> productItems;
    private ItemMenuProductsBinding binding;


    public itemsMenuProductAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Product> items){
        this.productItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products,parent,false);

        ItemMenuProductsBinding binding = ItemMenuProductsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Product productItem = productItems.get(position);
//        holder.tvTitle.setText(foodItem.getTitle());
//        holder.tvCost.setText((String) foodItem.getCost());
        holder.binding.setProduct(productItems.get(position));
    }

    @Override
    public int getItemCount() {
        if(productItems != null){
            return productItems.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemMenuProductsBinding binding;

        public ViewHolder(ItemMenuProductsBinding itembinding) {
            super(itembinding.getRoot());
            this.binding = itembinding;
        }
    }

}
