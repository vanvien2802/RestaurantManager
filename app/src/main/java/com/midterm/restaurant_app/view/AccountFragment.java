package com.midterm.restaurant_app.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.midterm.restaurant_app.R;

public class AccountFragment extends Fragment {
    private LinearLayout  navSer, navHis, navHome;
    public AccountFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navSer = view.findViewById(R.id.nav_serve);
        navSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.serveFragment, savedInstanceState);
            }
        });
        navHis = view.findViewById(R.id.nav_his);
        navHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.hisOrderFragment, savedInstanceState);
            }
        });
        navHome = view.findViewById(R.id.nav_home);
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.homenav, savedInstanceState);
            }
        });

        //edit Name

        ImageView editName = view.findViewById(R.id.pen_editName);
        EditText editText_Name = view.findViewById(R.id.edit_Name);
        ImageView editDateBirth = view.findViewById(R.id.pen_editDateBirth);
        EditText editText_DateBirth = view.findViewById(R.id.edit_DateBirth);
        ImageView editPhone = view.findViewById(R.id.pen_editPhoneNumber);
        EditText editText_Phone = view.findViewById(R.id.edit_phone);
        ImageView editEmail = view.findViewById(R.id.pen_editEmail);
        EditText editText_Email = view.findViewById(R.id.edit_Mail);
        ImageView editIDCard = view.findViewById(R.id.pen_editIDCard);
        EditText editText_IDCard= view.findViewById(R.id.edit_IDCard);


        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nếu EditText không được kích hoạt, kích hoạt nó và thay đổi văn bản của Button
                if (!editText_Name.isEnabled()) {
                    editText_Name.setEnabled(true);
                    editText_Name.setBackgroundColor(Color.parseColor("#f09e98"));

                    //button.setText("Lưu");
                }
                // Ngược lại, vô hiệu hóa EditText và đặt lại văn bản của Button
                else {
                    editText_Name.setEnabled(false);
                    //button.setText("Chỉnh sửa");
                    editText_Name.setBackgroundColor(Color.parseColor("#f5c3c0"));
                }
            }
        });

        //edit Date birth


        editDateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nếu EditText không được kích hoạt, kích hoạt nó và thay đổi văn bản của Button
                if (!editText_DateBirth.isEnabled()) {
                    editText_DateBirth.setEnabled(true);
                    editText_DateBirth.setBackgroundColor(Color.parseColor("#f09e98"));

                    //button.setText("Lưu");
                }
                // Ngược lại, vô hiệu hóa EditText và đặt lại văn bản của Button
                else {
                    editText_DateBirth.setEnabled(false);
                    //button.setText("Chỉnh sửa");
                    editText_DateBirth.setBackgroundColor(Color.parseColor("#f5c3c0"));
                }

            }
        });

        //edit Phone number
        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nếu EditText không được kích hoạt, kích hoạt nó và thay đổi văn bản của Button
                if (!editText_Phone.isEnabled()) {
                    editText_Phone.setEnabled(true);
                    editText_Phone.setBackgroundColor(Color.parseColor("#f09e98"));

                    //button.setText("Lưu");
                }
                // Ngược lại, vô hiệu hóa EditText và đặt lại văn bản của Button
                else {
                    editText_Phone.setEnabled(false);
                    //button.setText("Chỉnh sửa");
                    editText_Phone.setBackgroundColor(Color.parseColor("#f5c3c0"));
                }

            }
        });
        //edit Email
        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nếu EditText không được kích hoạt, kích hoạt nó và thay đổi văn bản của Button
                if (!editText_Email.isEnabled()) {
                    editText_Email.setEnabled(true);
                    editText_Email.setBackgroundColor(Color.parseColor("#f09e98"));

                    //button.setText("Lưu");
                }
                // Ngược lại, vô hiệu hóa EditText và đặt lại văn bản của Button
                else {
                    editText_Email.setEnabled(false);
                    //button.setText("Chỉnh sửa");
                    editText_Email.setBackgroundColor(Color.parseColor("#f5c3c0"));
                }

            }
        });
        //edit ID_card

        editIDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nếu EditText không được kích hoạt, kích hoạt nó và thay đổi văn bản của Button
                if (!editText_IDCard.isEnabled()) {
                    editText_IDCard.setEnabled(true);
                    editText_IDCard.setBackgroundColor(Color.parseColor("#f09e98"));

                    //button.setText("Lưu");
                }
                // Ngược lại, vô hiệu hóa EditText và đặt lại văn bản của Button
                else {
                    editText_IDCard.setEnabled(false);
                    //button.setText("Chỉnh sửa");
                    editText_IDCard.setBackgroundColor(Color.parseColor("#f5c3c0"));
                }

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }
}