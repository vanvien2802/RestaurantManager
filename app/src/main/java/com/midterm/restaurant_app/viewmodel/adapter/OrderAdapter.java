package com.midterm.restaurant_app.viewmodel.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemOrderBinding;
import com.midterm.restaurant_app.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Order> orderList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ItemOrderBinding bindingOrder;

    public OrderAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    public void getOrdersFromFirebase() {
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("Order");
        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                    Order order = orderSnapshot.getValue(Order.class);
                    orderList.add(order);
                }
                setData(orderList);
                for (Category category : categories) {
                    DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference("Products")
                            .orderByChild("category_id").equalTo(category.getId());
                    productsRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Lấy danh sách các sản phẩm
                            List<Product> products = new ArrayList<>();
                            for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                                Product product = productSnapshot.getValue(Product.class);
                                products.add(product);
                            }

                            // Hiển thị danh sách các sản phẩm lên giao diện
                            // ...
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Xử lý lỗi
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("OrderAdapter", "Error getting orders from Firebase: " + error.getMessage());
            }
        });
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
        holder.bindingOrder.llTableitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("nameTable", order.getNameTable().toString());
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
