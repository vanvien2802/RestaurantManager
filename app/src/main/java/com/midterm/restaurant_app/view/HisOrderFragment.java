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

import com.bumptech.glide.Glide;
import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentHisOrderBinding;
import com.midterm.restaurant_app.databinding.FragmentServeBinding;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.viewmodel.adapter.HisOrderAdapter;
import com.midterm.restaurant_app.viewmodel.modelView.AccountViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.OrderViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HisOrderFragment extends Fragment {
    private RecyclerView recyclerHisOrder;
    private HisOrderAdapter hisOrderAdapter;
    private LinearLayout navHome;
    private LinearLayout navSer;
    private LinearLayout navAcc;
    private OrderViewModel orderViewModel;
    private AccountViewModel accountViewModel;
    private HashMap<String, Account> hashMapAccount;
    private List<Order> lstOrders;
    private FragmentHisOrderBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHisOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerHisOrder =view.findViewById(R.id.rv_allHisOrderedTable);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),recyclerHisOrder.VERTICAL,false);
        recyclerHisOrder.setLayoutManager(linearLayoutManager);

        orderViewModel = new OrderViewModel();
        accountViewModel = new AccountViewModel();
        MainActivity mainActivity = new MainActivity();
        Account account = mainActivity.accountSignIn;
        if(account.getUrlAvatar()!= null){
            Glide.with(getContext())
                    .load(account.getUrlAvatar())
                    .centerCrop()
                    .placeholder(R.drawable.initialimage)
                    .into(binding.myAvatar);
        }

        hashMapAccount = new HashMap<>();

        orderViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                lstOrders = new ArrayList<>();
                for (Order order : orders){
                    if(order.getStatusOrdered().equals("Complete")){
                        accountViewModel.getById(order.getIdAcc()).observe(getViewLifecycleOwner(), new Observer<Account>() {
                            @Override
                            public void onChanged(Account t_account) {
                                hashMapAccount.put(order.getIdAcc(),t_account);
                                hisOrderAdapter = new HisOrderAdapter(view.getContext(),hashMapAccount);
                                if(account.getIdRole() == 1){
                                    binding.tvTitle.setText("His Ordered");
                                    setAdapterDb();
                                }
                                else {
                                    binding.tvTitle.setText("Your Ordered");
                                    if(order.getIdAcc().equals(account.getIdAcc()) && order.getStatusOrdered().equals("Complete")){
                                        setAdapterDb();
                                    }
                                }
                                recyclerHisOrder.setAdapter(hisOrderAdapter);
                            }

                            private void setAdapterDb() {
                                lstOrders.add(order);
                                hisOrderAdapter.setData(lstOrders);
                                hisOrderAdapter.notifyDataSetChanged();
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