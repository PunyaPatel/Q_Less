package com.example.q_less;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<FoodItem> foodItems;
    private OnAddToCartClickListener listener;

    public interface OnAddToCartClickListener {
        void onAddToCart(FoodItem item, int position);
    }

    public MenuAdapter(List<FoodItem> foodItems, OnAddToCartClickListener listener) {
        this.foodItems = foodItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        FoodItem item = foodItems.get(position);

        String displayName = item.getName();
        if (item.getNameRes() != 0) {
            displayName = holder.itemView.getContext().getString(item.getNameRes());
        }
        holder.tvFoodName.setText(displayName);
        holder.tvFoodPrice.setText(String.format("Rs. %.2f", item.getPrice()));
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));
        
        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .placeholder(R.drawable.ic_food)
                .error(R.drawable.ic_food)
                .centerCrop()
                .into(holder.ivFoodImage);
        } else {
            holder.ivFoodImage.setImageResource(item.getImageRes());
        }
        
        holder.btnAdd.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.tvQuantity.setText(String.valueOf(item.getQuantity()));
        });

        holder.btnRemove.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.tvQuantity.setText(String.valueOf(item.getQuantity()));
            }
        });

        holder.btnAddToCart.setOnClickListener(v -> listener.onAddToCart(item, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoodImage;
        TextView tvFoodName, tvFoodPrice, tvQuantity;
        MaterialButton btnAddToCart;
        ImageButton btnAdd, btnRemove;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoodImage = itemView.findViewById(R.id.ivFoodImage);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}