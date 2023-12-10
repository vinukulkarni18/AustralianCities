
Getting Started 
Australian Cities App

Overview

This Android application is built using Jetpack Compose, MVVM architecture, Coroutines, Hilt Dagger, and Unit Tests. The primary purpose of the app is to display a list of Australian cities. The project is organized with a modular structure, including a separate module for handling data fetching from local or in future remote sources.

Features

Jetpack Compose UI.
MVVM Architecture
Coroutines
Hilt Dagger
Unit Tests
Dark Mode support
Data Fetching Module: The project is structured with a separate module responsible for fetching data from local and remote sources. This promotes code reusability and maintainability.
Project Structure

The project is organized into the following modules:

app: The main Android application module that contains the UI implementation using Jetpack Compose and MVVM architecture.
australiancitieslibrary: A separate module responsible for handling data fetching from local and remote sources. This includes repository, data sources, and other related components.

Getting Started

To run the project locally, follow these steps:

1. Clone the repository
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

Dependencies

The major dependencies used in this project include:

Jetpack Compose
ViewModel
LiveData
Hilt Dagger
Coroutines

For a detailed list of dependencies, please refer to the build.gradle files in each module.

Unit Tests

The project includes a set of unit tests located in the test directories. These tests cover the essential functionalities of the app to ensure its robustness.

To run the unit tests, use the following command:

./gradlew test

For quick reference, you can refer following files
AustralianCities.apk
AustralianCities.mp4

