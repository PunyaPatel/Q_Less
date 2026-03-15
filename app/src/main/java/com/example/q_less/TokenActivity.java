package com.example.q_less;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class TokenActivity extends AppCompatActivity {

    private TextView tvTokenNumber;
    private MaterialButton btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        tvTokenNumber = findViewById(R.id.tvTokenNumber);
        btnBackHome = findViewById(R.id.btnBackHome);

        // Generate a random token number between 1 and 100
        int token = new Random().nextInt(100) + 1;
        tvTokenNumber.setText(String.valueOf(token));

        btnBackHome.setOnClickListener(v -> finish());
    }
}