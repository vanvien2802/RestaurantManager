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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.FirstActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentHomeBinding;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.viewmodel.adapter.itemsProductAdapter;
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
    private CircleImageView myAvatar;
    private ProductViewModel productViewModel;
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

        itemsAdapter = new itemsProductAdapter(view.getContext());

        lstProduct = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),recyclerPopular.HORIZONTAL,false);
        recyclerPopular.setLayoutManager(linearLayoutManager);
        productViewModel= new ProductViewModel();
        //get all with view model
        productViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                itemsAdapter.setData(products);
            }
        });

        // get by id example

        productViewModel.getById("Pd02").observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                Log.d("DEBUG", product.getNameProduct());
            }
        });

        // delete by id example

//        productViewModel.delete("Pd07");

        // update by id

        productViewModel.update("Pd08", new Product("Pd08", "ajdf", "adsjf", 1.2, "haha", 1));


        recyclerPopular.setAdapter(itemsAdapter);

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
        // Inflate the layout for this fragment
        bindingHome = FragmentHomeBinding.inflate(inflater, container, false);
//        myAvatar = bindingHome.myImageAvatar;
//        MainActivity mainAct = new MainActivity();
//        accountHome = mainAct.accountSignIn;
//        bindingHome.setAccount(accountHome);
//        if(accountHome.getUrlAvatar()!= null){
//            Glide.with(getContext())
//                    .load(accountHome.getUrlAvatar())
//                    .centerCrop()
//                    .placeholder(R.drawable.initialimage)
//                    .into(myAvatar);
//        }
        return bindingHome.getRoot();
    }
}