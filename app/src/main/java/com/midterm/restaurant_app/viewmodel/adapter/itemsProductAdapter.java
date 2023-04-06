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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.view.ServeFragment;

import java.util.ArrayList;
import java.util.List;

public class itemsProductAdapter extends RecyclerView.Adapter<itemsProductAdapter.ViewHolder> {

    private Context context;
    List<Product> productItems;

    private Spinner spTable;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product productItem = productItems.get(position);
//        holder.tvTitle.setText(foodItem.getTitle());
//        holder.tvCost.setText((String) foodItem.getCost());

        holder.itemView.findViewById(R.id.card_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeedbackDialog(Gravity.CENTER);
            }

            private void openFeedbackDialog(int gravity){
                final Dialog dialog = new Dialog (context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_add_food_for_table);

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
                Button btnNoThanks = dialog.findViewById(R.id.btn_no_thanks);
                Button btnSend = dialog.findViewById(R.id.btn_send);
                ImageView imageView_plus = dialog.findViewById(R.id.img_plus);
                ImageView imageView_minus = dialog.findViewById(R.id.img_minus);
                TextView number_of_dishes = dialog.findViewById(R.id.txt_number_of_dishes);
                imageView_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentValue = Integer.parseInt(number_of_dishes.getText().toString());
                        int newValue = currentValue + 1;
                        number_of_dishes.setText(Integer.toString(newValue));
                    }
                });


                imageView_minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentValue = Integer.parseInt(number_of_dishes.getText().toString());
                        if (currentValue > 0) {
                            int newValue = currentValue - 1;
                            number_of_dishes.setText(Integer.toString(newValue));
                        }
                    }
                });

                btnNoThanks.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnSend.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });
                //Lấy list table
                ServeFragment getlistTable= new ServeFragment();
                ArrayList<String> tableNameList = new ArrayList<>();

//                for (TableItem table : getlistTable.getListItem()) {
//                    String tableName = table.getNameTable();
//                    tableNameList.add(tableName);
//                }
//                ArrayAdapter<String> arrayAdapter_Table = new ArrayAdapter<>(context.getApplicationContext(),R.layout.style_spinner,tableNameList);
//                spTable = dialog.findViewById(R.id.List_table);
//                spTable.setAdapter(arrayAdapter_Table);
//                dialog.show();
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

        private TextView tvTitle;
        private TextView tvCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCost = itemView.findViewById(R.id.tv_cost);
        }
    }

}
