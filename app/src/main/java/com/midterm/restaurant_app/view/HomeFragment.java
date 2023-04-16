package com.midterm.restaurant_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.FirstActivity;
import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentAccountBinding;
import com.midterm.restaurant_app.databinding.FragmentHomeBinding;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.model.Table;
import com.midterm.restaurant_app.viewmodel.adapter.itemsProductAdapter;
import com.midterm.restaurant_app.viewmodel.modelView.FoodViewModel;
import com.midterm.restaurant_app.view.MenuFoodFragment;
import com.midterm.restaurant_app.viewmodel.modelView.OrderViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.ProductViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.TableViewModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerPopular;
    private RecyclerView recyclerFoods;
    private itemsProductAdapter itemsAdapter;
    private LinearLayout navSer;
    private LinearLayout navHis;
    private LinearLayout navAccount;
    private LinearLayout navChat, navMenu;
    private ImageView ivSideMenu;
    private DrawerLayout drawerLayout;
    private FloatingActionButton flbtnLogout;
    private CircleImageView myAvatar;

    private DatabaseReference databaseReference;
    private FragmentHomeBinding bindingHome;
    List<Product> lstProduct;

    private Account accountHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerPopular = view.findViewById(R.id.rv_popular);
        recyclerFoods = view.findViewById(R.id.rv_foods);

        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        itemsAdapter = new itemsProductAdapter(view.getContext());

        lstProduct = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),recyclerPopular.HORIZONTAL,false);
        recyclerPopular.setLayoutManager(linearLayoutManager);

        ProductViewModel productViewModel = new ProductViewModel();
        productViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                itemsAdapter.setData(products);
                recyclerPopular.setAdapter(itemsAdapter);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3); // số 2 ở đây là số cột hiển thị
        recyclerFoods.setLayoutManager(gridLayoutManager);

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
        navChat = view.findViewById(R.id.linear_chat);
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatMain.class);
                startActivity(intent);
            }
        });

        navMenu = view.findViewById(R.id.linear_menu);
        navMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.menuFoodFragment, savedInstanceState);
            }
        });

        drawerLayout = view.findViewById(R.id.drawerLayout);
        ivSideMenu = view.findViewById(R.id.iv_sidemenu);
        ivSideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        flbtnLogout = view.findViewById(R.id.flbtn_logout);
        flbtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FirstActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bindingHome = FragmentHomeBinding.inflate(inflater, container, false);
        return bindingHome.getRoot();
    }

    private FoodViewModel foodViewModel;

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

//    TableViewModel tableViewModel = new TableViewModel();
//    OrderViewModel orderViewModel = new OrderViewModel();
//        orderViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
//        @Override
//        public void onChanged(List<Order> orders) {
//            for (Order order : orders){
//                tableViewModel.getById(order.getIdTable()).observe(getViewLifecycleOwner(), new Observer<Table>() {
//                    @Override
//                    public void onChanged(Table table) {
//                        if(order.getStatusOrdered().equals("Complete")){
//                            FirebaseDatabase.getInstance().getReference("Table").child(table.getIdTable()).child("statusTB").setValue("0");
//                        }
//                        if(order.getStatusOrdered().equals("Serving...")){
//                            FirebaseDatabase.getInstance().getReference("Table").child(table.getIdTable()).child("statusTB").setValue("1");
//                        }
//                    }
//                });
//            }
//        }
//    });
}