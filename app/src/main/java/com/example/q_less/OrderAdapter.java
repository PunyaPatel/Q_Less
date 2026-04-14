package com.example.q_less;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orders;
    private OnOrderClickListener listener;

    public interface OnOrderClickListener {
        void onOrderClick(Order order);
    }

    public OrderAdapter(List<Order> orders, OnOrderClickListener listener) {
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.tvOrderId.setText(String.format(Locale.getDefault(), "Order #%d", order.getId()));
        holder.tvOrderTimestamp.setText(order.getTimestamp());
        holder.tvOrderStatus.setText(order.getStatusString().toUpperCase());
        holder.tvOrderTotalValue.setText(String.format(Locale.getDefault(), "Rs. %.2f", order.getTotalPrice()));
        
        if (order.getStatus() == 0) {
            holder.tvOrderStatus.setAlpha(0.7f);
        } else {
            holder.tvOrderStatus.setAlpha(1.0f);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onOrderClick(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvOrderTimestamp, tvOrderStatus, tvOrderTotalValue;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderTimestamp = itemView.findViewById(R.id.tvOrderTimestamp);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvOrderTotalValue = itemView.findViewById(R.id.tvOrderTotalValue);
        }
    }
}