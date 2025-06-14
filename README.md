# CMP-Permissions

<img width="1066" alt="Screenshot 2025-06-14 at 8 54 01 PM" src="https://github.com/user-attachments/assets/abb0f877-34f9-47c7-a886-582b90a54a74" />

**A practical demonstration of handling permissions in a Multiplatform Compose application using Moko-Permissions.**

This project showcases how to request and manage various device permissions (Camera, Location, Gallery, Contacts, Record Audio) across Android and iOS platforms within a single codebase using Jetpack Compose Multiplatform and the [Moko-Permissions](https://github.com/icerockdev/moko-permissions) library.

## ✨ Features

* **Multiplatform Permission Handling:** Seamlessly manage permissions on both Android and iOS from a shared Kotlin codebase.
* **Moko-Permissions Integration:** Leverages the robust `moko-permissions` library for simplified permission requests and status checking.
* **Centralized ViewModel Logic:** A single `AppViewModel` handles all permission-related logic, making it easy to extend and maintain.
* **StateFlow for UI Updates:** Uses Kotlin Flow's `StateFlow` to expose permission states to the UI, ensuring reactive updates.
* **Clear UI States:** Defines `UIPermissionState` to represent permission statuses: `UNKNOWN`, `GRANTED`, `DENIED`, and `DENIED_PERMANENTLY`.
* **Automatic App Settings Redirection:** Automatically guides the user to app settings if a permission is permanently denied.
* **Example Usage:** Provides a clear example of how to check and request permissions in a Compose Multiplatform UI.

---

## 🚀 Getting Started

To run and explore this project, follow these steps:

### Prerequisites

* [Android Studio Giraffe | 2022.3.1](https://developer.android.com/studio) or newer (for Compose Multiplatform support)
* [Xcode](https://developer.apple.com/xcode/) (for building and running the iOS app)
* Kotlin 1.9.23+

### Running the Application

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/AhmedOmara14/CMP-Permissions.git](https://github.com/AhmedOmara14/CMP-Permissions.git)
    cd CMP-Permissions
    ```

2.  **Open in Android Studio:**
    Open the cloned project in Android Studio.

3.  **Run on Android:**
    * Select the `androidApp` run configuration.
    * Choose an Android Emulator or physical device.
    * Click the 'Run' button (▶️).

4.  **Run on iOS:**
    * Ensure you have Xcode installed.
    * Select the `iosApp` run configuration (e.g., `iosApp (iOS Simulator)`).
    * Choose an iOS Simulator or a connected physical device.
    * Click the 'Run' button (▶️). Android Studio will build the iOS project and deploy it to the selected target.

---

## ⚙️ How Permissions are Handled

The core permission logic resides in the `shared` module, specifically within `AppViewModel.kt`.

1.  **`PermissionsController`:** The `AppViewModel` is initialized with `PermissionsController` from `moko-permissions`, which is the entry point for permission operations. This is provided by platform-specific implementations (e.g., in `androidApp` and `iosApp`).

2.  **`UIPermissionState`:**
    An enum `UIPermissionState` (defined in `utils/UIPermissionState.kt`) is used to represent the different states a permission can be in from the UI's perspective:
    * `UNKNOWN`: Initial or indeterminate state.
    * `GRANTED`: Permission has been granted.
    * `DENIED`: Permission has been denied once (can be asked again).
    * `DENIED_PERMANENTLY`: Permission has been denied permanently, requiring the user to go to app settings.

3.  **Centralized State Management:**
    A `MutableStateFlow<Map<Permission, UIPermissionState>>` named `_permissionStates` is used in `AppViewModel` to hold the current status of all tracked permissions. This allows for a single source of truth for permission states.

4.  **`checkPermissions()`:**
    This function checks the current status of specified permissions (e.g., on app start) and updates the `_permissionStates` map.

5.  **`requestPermission()`:**
    This generic function handles the actual request for a given `Permission`. It wraps the `moko-permissions` `providePermission` call in a `try-catch` block to gracefully handle different denial scenarios:
    * `DeniedException`: Updates the state to `DENIED`.
    * `DeniedAlwaysException`: Updates the state to `DENIED_PERMANENTLY` and automatically calls `permissionsController.openAppSettings()` to prompt the user to enable it from settings.

6.  **UI Integration:**
    The `App.kt` composable observes the individual `StateFlow` properties (e.g., `cameraPermissionState`, `locationPermissionState`) derived from the `_permissionStates` map to update the UI based on the current permission status. Buttons trigger the corresponding `requestPermission()` calls in the `ViewModel`.

---

## 🤝 Contributing

Contributions are welcome! If you find a bug or want to add a new feature, please open an issue or submit a pull request.

---

## 🙏 Acknowledgements

* [Jetpack Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) by JetBrains
* [Moko-Permissions](https://github.com/icerockdev/moko-permissions) by IceRock Development

