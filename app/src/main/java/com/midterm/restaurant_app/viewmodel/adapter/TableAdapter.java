package com.midterm.restaurant_app.viewmodel.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.Order;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private Context context;
    List<Order> orders;

    public TableAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Order> orders){
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);

//        holder.tvNameTable.setText(tables.getNameTable().toString());
//        if(tables.isStatus()){
//            holder.tvStatus.setText("Served");
//        }
//        else{
//            holder.tvStatus.setText("Serving");
//        }
//        holder.tvNumServed.setText(Integer.toString(tables.getNumServed()));
//        holder.tvTotalServe.setText(Integer.toString(tables.getTotalServe()));
//        holder.tvCostFoods.setText(tables.getTotalCostFood().toString());
//
//        holder.llTableItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("nameTable", tables.getNameTable().toString());
//                Navigation.findNavController(view).navigate(R.id.action_serveFragment_to_detailsOrderFragment, bundle);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(orders != null){
            return orders.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameTable;
        private TextView tvStatus;
        private TextView tvNumServed;
        private TextView tv_statusOrdered;
        private TextView tvCostFoods;
        private LinearLayout llTableItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameTable = itemView.findViewById(R.id.tv_nametable);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tv_statusOrdered = itemView.findViewById(R.id.tv_statusOrdered);
            tvCostFoods = itemView.findViewById(R.id.tv_totalcostfood);
            llTableItem = itemView.findViewById(R.id.ll_tableitem);
        }
    }

}