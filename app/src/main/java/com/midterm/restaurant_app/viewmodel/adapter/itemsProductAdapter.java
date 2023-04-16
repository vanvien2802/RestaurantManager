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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemProductsBinding;
import com.midterm.restaurant_app.databinding.LayoutDialogAddFoodForTableBinding;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.DetailOrder;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.model.Table;
import com.midterm.restaurant_app.view.ServeFragment;
import com.midterm.restaurant_app.viewmodel.modelView.OrderViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.TableViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class itemsProductAdapter extends RecyclerView.Adapter<itemsProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> productItems;
    private ItemProductsBinding binding;
    private LayoutDialogAddFoodForTableBinding bindingDialog;
    private List<String> lstItemTable;
    private List<Order> lstOrder;
    private String idAccount;
    private String selectedItem;
    private List<Table> lstTable;
    private List<DetailOrder> lstDetailOrder;
    private Table tableSelect;

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

                handleCheckOrderExist();
                getAllDetailOrder();
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

                if(product.getUrlProduct()!= ""){
                    Glide.with(dialog.getContext())
                            .load(product.getUrlProduct())
                            .centerCrop()
                            .placeholder(R.drawable.initialimage)
                            .into(bindingDialog.ivImageAddProduct);
                }

                lstItemTable = new ArrayList<>();
                lstTable = new ArrayList<>();
                FirebaseDatabase.getInstance().getReference("Table").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                            Table table = childSnapshot.getValue(Table.class);
                            lstTable.add(table);
                                if(table.getStatusTb().equals("0")){
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
                bindingDialog.listTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItem = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
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
                        if(bindingDialog.btnAdd.getText().equals("ADD TO NEW ORDER")){
                            handleAddProductToNewOrder(product);
                            setStatusTable();
                        }
                        else{
                            handleAddProductToOrder(product);
                        }
                    }

                    private void setStatusTable() {
                        FirebaseDatabase.getInstance().getReference("Table").child(tableSelect.getIdTable()).child("statusTB").setValue("1");
                    }
                });
                //Lấy list table
                ServeFragment getlistTable= new ServeFragment();
                ArrayList<String> tableNameList = new ArrayList<>();
                dialog.show();
            }
        });
    }

    private void handleAddProductToOrder(Product product){
        for(Order order : lstOrder){
            if(order.getIdAcc().equals(idAccount) && order.getStatusOrdered().equals("Serving...")){
                DetailOrder detailOrder = new DetailOrder(createNewId("DO"),order.getIdOrder(),product.getIdProduct(),Integer.parseInt(bindingDialog.txtNumberOfDishes.getText().toString().trim()),"Not Done");

                FirebaseDatabase.getInstance().getReference("DetailOrder").child(createNewId("DO")).setValue(detailOrder);
                break;
            }
        }
    }

    private void handleAddProductToNewOrder(Product product){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(calendar.getTime());
        String idOrder = createNewId("Ord");
        Order order = new Order(idOrder,idAccount,getIdTableByNameTable(selectedItem),"Serving...",45,dateTime);
        FirebaseDatabase.getInstance().getReference("Order").child(idOrder).setValue(order);


        DetailOrder detailOrder = new DetailOrder(createNewId("DO"),idOrder,product.getIdProduct(),Integer.parseInt(bindingDialog.txtNumberOfDishes.getText().toString().trim()),"Not Done");

        FirebaseDatabase.getInstance().getReference("DetailOrder").child(createNewId("DO")).setValue(detailOrder);
    }

    private String getIdTableByNameTable(String str){
        for (Table table : lstTable){
            if(table.getNameTable().equals(str)){
                tableSelect = table;
                return table.getIdTable();
            }
        }
        return "";
    }

    private String createNewId(String firstId){
            String id = "";
            int i = 1;
            boolean check = false;
            while(!check){
                check = true;
                if (i < 10) id = firstId+"0" + (i+1);
                else if (i >= 10) id = firstId + (i+1);
                if(firstId.equals("Ord")){
                    for (Order order : lstOrder) {
                        if (order.getIdOrder().toString().trim().equals(id)) {
                            check = false;
                            break;
                        }
                    }
                }
                else{
                    if(firstId.equals("DO")){
                        for (DetailOrder detailOrder : lstDetailOrder) {
                            if (detailOrder.getIdDetailOrder().toString().trim().equals(id)) {
                                check = false;
                                break;
                            }
                        }
                    }
                }
                i++;
            }
            return id;
    }

    private void getAllDetailOrder(){
        lstDetailOrder = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("DetailOrder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    DetailOrder detailOrder = childSnapshot.getValue(DetailOrder.class);
                    lstDetailOrder.add(detailOrder);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void handleCheckOrderExist(){
        MainActivity mainActivity = new MainActivity();
        Account account = mainActivity.accountSignIn;
        idAccount = account.getIdAcc();
        lstOrder = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean CHECK = false;
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    Order order = childSnapshot.getValue(Order.class);
                    lstOrder.add(order);
                    if(order.getIdAcc().equals(idAccount)){
                        CHECK = true;
                        break;
                    }
                }
                if(CHECK){
                    bindingDialog.btnAdd.setText("ADD TO YOUR ORDER");
                }
                else {
                    bindingDialog.btnAdd.setText("ADD TO NEW ORDER");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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