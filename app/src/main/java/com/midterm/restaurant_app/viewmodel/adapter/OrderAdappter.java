package com.midterm.restaurant_app.viewmodel.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemCustomerOrderedBinding;
import com.midterm.restaurant_app.databinding.ItemProductsBinding;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.model.Product;

import java.util.List;

public class OrderAdappter extends RecyclerView.Adapter<OrderAdappter.ViewHolder>{
    private Context context;
    private List<Order> orderItems;
    private ItemCustomerOrderedBinding binding;

    public OrderAdappter(Context context) {
        this.context = context;
    }
    public void setData(List<Order> items ){
        this.orderItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_customer_ordered,
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdappter.ViewHolder holder, int position) {
        holder.binding.setOrder(orderItems.get(position));
    }

    @Override
    public int getItemCount() {
        if(orderItems != null){
            return orderItems.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ItemCustomerOrderedBinding binding;

        public ViewHolder(ItemCustomerOrderedBinding itembinding) {
            super(itembinding.getRoot());
            this.binding = itembinding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Order order = orderItems.get(getAdapterPosition());
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("Order", order);
                    Navigation.findNavController(v).navigate(R.id.detailHisOrderFragment);
                }
            });
        }



    }
}
