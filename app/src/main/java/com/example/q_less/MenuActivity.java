package com.example.q_less;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView rvMenu;
    private MaterialToolbar toolbar;
    private Stall stall;
    private MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        stall = (Stall) getIntent().getSerializableExtra("stall");

        toolbar = findViewById(R.id.toolbar);
        rvMenu = findViewById(R.id.rvMenu);

        if (stall != null) {
            toolbar.setTitle(stall.getName());
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        
        List<FoodItem> foodItems = new ArrayList<>();
        
        if (stall != null) {
            String name = stall.getName();
            if (name.equals(getString(R.string.stall_jaiswal))) {
                foodItems.add(new FoodItem(R.string.food_samosa, "Samosa", 15.0, R.drawable.samosa));
                foodItems.add(new FoodItem(R.string.food_samosa_chat, "Samosa Chat", 30.0, R.drawable.samosa));
                foodItems.add(new FoodItem(R.string.food_dosa, "Dosa", 15.0, R.drawable.dosa));
                foodItems.add(new FoodItem(R.string.food_masala_dosa, "Masala Dosa", 200.0, R.drawable.masala_dosa));
                foodItems.add(new FoodItem(R.string.food_sandwich, "Sandwich", 100.0, R.drawable.sandwich));
                foodItems.add(new FoodItem(R.string.food_grilled_sandwich, "Grilled Cheese Sandwich", 200.0, R.drawable.grilled_sandwich));
                foodItems.add(new FoodItem(R.string.food_packets, "Packets", 10.0, R.drawable.packets));
                foodItems.add(new FoodItem(R.string.food_thali, "Fix Thali", 80.0, R.drawable.thali));
            } else if (name.equals(getString(R.string.stall_nestle))) {
                foodItems.add(new FoodItem(R.string.food_maggie, "Maggie", 55.0, R.drawable.maggie));
                foodItems.add(new FoodItem(R.string.food_masala_maggie, "Masala Maggie", 80.0, R.drawable.maggie));
                foodItems.add(new FoodItem(R.string.food_kitkat, "Kit-Kat", 10.0, R.drawable.kitkat));
                foodItems.add(new FoodItem(R.string.food_cheese_maggie, "Cheese Maggie", 100.0, R.drawable.maggie));
                foodItems.add(new FoodItem(R.string.food_coffee, "Coffee", 100.0, R.drawable.coffee));
            } else if (name.equals(getString(R.string.stall_tea_post))) {
                foodItems.add(new FoodItem(R.string.food_tea, "Tea", 15.0, R.drawable.tea));
                foodItems.add(new FoodItem(R.string.food_cold_coffee, "Cold Coffee", 110.0, R.drawable.cold_coffee));
                foodItems.add(new FoodItem(R.string.food_mexican_puff, "Mexican Puff", 70.0, R.drawable.puff));
                foodItems.add(new FoodItem(R.string.food_cheese_puff, "Cheese Puff", 90.0, R.drawable.puff));
                foodItems.add(new FoodItem(R.string.food_lemonade, "Lemonade", 60.0, R.drawable.lemonade));
                foodItems.add(new FoodItem(R.string.food_fries_peri, "Peri-Peri Fries", 150.0, R.drawable.fries));
                foodItems.add(new FoodItem(R.string.food_fries, "Fries", 100.0, R.drawable.fries));
            } else if (name.equals(getString(R.string.stall_indu_cafe))) {
                foodItems.add(new FoodItem(R.string.food_aloo_parotha, "Aloo Parotha", 150.0, R.drawable.aloo));
                foodItems.add(new FoodItem(R.string.food_paneer_tikka, "Paneer Tikka", 200.0, R.drawable.paneer_tikka));
                foodItems.add(new FoodItem(R.string.food_manchuriyan, "Manchuryan", 150.0, R.drawable.manchuriyan));
            } else if (name.equals(getString(R.string.stall_shiv_snacks))) {
                foodItems.add(new FoodItem(R.string.food_chole_bhatore, "Chole Bhatore", 100.0, R.drawable.chole));
                foodItems.add(new FoodItem(R.string.food_puff, "Puff", 50.0, R.drawable.puff));
                foodItems.add(new FoodItem(R.string.food_manchuriyan, "Manchuryan", 90.0, R.drawable.manchuriyan));
            } else if (name.equals(getString(R.string.stall_pizza_paradise))) {
                foodItems.add(new FoodItem(R.string.food_margherita_pizza, "Margherita Pizza", 199.0, R.drawable.margherita_pizza));
                foodItems.add(new FoodItem(R.string.food_veggie_pizza, "Veggie Paradise Pizza", 299.0, R.drawable.veggie_paradise));
                foodItems.add(new FoodItem(R.string.food_cheese_pizza, "Double Cheese Pizza", 349.0, R.drawable.double_cheese));
                foodItems.add(new FoodItem(R.string.food_paneer_pizza, "Paneer Tikka Pizza", 399.0, R.drawable.paneer_tikka_pizza));
                foodItems.add(new FoodItem(R.string.food_farmhouse_pizza, "Farmhouse Pizza", 329.0, R.drawable.farm_house));
                foodItems.add(new FoodItem(R.string.food_mexican_pizza, "Mexican Wave Pizza", 359.0, R.drawable.mexican_wave));
                foodItems.add(new FoodItem(R.string.food_tandoori_pizza, "Tandoori Paneer Pizza", 419.0, R.drawable.tandoori_paneer_pizza));
            }
        }

        adapter = new MenuAdapter(foodItems, (item, position) -> {
            CartManager.getInstance().addItem(item);
            String message = getString(R.string.added_to_cart, item.getName());
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            item.setQuantity(1);
            if (position != RecyclerView.NO_POSITION) {
                adapter.notifyItemChanged(position);
            }
        });
        rvMenu.setAdapter(adapter);
    }
}