package com.midterm.restaurant_app.view;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.model.Table;
import com.midterm.restaurant_app.viewmodel.adapter.AccountAdapter;
import com.midterm.restaurant_app.viewmodel.adapter.OrderAdapter;
import com.midterm.restaurant_app.viewmodel.modelView.OrderViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.TableViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HisOrderFragment extends Fragment {
    private RecyclerView recyclerHisOrder;
    private AccountAdapter cusOrderedAdapter;
    private LinearLayout navHome;
    private LinearLayout navSer;
    private LinearLayout navAcc;
    private String nameTable;
    private OrderViewModel orderViewModel;
    private TableViewModel tableViewModel;
    private HashMap<String, Table> hashMapTable;
    private List<Order> lstOrders;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),recyclerHisOrder.VERTICAL,false);
        recyclerHisOrder.setLayoutManager(linearLayoutManager);

        orderViewModel = new OrderViewModel();
        tableViewModel = new TableViewModel();
        MainActivity mainActivity = new MainActivity();
        Account account = mainActivity.accountSignIn;

        lstOrders = new ArrayList<>();
        orderViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                for (Order order : orders){
                    if(order.getStatusOrdered().equals("Serving...")){
                        tableViewModel.getById(order.getIdTable()).observe(getViewLifecycleOwner(), new Observer<Table>() {
                            @Override
                            public void onChanged(Table table) {
                                hashMapTable.put(order.getIdTable(),table);
                                cusOrderedAdapter = new AccountAdapter(view.getContext(),hashMapTable);
                                if(account.getIdRole() == 1){
                                    setAdapterDb();
                                }
                                else {
                                    if(order.getIdAcc().equals(account.getIdAcc()) && order.getStatusOrdered().equals("Serving...")){
                                        setAdapterDb();
                                    }
                                }
                                recyclerHisOrder.setAdapter(cusOrderedAdapter);
                            }

                            private void setAdapterDb() {
                                lstOrders.add(order);
                                cusOrderedAdapter.setData(lstOrders);
                                cusOrderedAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        });

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
}