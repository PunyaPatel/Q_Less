package com.example.q_less;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<FoodItem> cartItems;

    public CartAdapter(List<FoodItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        FoodItem item = cartItems.get(position);
        
        String displayName = item.getName();
        if (item.getNameRes() != 0) {
            displayName = holder.itemView.getContext().getString(item.getNameRes());
        }
        holder.tvName.setText(displayName);
        holder.tvPrice.setText(String.format("Rs. %.2f", item.getPrice() * item.getQuantity()));
        holder.tvQuantity.setText("Qty: " + item.getQuantity());
        
        // Use local resource instead of URL for stability and offline support
        if (item.getImageRes() != 0) {
            holder.ivImage.setImageResource(item.getImageRes());
        } else if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .placeholder(R.drawable.ic_food)
                .error(R.drawable.ic_food)
                .centerCrop()
                .into(holder.ivImage);
        } else {
            holder.ivImage.setImageResource(R.drawable.ic_food);
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQuantity;
        ImageView ivImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCartItemName);
            tvPrice = itemView.findViewById(R.id.tvCartItemPrice);
            tvQuantity = itemView.findViewById(R.id.tvCartItemQuantity);
            ivImage = itemView.findViewById(R.id.ivCartItemImage);
        }
    }
}