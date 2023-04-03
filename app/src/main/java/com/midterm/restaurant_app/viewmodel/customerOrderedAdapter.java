package com.midterm.restaurant_app.viewmodel;

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
import com.midterm.restaurant_app.model.Account;
import java.util.List;

public class customerOrderedAdapter extends RecyclerView.Adapter<customerOrderedAdapter.ViewHolder>{
    private Context context;
    List<Account> customerItemList;

    public customerOrderedAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Account> items){
        this.customerItemList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_ordered,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Account item_ordered = customerItemList.get(position);
//        holder.tvNameCus.setText((String) item_ordered.getNameCus());
//        holder.tvTotalPaid.setText("123456666");
//        holder.dateOrdered.setText((String) item_ordered.getDateOrdered());
//        holder.llFoodItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("nameCustom", item_ordered.getNameCus().toString()+"'s");
//                Navigation.findNavController(view).navigate(R.id.action_hisOrderFragment_to_detailHisOrderFragment, bundle);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(customerItemList != null){
            return customerItemList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameCus;
        private TextView tvTotalPaid;
        private TextView dateOrdered;
        private LinearLayout llFoodItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCus = itemView.findViewById(R.id.tv_namecus);
            tvTotalPaid = itemView.findViewById(R.id.tv_totalPaid);
            dateOrdered = itemView.findViewById(R.id.tv_timeOrdered);
            llFoodItem = itemView.findViewById(R.id.ll_tableitem);
        }
    }
}
