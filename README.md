# TileOrchestrator-QuickSettings

A modern Android demo showcasing how to integrate a Quick Settings Tile
with a Jetpack Compose UI using current platform best practices.

This project demonstrates how to launch a Compose-based AI interface
directly from the system Quick Settings panel while maintaining backward
compatibility and Android 13+ compliance.

------------------------------------------------------------------------

## ✨ Overview

TileOrchestrator explores system-level integration using:

-   `TileService` API
-   Android 13+ compliant activity launching
-   Backward-compatible implementation (API 24+)
-   Jetpack Compose UI
-   Material 3 with dynamic color support
-   Simulated streaming AI response

The goal of this project is to demonstrate clean platform integration
without overengineering or introducing unnecessary architectural layers.

------------------------------------------------------------------------

## 🏗 Architecture Decisions

### 1. Quick Settings Integration

-   Uses `TileService`
-   Handles API 34+ `PendingIntent` launch requirement
-   Gracefully falls back to deprecated method for lower API levels
-   No hidden OEM or unsupported intents

### 2. UI Layer

-   100% Jetpack Compose
-   Material 3
-   Dynamic color (Android 12+)
-   Dark mode supported
-   Keyboard autofocus on launch

### 3. Compatibility Strategy

``` kotlin
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
    startActivityAndCollapse(pendingIntent)
} else {
    @Suppress("DEPRECATION")
    startActivityAndCollapse(intent)
}
```

This ensures: - No crashes - No API violations - Full support from API
24 onward

------------------------------------------------------------------------

## 📱 How to Use

1.  Install the app
2.  Pull down the notification shade
3.  Tap **Edit (✏️)**
4.  Drag **Quick AI** into active tiles
5.  Tap the tile to launch the Compose AI screen

------------------------------------------------------------------------

## 🔧 Tech Stack

-   Kotlin
-   Android SDK 34
-   Jetpack Compose
-   Material 3
-   TileService API

Minimum SDK: 24\
Target SDK: 34

------------------------------------------------------------------------

## 🎯 Why This Project Exists

Quick Settings Tiles are powerful but underused entry points into
Android applications.

This demo focuses on:

-   Correct API usage
-   Modern Compose UI
-   Clean separation of concerns
-   Production-safe implementation
-   Backward compatibility awareness

It intentionally avoids unnecessary frameworks to keep the focus on
platform-level integration.

------------------------------------------------------------------------

## 🚀 Future Enhancements

-   ViewModel-based state management
-   Voice input integration
-   Tile state animation based on background work
-   Bottom sheet assistant mode

------------------------------------------------------------------------

## 📌 Key Takeaway

TileOrchestrator is not just a UI demo --- it demonstrates responsible
system API usage with modern Android development practices.
