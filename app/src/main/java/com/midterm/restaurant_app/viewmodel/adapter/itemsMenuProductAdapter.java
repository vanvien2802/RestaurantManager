package com.midterm.restaurant_app.viewmodel.adapter;


import android.annotation.SuppressLint;
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
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemMenuProductsBinding;
import com.midterm.restaurant_app.databinding.ItemProductsBinding;
import com.midterm.restaurant_app.databinding.LayoutDialogAddFoodForTableBinding;
import com.midterm.restaurant_app.databinding.LayoutDialogAddFoodForMenuBinding;
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
        setupFirebaseListener();
    }

    public void setData(List<Product> items){
        this.productItems = items;
        notifyDataSetChanged();
    }

    private void setupFirebaseListener() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference productRef = database.getReference("Product");

        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Product> products = new ArrayList<>();
                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    products.add(product);
                }
                setData(products);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products,parent,false);

        ItemMenuProductsBinding binding = ItemMenuProductsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        Product productItem = productItems.get(position);
//        holder.tvTitle.setText(foodItem.getTitle());
//        holder.tvCost.setText((String) foodItem.getCost());
        holder.binding.setProduct(productItems.get(position));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference productRef = database.getReference("Product");

        holder.binding.cardFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeedbackDialog(Gravity.CENTER, productItems.get(position).getIdProduct());
            }

            private void openFeedbackDialog(int gravity, String productId) {
                final Dialog dialog = new Dialog(context);

                @NonNull LayoutDialogAddFoodForMenuBinding bindingDialog = LayoutDialogAddFoodForMenuBinding.inflate(LayoutInflater.from(context));

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(bindingDialog.getRoot());

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                windowAttributes.gravity = gravity;
                window.setAttributes(windowAttributes);

                if (Gravity.BOTTOM == gravity) {
                    dialog.setCancelable(true);
                } else {
                    dialog.setCancelable(false);
                }

                // Load data from Realtime Database
                productRef.child(productId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Product product = snapshot.getValue(Product.class);
                            if (product != null) {
                                bindingDialog.setProduct(product);
                                Glide.with(context)
                                        .load(productItems.get(position).getUrlProduct())
                                        .centerCrop()
                                        .placeholder(R.drawable.hampogar)
                                        .into(bindingDialog.imgFood);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle error
                    }
                });

                bindingDialog.btnCancel.setText("Delete");
                bindingDialog.btnAdd.setText("Update");
                bindingDialog.btnCancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // Delete product
                        productRef.child(productId).removeValue();
                        dialog.dismiss();
                    }
                });

                bindingDialog.btnAdd.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // Update product
                        Product product = bindingDialog.getProduct();
                        if (product != null) {
                            productRef.child(productId).setValue(product);
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
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
