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
import com.midterm.restaurant_app.model.Ordered;
import com.midterm.restaurant_app.viewmodel.adapter.TableAdapter;

import java.util.ArrayList;
import java.util.List;

public class ServeFragment extends Fragment {
    private RecyclerView recyclerAllTable;
    private TableAdapter tablesAdap;
    private LinearLayout navHome;
    private LinearLayout navHis;
    private LinearLayout navAccount;

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
        return inflater.inflate(R.layout.fragment_serve, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerAllTable = view.findViewById(R.id.rv_alltable);
        tablesAdap = new TableAdapter(view.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),recyclerAllTable.VERTICAL,false);
        recyclerAllTable.setLayoutManager(linearLayoutManager);
        tablesAdap.setData(getListItem());
        recyclerAllTable.setAdapter(tablesAdap);
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
    public List<Ordered> getListItem() {
        List<Ordered> list = new ArrayList<>();
        return list;
    }

}