# Fluid Splash 🌊

A Kotlin Multiplatform library for creating beautiful fluid and metaball animations in Compose Multiplatform.

<video controls width="50%">
    <source src="https://github.com/user-attachments/assets/c20e9336-2627-4e06-b5e8-6509a315f7fb" type="video/mp4">
    Your browser does not support the video tag.
</video>

## ✨ Features

- **AnimatedFluidBackground**: A dynamic, flowing background with customizable colors.
- **MetaballBackground**: Smooth, organic-looking metaball animations.
- **FluidCarousel**: An interactive carousel component with fluid transition effects.
- **FluidContainer & FluidCard**: Containers and cards with fluid effects.
- **LiquidFillAnimation**: Progress-style animations with a liquid filling effect.

## 🚀 Getting Started

This project is a Kotlin Multiplatform project targeting **Android** and **iOS**.

### Prerequisites

- [JDK 17+](https://adoptium.net/)
- [Android Studio](https://developer.android.com/studio) or [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Xcode](https://developer.apple.com/xcode/) (for iOS development)

### 📂 Project Structure

- `androidApp`: The Android application that launches the sample.
- `composeApp`: The shared sample UI demonstrating the usage of the library (Android and iOS).
- `fluid-lib`: The core library containing all fluid and metaball components.

## 🛠 Usage

### AnimatedFluidBackground

```kotlin
Box(modifier = Modifier.fillMaxSize()) {
    AnimatedFluidBackground(
        modifier = Modifier.fillMaxSize(),
        primaryColor = Color(0xFF673AB7),
        secondaryColor = Color(0xFFFFB727)
    )
    // Your content here
}
```

### MetaballBackground

```kotlin
Box(modifier = Modifier.fillMaxSize()) {
    MetaballBackground()
    // Your content here
}
```

### LiquidFillAnimation

```kotlin
var progress by remember { mutableStateOf(0.5f) } // 0.0 to 1.0

LiquidFillAnimation(
    progress = progress,
    modifier = Modifier.size(200.dp),
    color = Color(0xFF4FC3F7),
    waveHeight = 20f
)
```

### FluidCard & FluidCardCustom

`FluidCard` provides a pre-defined layout for a card with fluid background:

```kotlin
val config = FluidGraphicConfig(
    background = Background.BackgroundColor(Color(0xFF673AB7)),
    illustration = painterResource(Res.drawable.my_illustration),
    indicatorColor = Color.White
)

FluidCard(
    config = config,
    title = "Hello Fluid",
    subtitle = "This is a fluid card"
)
```

`FluidCardCustom` allows you to define your own content inside a fluid container:

```kotlin
FluidCardCustom(
    background = Background.BackgroundColor(Color(0xFF673AB7))
) {
    // Your custom layout here
    Column {
        Text("Custom Title", color = Color.White)
        Button(onClick = { /* ... */ }) {
            Text("Action")
        }
    }
}
```

### FluidCarousel

`FluidCarousel` is an interactive carousel component that provides smooth fluid transitions between pages. It's often used with `FluidCardCustom`:

```kotlin
val pages = listOf<@Composable () -> Unit>(
    {
        FluidCardCustom(background = Background.BackgroundColor(Color(0xFF673AB7))) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Page 1", color = Color.White)
            }
        }
    },
    {
        FluidCardCustom(background = Background.BackgroundColor(Color(0xFFFFB727))) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Page 2", color = Color.White)
            }
        }
    }
)

FluidCarousel(
    children = pages,
    enableBubble = true, // Optional: Enable bubble effect during transitions
    modifier = Modifier.fillMaxSize()
)
```

## 📱 Running the Sample App

### Android
1. Open the project in Android Studio.
2. Select `androidApp` in the run configuration.
3. Choose an Android emulator or device and click **Run**.

### iOS
1. Open `iosApp/iosApp.xcodeproj` in Xcode OR run from Android Studio/IntelliJ with the Kotlin Multiplatform Mobile plugin.
2. Select the target device/simulator and click **Run**.

> `iosX64` (the Intel Mac iOS Simulator) is not supported: Compose Multiplatform 1.12 no longer publishes artifacts for it. Use a physical device or an Apple Silicon simulator.

## 📖 Learn More
Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html) and [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/).