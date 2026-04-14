package com.example.q_less;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Qless.db";
    private static final int DATABASE_VERSION = 1;

    // Tables
    public static final String TABLE_USERS = "users";
    public static final String TABLE_STALLS = "stalls";
    public static final String TABLE_MENU_ITEMS = "menu_items";
    public static final String TABLE_ORDERS = "orders";
    public static final String TABLE_ORDER_ITEMS = "order_items";

    // Common columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    // Users columns
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Stalls columns
    public static final String COLUMN_LOGO_RES = "logo_res";

    // Menu Items columns
    public static final String COLUMN_STALL_ID = "stall_id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE_RES = "image_res";
    public static final String COLUMN_NAME_RES = "name_res";

    // Orders columns
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_TOTAL_PRICE = "total_price";
    public static final String COLUMN_STATUS = "status"; // 0: Under Preparation, 1: Prepared
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // Order Items columns
    public static final String COLUMN_ORDER_ID = "order_id";
    public static final String COLUMN_FOOD_NAME = "food_name";
    public static final String COLUMN_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users table
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUsersTable);

        // Create Stalls table
        String createStallsTable = "CREATE TABLE " + TABLE_STALLS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LOGO_RES + " INTEGER)";
        db.execSQL(createStallsTable);

        // Create Menu Items table
        String createMenuTable = "CREATE TABLE " + TABLE_MENU_ITEMS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STALL_ID + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NAME_RES + " INTEGER, " +
                COLUMN_PRICE + " REAL, " +
                COLUMN_IMAGE_RES + " INTEGER)";
        db.execSQL(createMenuTable);

        // Create Orders table
        String createOrdersTable = "CREATE TABLE " + TABLE_ORDERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_ID + " INTEGER, " +
                COLUMN_TOTAL_PRICE + " REAL, " +
                COLUMN_STATUS + " INTEGER DEFAULT 0, " +
                COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(createOrdersTable);

        // Create Order Items table
        String createOrderItemsTable = "CREATE TABLE " + TABLE_ORDER_ITEMS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ORDER_ID + " INTEGER, " +
                COLUMN_FOOD_NAME + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRICE + " REAL)";
        db.execSQL(createOrderItemsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STALLS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_ITEMS);
        onCreate(db);
    }

    // User Methods
    public long registerUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        return db.insert(TABLE_USERS, null, values);
    }

    public Cursor loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{email, password});
    }

    // Order Methods
    public long placeOrder(int userId, double totalPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_TOTAL_PRICE, totalPrice);
        values.put(COLUMN_STATUS, 0); // Under Preparation
        return db.insert(TABLE_ORDERS, null, values);
    }

    public void addOrderItem(long orderId, String foodName, int quantity, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_ID, orderId);
        values.put(COLUMN_FOOD_NAME, foodName);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_PRICE, price);
        db.insert(TABLE_ORDER_ITEMS, null, values);
    }

    public Cursor getUserOrders(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ORDERS + " WHERE " + COLUMN_USER_ID + "=? ORDER BY " + COLUMN_TIMESTAMP + " DESC", new String[]{String.valueOf(userId)});
    }

    public Cursor getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ORDERS + " ORDER BY " + COLUMN_TIMESTAMP + " DESC", null);
    }

    public void updateOrderStatus(long orderId, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATUS, status);
        db.update(TABLE_ORDERS, values, COLUMN_ID + "=?", new String[]{String.valueOf(orderId)});
    }
}