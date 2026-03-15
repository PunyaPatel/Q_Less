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

public class StallAdapter extends RecyclerView.Adapter<StallAdapter.StallViewHolder> {

    private List<Stall> stallList;
    private OnStallClickListener listener;

    public interface OnStallClickListener {
        void onStallClick(Stall stall);
    }

    public StallAdapter(List<Stall> stallList, OnStallClickListener listener) {
        this.stallList = stallList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stall, parent, false);
        return new StallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StallViewHolder holder, int position) {
        Stall stall = stallList.get(position);
        holder.tvStallName.setText(stall.getName());
        
        // Use Glide to load and resize the logo
        Glide.with(holder.itemView.getContext())
            .load(stall.getLogoRes())
            .placeholder(R.drawable.ic_stall)
            .error(R.drawable.ic_stall)
            .centerCrop()
            .into(holder.ivStallLogo);
            
        holder.itemView.setOnClickListener(v -> listener.onStallClick(stall));
    }

    @Override
    public int getItemCount() {
        return stallList.size();
    }

    static class StallViewHolder extends RecyclerView.ViewHolder {
        ImageView ivStallLogo;
        TextView tvStallName;

        public StallViewHolder(@NonNull View itemView) {
            super(itemView);
            ivStallLogo = itemView.findViewById(R.id.ivStallLogo);
            tvStallName = itemView.findViewById(R.id.tvStallName);
        }
    }
}