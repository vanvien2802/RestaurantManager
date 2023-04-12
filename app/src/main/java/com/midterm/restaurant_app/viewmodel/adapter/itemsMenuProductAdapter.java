package com.midterm.restaurant_app.viewmodel.adapter;


import static android.app.Activity.RESULT_OK;
import static com.google.common.io.Files.getFileExtension;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.gms.fido.fido2.api.common.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ItemMenuProductsBinding;
import com.midterm.restaurant_app.databinding.ItemProductsBinding;
import com.midterm.restaurant_app.databinding.LayoutDialogAddFoodForTableBinding;
import com.midterm.restaurant_app.databinding.LayoutDialogAddFoodForMenuBinding;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.view.ServeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class itemsMenuProductAdapter extends RecyclerView.Adapter<itemsMenuProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> productItems;
    private ItemMenuProductsBinding binding;
    public Uri uri_img_food;

    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference productRef;
    public Product product;
    public StorageTask storageTask_product;
    public static final int PICK_IMAGE_REQUESR_Product = 1;



    public itemsMenuProductAdapter(Context context) {

        this.context = context;
        setupFirebaseListener();
    }

    public void setData(List<Product> items){
        this.productItems = items;
        notifyDataSetChanged();
    }

    private void setupFirebaseListener() {
        database = FirebaseDatabase.getInstance();
        productRef = database.getReference("Product");

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
                            product = snapshot.getValue(Product.class);
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
//                if(uri_img_food != null) {
//                    uri_img_food = Uri.parse(product.getUrlProduct());
//                }
                Picasso.with(context).load(uri_img_food).into(bindingDialog.imgFood);

                bindingDialog.ivUpload.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                       // openFileChooser();
                    }
                });
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
                        Product product = bindingDialog.getProduct();
                        if (product != null) {

                            productRef.child(product.getIdProduct()).child("nameProduct").setValue(bindingDialog.edtName.getText().toString());
                            productRef.child(product.getIdProduct()).child("detailProduct").setValue(bindingDialog.edtIngredient.getText().toString());
                            productRef.child(product.getIdProduct()).child("pricesProduct").setValue(Double.parseDouble(bindingDialog.edtPrice.getText().toString()));
                            //updateimg_Food(uri_img_food);
                        }
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
    }

//            private void updateimg_Food(Uri uri_img_food){
//
//                if(uri_img_food != null){
//                    storageReference = FirebaseStorage.getInstance().getReference("ImageProduct");
//                    StorageReference fileReference = storageReference.child(product.getNameProduct()+"."+getFileExtension(uri_img_food));
//                    storageTask_product = fileReference.putFile(uri_img_food)
//                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    Toast.makeText(context, "Upload successfull !!!",Toast.LENGTH_SHORT).show();
//                                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            String imageUrl = uri.toString();
//                                            productRef.child(product.getIdProduct()).child("urlAvatar").setValue(imageUrl);
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                        }
//                                    });
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(context, e.getMessage(),Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }
//
//            }
    @Override
    public int getItemCount() {
        if(productItems != null){
            return productItems.size();
        }
        return 0;
    }
//    private void openFileChooser(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//    }
//    private String getFileExtension(Uri uri){
//        ContentResolver cR = this.getContext().getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(cR.getType(uri));
//    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemMenuProductsBinding binding;

        public ViewHolder(ItemMenuProductsBinding itembinding) {
            super(itembinding.getRoot());
            this.binding = itembinding;
        }

    }



}
