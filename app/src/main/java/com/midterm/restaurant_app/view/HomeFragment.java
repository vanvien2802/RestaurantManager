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
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.viewmodel.adapter.itemsProductAdapter;
import com.midterm.restaurant_app.viewmodel.modelView.FoodViewModel;

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
    private LinearLayout navChat;
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
        itemsAdapter.setData(lstProduct);
        recyclerPopular.setAdapter(itemsAdapter);

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
        // Inflate the layout for this fragment
        bindingHome = FragmentHomeBinding.inflate(inflater, container, false);
        myAvatar = bindingHome.myImageAvatar;
        MainActivity mainAct = new MainActivity();
        accountHome = mainAct.accountSignIn;
        bindingHome.setAccount(accountHome);
//        if(accountHome.getUrlAvatar()!= null){
//            Glide.with(getContext())
//                    .load(accountHome.getUrlAvatar())
//                    .centerCrop()
//                    .placeholder(R.drawable.initialimage)
//                    .into(myAvatar);
//        }
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
}