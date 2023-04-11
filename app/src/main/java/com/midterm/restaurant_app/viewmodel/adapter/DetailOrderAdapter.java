package com.midterm.restaurant_app.viewmodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemDetailsProductBinding;
import com.midterm.restaurant_app.model.DetailOrder;

import java.util.List;

public class DetailOrderAdapter extends RecyclerView.Adapter<DetailOrderAdapter.ViewHolder>{
    private Context context;
    private List<DetailOrder> detailsOrderItems;
    ItemDetailsProductBinding binding;

    public DetailOrderAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<DetailOrder> items ){
        this.detailsOrderItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_details_product,
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOrderAdapter.ViewHolder holder, int position) {
        holder.binding.setDetailOrder(detailsOrderItems.get(position));
    }

    @Override
    public int getItemCount() {
        if(detailsOrderItems != null){
            return detailsOrderItems.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ItemDetailsProductBinding binding;

        public ViewHolder(ItemDetailsProductBinding itembinding) {
            super(itembinding.getRoot());
            this.binding = itembinding;
        }
    }
}
