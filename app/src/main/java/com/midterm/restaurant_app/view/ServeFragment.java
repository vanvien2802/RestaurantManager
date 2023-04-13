package com.midterm.restaurant_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentServeBinding;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.viewmodel.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class ServeFragment extends Fragment {

    private FragmentServeBinding binding;
    private OrderAdapter orderAdapter;

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

        // Set up the RecyclerView and OrderAdapter
        orderAdapter = new OrderAdapter(view.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerAllTable.setLayoutManager(linearLayoutManager);
        recyclerAllTable.setAdapter(orderAdapter);

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

        // Load data from Firebase
        orderAdapter.getOrdersFromFirebase();
    }

    public void setData(List<Order> orderList) {
        orderAdapter.setData(orderList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


