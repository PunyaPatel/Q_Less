package com.example.q_less;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView rvOrderHistory;
    private OrderAdapter adapter;
    private List<Order> orderList;
    private DatabaseHelper dbHelper;
    private TextView tvNoOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        rvOrderHistory = findViewById(R.id.rv_order_history);
        tvNoOrders = findViewById(R.id.tv_no_orders);
        dbHelper = new DatabaseHelper(this);
        orderList = new ArrayList<>();

        rvOrderHistory.setLayoutManager(new LinearLayoutManager(this));
        loadOrders();
    }

    private void loadOrders() {
        int userId = UserManager.getInstance().getId();
        Cursor cursor = dbHelper.getUserOrders(userId);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                double totalPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TOTAL_PRICE));
                int status = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STATUS));
                String timestamp = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TIMESTAMP));
                
                orderList.add(new Order(id, userId, totalPrice, status, timestamp));
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (orderList.isEmpty()) {
            tvNoOrders.setVisibility(View.VISIBLE);
            rvOrderHistory.setVisibility(View.GONE);
        } else {
            tvNoOrders.setVisibility(View.GONE);
            rvOrderHistory.setVisibility(View.VISIBLE);
            adapter = new OrderAdapter(orderList, this::showOrderDetails);
            rvOrderHistory.setAdapter(adapter);
        }
    }

    private void showOrderDetails(Order order) {
        StringBuilder details = new StringBuilder();
        details.append("Order Status: ").append(order.getStatusString()).append("\n");
        details.append("Time: ").append(order.getTimestamp()).append("\n\n");
        details.append("Items:\n");

        android.database.sqlite.SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_ORDER_ITEMS + 
                " WHERE " + DatabaseHelper.COLUMN_ORDER_ID + "=?", new String[]{String.valueOf(order.getId())});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FOOD_NAME));
                int qty = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUANTITY));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRICE));
                details.append(String.format(Locale.getDefault(), "• %s x%d - Rs. %.2f\n", name, qty, price * qty));
            } while (cursor.moveToNext());
            cursor.close();
        }

        details.append("\nTotal: Rs. ").append(String.format(Locale.getDefault(), "%.2f", order.getTotalPrice()));

        new MaterialAlertDialogBuilder(this)
                .setTitle("Order #" + order.getId() + " Details")
                .setMessage(details.toString())
                .setPositiveButton("Close", null)
                .show();
    }
}