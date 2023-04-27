# Contacts
A simple contact App featuring some of Modern Android Development tools
<p align="center">
  <img src="https://user-images.githubusercontent.com/18175202/234982482-fa5d0490-08a4-42e0-be97-338c16a47d9d.jpg" width="300" />
  <img src="https://user-images.githubusercontent.com/18175202/234982519-24373330-76e9-46e4-a345-1a5c569466ac.jpg" width="300" /> 
</p>

## Development setup

You require the latest Android Studio Flamingo (for Gradle 8.1), so please do update or download it [here](https://developer.android.com/studio)

First, clone the repository: 
```
https://github.com/ralphevmanzano/Contacts.git
```
Import the project in Android Studio and make sure the project uses Gradle JDK version 17 (this comes pre-installed with the latest Android Studio Flamingo)
```
File > Project Structure > SDK Location > Gradle Settings
```
![image_2023-04-28_03-36-28](https://user-images.githubusercontent.com/18175202/234985295-8b50eb82-38bf-44a4-b94a-9318ef40d814.png)
Now you can build and run the app

To run the tests, copy and paste this line in Android Studio's Terminal

For Mac users:
```
./gradlew test -Dorg.gradle.java.home=/JDK_PATH
```
For Windows users:
```
gradlew test -Dorg.gradle.java.home=/JDK_PATH
```

### Tech Stack
- Single Activity, MVVM Architecture
- Application is written entirely in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Dependency Injecy using [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Room](https://developer.android.com/training/data-storage/room) for local storage
- [Jetpack Navigation](https://developer.android.com/guide/navigation) for in-app navigations
