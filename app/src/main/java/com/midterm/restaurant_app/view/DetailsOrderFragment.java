package com.midterm.restaurant_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentDetailServeBinding;
import com.midterm.restaurant_app.databinding.FragmentHomeBinding;
import com.midterm.restaurant_app.model.DetailOrder;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.viewmodel.adapter.ProductOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailsOrderFragment extends Fragment {

    private RecyclerView recyclerListFoods;
    private static ProductOrderAdapter productsOrAdapter;
    private LinearLayout navHome;
    private LinearLayout navHis;
    private LinearLayout navAccount;
    private String nameTable;
    private static List<DetailOrder> lstDetailOrder;

    private FragmentDetailServeBinding bindingDetailServe;

    private DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
//        lstDetailOrder = new ArrayList<>();
//        databaseReference = FirebaseDatabase.getInstance().getReference("DetailOrder");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    DetailOrder detailOrder = dataSnapshot.getValue(DetailOrder.class);
//                    lstDetailOrder.add(detailOrder);
//                }
//                productsOrAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            nameTable = bundle.getString("nameTable");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindingDetailServe = FragmentDetailServeBinding.inflate(inflater, container, false);
        return bindingDetailServe.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerListFoods = view.findViewById(R.id.rv_detailtable);
        productsOrAdapter = new ProductOrderAdapter(view.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),recyclerListFoods.VERTICAL,false);
        recyclerListFoods.setLayoutManager(linearLayoutManager);

        productsOrAdapter.setData(lstDetailOrder);
        recyclerListFoods.setAdapter(productsOrAdapter);

        navHis = view.findViewById(R.id.nav_his);
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
        navHome = view.findViewById(R.id.nav_home);
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.homenav, savedInstanceState);
            }
        });
    }
}