package com.example.q_less;

import android.content.Intent;
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

import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView rvCart;
    private TextView tvTotalPrice;
    private MaterialButton btnCheckout;
    private CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        
        rvCart = view.findViewById(R.id.rvCart);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        btnCheckout = view.findViewById(R.id.btnCheckout);
        
        rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        
        updateCartUI();

        btnCheckout.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                Toast.makeText(getContext(), "Cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                int userId = UserManager.getInstance().getId();
                if (userId != -1) {
                    DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                    double total = CartManager.getInstance().getTotalPrice();
                    long orderId = dbHelper.placeOrder(userId, total);
                    
                    if (orderId != -1) {
                        for (FoodItem item : CartManager.getInstance().getCartItems()) {
                            dbHelper.addOrderItem(orderId, item.getName(), item.getQuantity(), item.getPrice());
                        }
                        
                        Intent intent = new Intent(getActivity(), TokenActivity.class);
                        intent.putExtra("order_id", orderId);
                        startActivity(intent);
                        
                        CartManager.getInstance().clearCart();
                        updateCartUI();
                    } else {
                        Toast.makeText(getContext(), "Failed to place order", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void updateCartUI() {
        List<FoodItem> items = CartManager.getInstance().getCartItems();
        adapter = new CartAdapter(items);
        rvCart.setAdapter(adapter);
        tvTotalPrice.setText(String.format("Rs. %.2f", CartManager.getInstance().getTotalPrice()));
    }
}