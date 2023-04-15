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
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemProductsBinding;
import com.midterm.restaurant_app.databinding.LayoutDialogAddFoodForTableBinding;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.model.Table;
import com.midterm.restaurant_app.view.ServeFragment;
import com.midterm.restaurant_app.viewmodel.modelView.TableViewModel;

import java.util.ArrayList;
import java.util.List;

public class itemsProductAdapter extends RecyclerView.Adapter<itemsProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> productItems;
    private ItemProductsBinding binding;
    private LayoutDialogAddFoodForTableBinding bindingDialog;
    private List<String> lstItemTable;

    public itemsProductAdapter(Context context) {
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

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_products,
                parent,
                false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productItems.get(position);
        holder.binding.setProduct(productItems.get(position));
        if(product.getUrlProduct()!= ""){
            Glide.with(this.context)
                    .load(product.getUrlProduct())
                    .centerCrop()
                    .placeholder(R.drawable.initialimage)
                    .into(holder.binding.ivFoodimage);
        }

        holder.binding.cardFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeedbackDialog(Gravity.CENTER);
            }

            private void openFeedbackDialog(int gravity){
                final Dialog dialog = new Dialog (context);

                bindingDialog = LayoutDialogAddFoodForTableBinding.inflate(LayoutInflater.from(context));

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(bindingDialog.getRoot());

                Window window = dialog.getWindow();
                if(window==null){
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                windowAttributes.gravity= gravity;
                window.setAttributes(windowAttributes);

                if(Gravity.BOTTOM == gravity){
                    dialog.setCancelable(true);
                } else{
                    dialog.setCancelable(false);
                }

                lstItemTable = new ArrayList<>();
                FirebaseDatabase.getInstance().getReference("Table").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                            Table table = childSnapshot.getValue(Table.class);
                            if(table.getStatusTb().equals("opening")){
                                lstItemTable.add(table.getNameTable());
                                // Tạo một ArrayAdapter để hiển thị danh sách chuỗi
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context.getApplicationContext(), R.layout.style_spinner, lstItemTable);
                                adapter.notifyDataSetChanged();
                                bindingDialog.listTable.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                bindingDialog.imgPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentValue = Integer.parseInt(bindingDialog.txtNumberOfDishes.getText().toString());
                        int newValue = currentValue + 1;
                        bindingDialog.txtNumberOfDishes.setText(Integer.toString(newValue));
                    }
                });


                bindingDialog.imgMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentValue = Integer.parseInt(bindingDialog.txtNumberOfDishes.getText().toString());
                        if (currentValue > 0) {
                            int newValue = currentValue - 1;
                            bindingDialog.txtNumberOfDishes.setText(Integer.toString(newValue));
                        }
                    }
                });

                bindingDialog.btnCancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                bindingDialog.btnAdd.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });
                //Lấy list table
                ServeFragment getlistTable= new ServeFragment();
                ArrayList<String> tableNameList = new ArrayList<>();
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

        private ItemProductsBinding binding;

        public ViewHolder(ItemProductsBinding itembinding) {
            super(itembinding.getRoot());
            this.binding = itembinding;
        }
    }

}