package com.midterm.restaurant_app.viewmodel.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentAccountBinding;
import com.midterm.restaurant_app.databinding.ItemOrderBinding;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.model.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{
    private List<Order> orderList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ItemOrderBinding bindingOrder;
    private HashMap<String, Table> tableHashMap;

    public AccountAdapter(Context context,HashMap<String, Table> tableHashMap) {
        layoutInflater = LayoutInflater.from(context);
        this.tableHashMap =tableHashMap;
    }

    public void setData(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        bindingOrder = ItemOrderBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(bindingOrder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.bindingOrder.setOrder(orderList.get(position));

        for (Map.Entry<String, Table> entry : tableHashMap.entrySet()) {
            String s = order.getIdTable();
            if(order.getIdTable().equals(entry.getKey())){
                holder.bindingOrder.tvNametable.setText(entry.getValue().getNameTable().toString());
            }
        }

        holder.bindingOrder.llTableitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("idOrder", order.getIdOrder().toString());
                Navigation.findNavController(view).navigate(R.id.action_serveFragment_to_detailsOrderFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemOrderBinding bindingOrder;

        public ViewHolder(@NonNull ItemOrderBinding binding) {
            super(binding.getRoot());
            this.bindingOrder = binding;
        }
    }
}
