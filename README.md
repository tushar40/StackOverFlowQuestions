# StackOverFlowQuestions

A native Android app that displays a list of recent Stack Overflow questions that:
- have an accepted answer, and
- contain more than one answer

​![alt text](https://github.com/tushar40/StackOverFlowQuestions/blob/master/readme-images/splash_screen.png)
​![alt text](https://github.com/tushar40/StackOverFlowQuestions/blob/master/readme-images/questions_screen.png)
​![alt text](https://github.com/tushar40/StackOverFlowQuestions/blob/master/readme-images/webview_screen.png)


This project is built using Kotlin and AndroidX.

# Contents at a Glance
* [Getting Started](#getting-started)
  * [Clone the Repository](#clone-the-repository)
  * [Open and Run Project in Android Studio](#open-and-run-project-in-android-studio)
  * [Generating APK](#generating-apk)
  * [Components](#components)
* [On Device Caching](#on-device-caching)
* [Dependencies](#dependencies)
  * [Libraries Used](#libraries-used)
  * [Licenses](#licenses)
## Getting Started

### Clone the Repository

```
$ git clone https://github.com/tushar40/StackOverFlowQuestions.git
```

### Open and Run Project in Android Studio

- Open the project up in Android Studio.
- Click on the `Run App` (Green play button in top bar)

At this point, you *should* be able to build and run the project in the Android device or emulator.

### Generating APK

From Android Studio:

1. ***Build*** menu
2. ***Build Bundle(s) / APK(s)***
3. ***Build APK***

### Components
- Activities
  - ***MainActivity*** - The activity which is presented to the user when the app is launched. It is the parent container for the fragments which are controlled by the navigation graph.

- Fragments
  - ***SplashFragment*** - This fragment is displaying a splash screen on app start.
  - ***QuestionsFragment*** - This fragment is displaying the list of Stack OverFlow Questions.
   - ***WebViewFragment*** - This fragment is displaying the site of the Stack OverFlow Question selected in the QuestionsFragment.


- Adaptors
  - ***QuestionsAdapter*** - The adaptor for Questions ListView in Questions Screen.

- API
  - Interfaces & Wrappers for Endpoints.

- Utils
  1. FragmentListener - Interface for Fragment Callbacks.
  2. Constants - Contains constant strings used throughout the app.
  3. QuestionsDiffCallback - Gives the callback used to differentiate between old and new items in the ListView.

- ViewModels
  - ***QuestionsViewModel*** - ViewModel for QuestionFragment.

## On Device Caching
  - The app caches the previously fetched response to be used in case there is no network.

## Dependencies

### Libraries Used

  1. [Retrofit](https://square.github.io/retrofit/)
  2. [Lottie Android](https://github.com/airbnb/lottie-android)
  3. [CircleImageView](https://github.com/hdodenhof/CircleImageView)
  4. [Glide](https://github.com/bumptech/glide)

### Licenses

- Android native dependencies (Kotlin, Testing etc) - Apache 2.0
- Glide - BSD, part MIT and Apache 2.0
- RetroFit - Apache 2.0
- CircleImageView - Apache 2.0
- gson - Apache 2.0
- lottie-android - Apache 2.0
- material-components-android - Apache 2.0
