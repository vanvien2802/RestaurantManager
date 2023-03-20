package com.midterm.restaurant_app.view;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.customerItem;
import com.midterm.restaurant_app.viewmodel.customerOrderedAdapter;

import java.util.ArrayList;
import java.util.List;


public class hisOrderFragment extends Fragment {
    private RecyclerView recyclerHisOrder;
    private customerOrderedAdapter cusOrderedAdapter;
    private LinearLayout navHome;
    private LinearLayout navSer;
    private LinearLayout navAccount;
    private LinearLayout navAcc;
    private String nameTable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerHisOrder =view.findViewById(R.id.rv_allHisOrderedTable);
        cusOrderedAdapter = new customerOrderedAdapter(view.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),recyclerHisOrder.VERTICAL,false);
        recyclerHisOrder.setLayoutManager(linearLayoutManager);
        cusOrderedAdapter.setData(getListItem());
        recyclerHisOrder.setAdapter(cusOrderedAdapter);
        navHome = view.findViewById(R.id.nav_home);
        navSer = view.findViewById(R.id.nav_serve);
        navAccount = view.findViewById(R.id.nav_account);
//        navHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view).navigate(R.id.home_nav, savedInstanceState);
//            }
//        });
    }

    private List<customerItem> getListItem(){

        List<customerItem> list = new ArrayList<>();
        list.add(new customerItem("Huong Trinh","250,9","28/02/2023"));
        list.add(new customerItem("Huong Trinh","250,9","28/02/2023"));
        list.add(new customerItem("Huong Trinh","250,9","28/02/2023"));
        list.add(new customerItem("Huong Trinh","250,9","28/02/2023"));
        list.add(new customerItem("Huong Trinh","250,9","28/02/2023"));
        return list;
    }
}