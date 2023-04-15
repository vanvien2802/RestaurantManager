package com.midterm.restaurant_app.view;

import android.media.audiofx.AcousticEchoCanceler;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentServeBinding;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.model.Table;
import com.midterm.restaurant_app.viewmodel.adapter.OrderAdapter;
import com.midterm.restaurant_app.viewmodel.adapter.itemsProductAdapter;
import com.midterm.restaurant_app.viewmodel.modelView.OrderViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.ProductViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.TableViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServeFragment extends Fragment {
    private FragmentServeBinding binding;
    private OrderAdapter orderAdapter;
    private OrderViewModel orderViewModel;
    private TableViewModel tableViewModel;
    private HashMap<String, Table> hashMapTable;
    private List<Order> lstOrders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using data binding
        binding = FragmentServeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the reference to the RecyclerView using data binding
        RecyclerView recyclerAllTable = binding.rvAlltable;

        hashMapTable = new HashMap<>();
        final boolean[] check = {false};


        tableViewModel = new TableViewModel();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerAllTable.setLayoutManager(linearLayoutManager);

        orderViewModel = new OrderViewModel();
        MainActivity mainActivity = new MainActivity();
        Account account = mainActivity.accountSignIn;

        lstOrders = new ArrayList<>();
        orderViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                for (Order order : orders){
                    tableViewModel.getById(order.getIdTable()).observe(getViewLifecycleOwner(), new Observer<Table>() {
                        @Override
                        public void onChanged(Table table) {
                            hashMapTable.put(order.getIdTable(),table);
                            orderAdapter = new OrderAdapter(view.getContext(),hashMapTable);
                            if(account.getIdRole() == 1){
                                orderAdapter.setData(orders);
                            }
                            else {
                                if(order.getIdAcc().equals(account.getIdAcc())){
                                    lstOrders.add(order);
                                    orderAdapter.setData(lstOrders);
                                    orderAdapter.notifyDataSetChanged();
                                }
                            }
                            recyclerAllTable.setAdapter(orderAdapter);
                        }
                    });
                }
            }
        });



        // Set up the onClickListeners for the navigation buttons
        binding.navHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.hisOrderFragment);
            }
        });

        binding.navAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.accountFragment);
            }
        });

        binding.navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.homenav);
            }
        });
    }
}


