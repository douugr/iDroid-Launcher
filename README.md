# iPodLauncher

An Android launcher that brings back the classic iPod interface, built with Jetpack Compose.

## Features

*   **Classic iPod Interface:** Navigate your phone using a nostalgic click wheel interface.
*   **Browse Your Media:** Access your Music, Photos, and Videos stored on your device.
*   **App Drawer:** A simple list of all your installed applications.
*   **File Access:** Browse your Downloads and Documents folders.
*   **Now Playing:** A functional Now Playing screen with controls for next and previous songs.

## Screenshots

*(You can add screenshots or GIFs of the app in action here)*

## Getting Started

To build and run this project, you'll need Android Studio.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/iPodLauncher.git
    ```
2.  **Open in Android Studio:** Open the cloned project in Android Studio.
3.  **Add Coil Dependency:** This project uses the [Coil](https://coil-kt.github.io/coil/) library to load images. Please add the following line to your `app/build.gradle` file inside the `dependencies` block:
    ```groovy
    implementation 'io.coil-kt:coil-compose:2.4.0'
    ```
4.  **Sync and Run:** Sync the Gradle files and run the app on an emulator or a physical device.

## Tech Stack

*   [Kotlin](https://kotlinlang.org/)
*   [Jetpack Compose](https://developer.android.com/jetpack/compose)
*   [Material 3](https://m3.material.io/)
*   [Coil](https://coil-kt.github.io/coil/) for image loading.

## Contributing

Contributions are welcome! If you have any ideas, suggestions, or bug reports, please feel free to open an issue or submit a pull request.
