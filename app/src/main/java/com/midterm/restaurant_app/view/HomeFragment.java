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
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.FirstActivity;
import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentHomeBinding;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.viewmodel.adapter.itemsProductAdapter;
import com.midterm.restaurant_app.viewmodel.modelView.AccountViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.ProductViewModel;

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
    private FragmentHomeBinding bindingHome;
    List<Product> lstProduct;
    private LinearLayout linearMenu;
    private LinearLayout linearAction;

    private MainActivity mainActivity;
    private String Gmail;

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

        recyclerPopular = bindingHome.rvPopular;
        recyclerFoods = bindingHome.rvFoods;

        mainActivity = new MainActivity();


        linearMenu = bindingHome.linearMenu;
        linearAction = bindingHome.linearAction;

        Gmail = mainActivity.GMAIL;

        setViewUser();

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

        navSer = bindingHome.navServe;
        navHis = bindingHome.navHis;
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
        navAccount = bindingHome.navAccount;
        navAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.accountFragment, savedInstanceState);
            }
        });
        navChat = bindingHome.linearChat;
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatMain.class);
                startActivity(intent);
            }
        });

        navMenu = bindingHome.linearMenu;
        navMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.menuFoodFragment, savedInstanceState);
            }
        });

        drawerLayout = bindingHome.drawerLayout;
        ivSideMenu = bindingHome.ivSidemenu;
        ivSideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        flbtnLogout = bindingHome.flbtnLogout;
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

    private void setViewUser() {
        AccountViewModel accountViewModel = new AccountViewModel();
        accountViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                for(Account account : accounts){
                    if(account.getEmail().equals(Gmail)){
                        if(account.getUrlAvatar()!= null){
                            Glide.with(getContext())
                                    .load(account.getUrlAvatar())
                                    .centerCrop()
                                    .placeholder(R.drawable.initialimage)
                                    .into(bindingHome.myAvatar);
                        }
                        if(account.getIdRole() == 0){
                            linearAction.removeView(linearMenu);
                        }
                        break;
                    }
                }
            }
        });
    }
}