package com.midterm.restaurant_app.viewmodel.adapter;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemDetailsProductBinding;
import com.midterm.restaurant_app.databinding.ItemOrderBinding;
import com.midterm.restaurant_app.model.DetailOrder;
import com.midterm.restaurant_app.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductOrderAdapter extends RecyclerView.Adapter<ProductOrderAdapter.ViewHolder> {

    private Context context;
    private List<DetailOrder> detailOrderItems;
    private ItemDetailsProductBinding bindingDetailsProduct;
    private static Product product;

    public ProductOrderAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DetailOrder> items){
        this.detailOrderItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        bindingDetailsProduct = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_details_product,
                parent,
                false
        );
        return new ViewHolder(bindingDetailsProduct);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailOrder detailOrder = detailOrderItems.get(position);
        holder.bindingDetailsProduct.tvNumber.setText(String.valueOf(detailOrder.getQuantity()));
    }

    @Override
    public int getItemCount() {
        if(detailOrderItems != null){
            return detailOrderItems.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ItemDetailsProductBinding bindingDetailsProduct;

        public ViewHolder(@NonNull ItemDetailsProductBinding binding) {
            super(binding.getRoot());
            this.bindingDetailsProduct = binding;
            bindingDetailsProduct.imRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openFeedbackDialog(Gravity.CENTER);
                }
            });
        }
        private void openFeedbackDialog(int gravity){
            final Dialog dialog = new Dialog(bindingDetailsProduct.imRemove.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_remove_food);
            Window window = dialog.getWindow();
            if(window == null){
                return;
            }
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams windownAttribute = window.getAttributes();
            windownAttribute.gravity = gravity;
            window.setAttributes(windownAttribute);
            Spinner spReason = dialog.findViewById(R.id.sp_reason);

            String[] reasons = {"Đã hết nguyên liệu","Có vấn đề trong lúc nấu","Khác"};
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(reasons));
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context.getApplicationContext(),R.layout.style_spinner,arrayList);
            spReason.setAdapter(arrayAdapter);

            Button btnCancel = dialog.findViewById(R.id.btn_cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            if(Gravity.BOTTOM == gravity){
                dialog.setCancelable(true);
            }
            else {
                dialog.setCancelable(false);
            }

            dialog.show();
        }
    }

//    private static void getProductById(String idProduct){
//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Product");
//        myRef.child(idProduct).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Product prod = dataSnapshot.getValue(Product.class);
//                if (prod != null) {
//                    product = prod;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Lỗi xảy ra khi lấy sản phẩm từ Firebase Realtime Database
//            }
//        });
//    }

}
