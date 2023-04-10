package com.midterm.restaurant_app.view;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.FirstActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.viewmodel.adapter.itemsMenuProductAdapter;
import com.midterm.restaurant_app.viewmodel.modelView.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class MenuFoodFragment extends Fragment {
    private RecyclerView recyclerFoods;
    private  itemsMenuProductAdapter itemsAdapter;
    private LinearLayout navSer;
    private LinearLayout navHis;
    private LinearLayout navAccount;
    private LinearLayout navMenu;
    private ImageView ivSideMenu;
    private DrawerLayout drawerLayout;
    private FloatingActionButton flbtnLogout;
    private Button  Add_Button;

    private DatabaseReference databaseReference;
    List<Product> lstProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        itemsAdapter = new itemsMenuProductAdapter(view.getContext());

        lstProduct = new ArrayList<>();


        itemsAdapter.setData(lstProduct);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    lstProduct.add(product);
                }

                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3); // số 2 ở đây là số cột hiển thị
        recyclerFoods = view.findViewById(R.id.rv_list_food);
//        recyclerFoods.setLayoutManager(gridLayoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerFoods.setLayoutManager(layoutManager);
        recyclerFoods.setAdapter(itemsAdapter);

        navSer = view.findViewById(R.id.nav_serve);
        navHis = view.findViewById(R.id.nav_his);
        navSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.serveFragment, savedInstanceState);
            }
        });
        navHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.hisOrderFragment, savedInstanceState);
            }
        });
        navAccount = view.findViewById(R.id.nav_account);
        navAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.accountFragment, savedInstanceState);
            }
        });

        Add_Button = view.findViewById(R.id.but_Add);
        updateAddButton(false, "+ Add");
        recyclerFoods.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // RecyclerView đứng yên, hiển thị nút dạng hình chữ nhật bo tròn
                    updateAddButton(false, "+ Add");
                } else {
                    // RecyclerView đang di chuyển, hiển thị nút dạng hình tròn
                    updateAddButton(true, "+");
                }
            }
        });

        Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    private FoodViewModel foodViewModel;
    public void updateAddButton ( boolean isRound,String text) {
        if (isRound) {
            // Đặt định dạng hình tròn cho nút
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.OVAL);
            shape.setSize(80,80);
            shape.setColor(ContextCompat.getColor(getContext(), R.color.green));
            Add_Button.setBackground(shape);
            Add_Button.setTextSize(40);
            Add_Button.setGravity(Gravity.CENTER);
        } else {
            // Đặt định dạng hình chữ nhật bo tròn cho nút
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            shape.setSize(200,80);
            shape.setCornerRadius(getResources().getDimension(R.dimen.button_corner_radius));
            shape.setColor(ContextCompat.getColor(getContext(), R.color.green));
            Add_Button.setBackground(shape);
            Add_Button.setTextSize(20);
            Add_Button.setGravity(Gravity.CENTER);

        }
        Add_Button.setText(text);
    }

    private List<Product> getListItem(){
        List<Product> list = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    list.add(product);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        itemsAdapter.notifyDataSetChanged();
        return list;
    }
}