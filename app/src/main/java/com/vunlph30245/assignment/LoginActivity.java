package com.vunlph30245.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vunlph30245.assignment.DAO.UsersDAO;
import com.vunlph30245.assignment.util.SendMail;

public class LoginActivity extends AppCompatActivity {

    private UsersDAO usersDAO;
    private SendMail sendMail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        TextView txtForgotPassword = findViewById(R.id.txtForgotPassword);
        TextView txtSignUp = findViewById(R.id.txtSignUp);
        Button btnLogin = findViewById(R.id.btnLogin);

        usersDAO = new UsersDAO(this);
        sendMail = new SendMail();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                boolean check = usersDAO.checkLogin(username, password);
                if (check) {
                    Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Dang nhap that bai. Vui long kiem tra lai", Toast.LENGTH_SHORT).show();

                }
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDialogForgotPassword();
            }
        });

    }
    private void showDialogForgotPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_forgot, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        EditText edtEmail = view.findViewById(R.id.edtEmail);
        Button btnSend = view.findViewById(R.id.btnSend);
        Button btnCancel2 = view.findViewById(R.id.btnCancel2);

        btnCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = usersDAO.forgotPassword(email);
                //Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
                if (password.equals("")){
                    Toast.makeText(LoginActivity.this, "Khong tim thay tai khoan", Toast.LENGTH_SHORT).show();
                }else{
                    sendMail.Send(LoginActivity.this,email,"Password", "Mat khau cua ban la: " + password);
                    dialog.dismiss();
                }
            }
        });
    }
}