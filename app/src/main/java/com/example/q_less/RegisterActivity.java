package com.example.q_less;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etName, etEmail, etPassword;
    private MaterialButton btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!email.endsWith("@indusuni.ac.in")) {
                Toast.makeText(this, "Registration requires @indusuni.ac.in email", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseHelper dbHelper = new DatabaseHelper(this);
                long id = dbHelper.registerUser(name, email, password);
                
                if (id != -1) {
                    UserManager.getInstance().setUser((int)id, name, email);
                    Toast.makeText(this, "Registration Successful for " + name, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Registration failed or email already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvLogin.setOnClickListener(v -> finish());
    }
}