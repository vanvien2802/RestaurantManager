package com.midterm.restaurant_app.view;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.viewmodel.adapter.customerOrderedAdapter;

import java.util.ArrayList;
import java.util.List;


public class HisOrderFragment extends Fragment {
    private RecyclerView recyclerHisOrder;
    private customerOrderedAdapter cusOrderedAdapter;
    private LinearLayout navHome;
    private LinearLayout navSer;
    private LinearLayout navAcc;
    private String nameTable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_his_order, container, false);
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

        navSer = view.findViewById(R.id.nav_serve);
        navSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.serveFragment, savedInstanceState);
            }
        });
        navAcc = view.findViewById(R.id.nav_account);
        navAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.accountFragment, savedInstanceState);
            }
        });
        navHome = view.findViewById(R.id.nav_home);
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.homenav, savedInstanceState);
            }
        });
    }

    private List<Account> getListItem(){

        List<Account> list = new ArrayList<>();
//        list.add(new Account("Huong Trinh","250,9","28/02/2023"));
//        list.add(new Account("Huong Trinh","250,9","28/02/2023"));
//        list.add(new Account("Huong Trinh","250,9","28/02/2023"));
//        list.add(new Account("Huong Trinh","250,9","28/02/2023"));
//        list.add(new Account("Huong Trinh","250,9","28/02/2023"));
        return list;
    }
}