# QLess - Smart Canteen Application

QLess is a professional Android application designed to streamline the canteen ordering process at Indus University. It allows students to browse menus from various stalls, place orders, and track their preparation status digitally, eliminating long queues.

## Recent Updates

- **Order History:** A dedicated activity for users to view their past orders with detailed items, prices, and status.
- **Pizza Paradise Stall:** A new specialized pizza stall with a premium menu including Margherita, Farmhouse, Tandoori Paneer, and more.
- **Enhanced UI/UX:** Improved color contrast, refined navigation icon states, and polished layouts for a more professional experience.
- **Student Profile:** A redesigned profile section with a "Verified Student" badge and intuitive navigation to order history.

## Features

- **User Authentication:** Secure login and registration for Indus University students using their institutional emails.
- **Stall Browsing:** View multiple food stalls within the campus, including Jaiswal Canteen, Tea Post, Shiv Snacks, Indu Cafe, and the new **Pizza Paradise**.
- **Detailed Order History:** Clickable history items that show exactly what was ordered, including individual item prices and quantities.
- **Menu Management:** Explore detailed menus with prices and specific food images for each item.
- **Digital Cart:** Add and manage food items in a persistent cart with real-time total calculation.
- **Order Tracking:** Automated token generation and real-time order status tracking (Under Preparation vs. Prepared).
- **SQLite Integration:** Robust local database management for users, stalls, menus, and order history tracking.
- **Professional UI:** Modern design with a custom Material 3 color palette and consistent branding.
- **Multi-language Support:** Localization support for English, Hindi, Gujarati, and Japanese.

## Technical Stack

- **Platform:** Android (Java)
- **UI Components:** Material Design 3, ConstraintLayout, RecyclerView, DrawerLayout, BottomNavigationView, CoordinatorLayout.
- **Database:** SQLite (with DatabaseHelper) for persistent local storage of users and orders.
- **Architecture:** Singleton pattern for managers, Fragment-based and Activity-based navigation.

## Setup Instructions

1. Clone the repository: `git clone <repository-url>`
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Run the application on an emulator or a physical Android device (API Level 24+ recommended).

## Color Palette

The app features a professional blue-themed palette:
- **Primary:** #042B51 (Deep Blue)
- **Secondary:** #305E84 (Medium Blue)
- **Accent:** #7ABAE0 (Sky Blue)
- **Background:** #F5F9FC (Soft Blue-White)

## Screenshots

The repository includes UI review screenshots (`ui_review_1.jpg`, `review_screen_1.jpg`, etc.) for reference.

---
Developed for Indus University.
