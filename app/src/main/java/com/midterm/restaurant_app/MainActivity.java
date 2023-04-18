package com.midterm.restaurant_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.databinding.FragmentHomeBinding;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Order;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.model.Table;
import com.midterm.restaurant_app.view.ChatMain;
import com.midterm.restaurant_app.viewmodel.modelView.OrderViewModel;
import com.midterm.restaurant_app.viewmodel.modelView.TableViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String GMAIL;
    public static Account accountSignIn;
    private DrawerLayout drawerLayout;
    private FloatingActionButton flbtnLogout;
    private LinearLayout linearMenu;
    private LinearLayout linearAction;
    private ImageView ivSideMenu;
    private LinearLayout navChat, navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GMAIL = getIntent().getStringExtra("GMAIL");

        linearMenu = findViewById(R.id.linear_menu);
        linearAction = findViewById(R.id.linear_action);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Account");
        MainActivity mainActivity = new MainActivity();
        String gmail = mainActivity.GMAIL;
        ImageView imageView = findViewById(R.id.my_image_view);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    accountSignIn = dataSnapshot.getValue(Account.class);
                    if(accountSignIn.getEmail().equals(gmail)){
                        if(accountSignIn.getIdRole() == 0){
                            linearAction.removeView(linearMenu);
                        }
                        if(accountSignIn.getUrlAvatar()!= null){
                            Glide.with(getApplicationContext())
                                    .load(accountSignIn.getUrlAvatar())
                                    .centerCrop()
                                    .placeholder(R.drawable.initialimage)
                                    .into(imageView);
                        }
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        navChat = findViewById(R.id.linear_chat);
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChatMain.class);
                startActivity(intent);
            }
        });

        navMenu = findViewById(R.id.linear_menu);
        navMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.menuFoodFragment, savedInstanceState);
            }
        });

        drawerLayout = findViewById(R.id.drawerLayout);
        ivSideMenu = findViewById(R.id.iv_side_menu);
        ivSideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        flbtnLogout = findViewById(R.id.flbtn_logout);
        flbtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}