package com.midterm.restaurant_app.view;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.midterm.restaurant_app.FirstActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.FoodItem;
import com.midterm.restaurant_app.viewmodel.itemsFoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerPopular;
    private RecyclerView recyclerFoods;
    private itemsFoodAdapter itemsAdapter;
    private LinearLayout navSer;
    private LinearLayout navHis;
    private LinearLayout navAcc;
    private LinearLayout navHome;
    private LinearLayout navAccount;
    private ImageView ivSideMenu;
    private DrawerLayout drawerLayout;
    private FloatingActionButton flbtnLogout;

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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerPopular = view.findViewById(R.id.rv_popular);
        recyclerFoods = view.findViewById(R.id.rv_foods);
        itemsAdapter = new itemsFoodAdapter(view.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),recyclerPopular.HORIZONTAL,false);
        recyclerPopular.setLayoutManager(linearLayoutManager);
        itemsAdapter.setData(getListItem());
        recyclerPopular.setAdapter(itemsAdapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3); // số 2 ở đây là số cột hiển thị
        recyclerFoods.setLayoutManager(gridLayoutManager);

        recyclerFoods.setAdapter(itemsAdapter);

        navSer = view.findViewById(R.id.nav_serve);
        navHis = view.findViewById(R.id.nav_his);
        navHome = view.findViewById(R.id.nav_home);
        navSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.serveFragment, savedInstanceState);
            }
        });
//        navHis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.hisOrder, savedInstanceState);
//            }
//        });
//        navAccount = view.findViewById(R.id.nav_account);
//        navAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.account, savedInstanceState);
//            }
//        });

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

    private List<FoodItem> getListItem(){
        List<FoodItem> list = new ArrayList<>();
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",true));
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",false));
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",true));
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",false));
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",true));
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",true));
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",true));
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",true));
        list.add(new FoodItem("Hamperger","15.93","Carrot, rise, broccoli, paprica",true));
        return list;
    }
}