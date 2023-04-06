package com.midterm.restaurant_app.view;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.FragmentAccountBinding;
import com.midterm.restaurant_app.model.Upload;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {
    private LinearLayout  navSer, navHis, navHome;

    private static final int PICK_IMAGE_REQUESR = 1;
    private ImageView ivUpload;
    private CircleImageView ivAvatar;
    private Uri avatarUri;
    private Button btnSave;
    private FragmentAccountBinding binding;


    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    public AccountFragment() {
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

        ivUpload = view.findViewById(R.id.iv_upload);
        ivAvatar = view.findViewById(R.id.cirv_avatar);
        btnSave = view.findViewById(R.id.btn_save);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        binding = FragmentAccountBinding.inflate(getLayoutInflater());

        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });


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

        ImageView editName = binding.penEditName;
        EditText editText_Name = binding.editName;
        ImageView editDateBirth = binding.penEditDateBirth;
        EditText editText_DateBirth = binding.editDateBirth;
        ImageView editPhone = binding.penEditPhoneNumber;
        EditText editText_Phone = binding.editPhone;
        ImageView editEmail = binding.penEditEmail;
        EditText editText_Email = binding.editMail;
        ImageView editIDCard = binding.penEditIDCard;
        EditText editText_IDCard= binding.editIDCard;


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
        editDateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText_DateBirth.isEnabled()) {
                    editText_DateBirth.setEnabled(true);
                    editText_DateBirth.setBackgroundColor(Color.parseColor("#f09e98"));
                }
                else {
                    editText_DateBirth.setEnabled(false);
                    editText_DateBirth.setBackgroundColor(Color.parseColor("#f5c3c0"));
                }

            }
        });
        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText_Phone.isEnabled()) {
                    editText_Phone.setEnabled(true);
                    editText_Phone.setBackgroundColor(Color.parseColor("#f09e98"));
                }
                else {
                    editText_Phone.setEnabled(false);
                    editText_Phone.setBackgroundColor(Color.parseColor("#f5c3c0"));
                }

            }
        });
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

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUESR);
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = this.getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if(avatarUri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(avatarUri));
            fileReference.putFile(avatarUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(getContext(), "Upload successfull !!!",Toast.LENGTH_SHORT).show();

                                    Upload upload = new Upload("avatar",taskSnapshot.getUploadSessionUri().toString());
                                    String uploadId = databaseReference.push().getKey();
                                    databaseReference.child(uploadId).setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        else{
            Toast.makeText(this.getContext(),"No file selected",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUESR && resultCode == RESULT_OK
                        && data != null &&data.getData() !=null){
            avatarUri = data.getData();
            Picasso.with(this.getContext()).load(avatarUri).into(ivAvatar);
//            ivAvatar.setImageURI();
        }
    }
}