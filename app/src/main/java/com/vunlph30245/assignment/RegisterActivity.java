package com.vunlph30245.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vunlph30245.assignment.DAO.UsersDAO;

public class RegisterActivity extends AppCompatActivity {

    private UsersDAO usersDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtRePassword = findViewById(R.id.edtRePassword);
        EditText edtFullName = findViewById(R.id.edtFullName);
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnCancel = findViewById(R.id.btnCancel);

        usersDAO = new UsersDAO(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();
                String fullName = edtFullName.getText().toString();

                if (!password.equals(rePassword)){
                    Toast.makeText(RegisterActivity.this, "Mat khau khong trung khop", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = usersDAO.Register(username, password, fullName);
                    if (check){
                        Toast.makeText(RegisterActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}