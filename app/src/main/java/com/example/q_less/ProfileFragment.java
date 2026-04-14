package com.example.q_less;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private TextInputEditText etName;
    private TextView tvUserEmail;
    private MaterialButton btnApplyChanges;
    private MaterialButton btnGoToHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etName = view.findViewById(R.id.etName);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);
        btnApplyChanges = view.findViewById(R.id.btnApplyChanges);
        btnGoToHistory = view.findViewById(R.id.btnGoToHistory);

        // Load current user data
        UserManager userManager = UserManager.getInstance();
        etName.setText(userManager.getName());
        tvUserEmail.setText(userManager.getEmail());

        btnApplyChanges.setOnClickListener(v -> {
            String newName = etName.getText().toString().trim();
            if (newName.isEmpty()) {
                Toast.makeText(getContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                userManager.setName(newName);
                Toast.makeText(getContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoToHistory.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(getActivity(), OrderHistoryActivity.class);
            startActivity(intent);
        });

        return view;
    }
}