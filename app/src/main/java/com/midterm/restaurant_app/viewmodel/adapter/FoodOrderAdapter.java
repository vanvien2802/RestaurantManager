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
import androidx.recyclerview.widget.RecyclerView;
import com.midterm.restaurant_app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.ViewHolder> {

    private Context context;
    List<FoodItem> foodItems;

    public FoodOrderAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<FoodItem> items){
        this.foodItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_food,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.tvTitle.setText(foodItem.getTitle());
        holder.tvCostFood.setText((String) foodItem.getCost());
        holder.tvDescrip.setText(foodItem.getCost());
        if(foodItem.isStatus()){
            holder.tvStatusServe.setText("Done");
        }
        else{
            holder.tvStatusServe.setText("Not done");
        }
    }

    @Override
    public int getItemCount() {
        if(foodItems != null){
            return foodItems.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvCostFood;
        private TextView tvStatusServe;
        private TextView tvDescrip;
        private ImageView imRemove;
        private Spinner spReason;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_nameFood);
            tvCostFood = itemView.findViewById(R.id.tv_costfood);
            tvStatusServe = itemView.findViewById(R.id.tv_statusserve);
            tvDescrip = itemView.findViewById(R.id.tv_details);

            imRemove = itemView.findViewById(R.id.im_remove);

            imRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openFeedbackDialog(Gravity.CENTER);
                }
            });
        }
        private void openFeedbackDialog(int gravity){
            final Dialog dialog = new Dialog(imRemove.getContext());
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
            spReason = dialog.findViewById(R.id.sp_reason);

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

}
