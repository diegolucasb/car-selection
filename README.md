# Simple project demonstration
You can download the APK [here](https://github.com/diegolucasb/car-selection/tree/master/apk) or see the [images](https://github.com/diegolucasb/car-selection/tree/master/screenshots) here.

## Assumption I made
- As the response body has a commom pattern, I assumed I could make a generic dialog picker for all of the requests
- I also assumed that layout is not the most important for this assignment, as I'm terrible at designing I didn't care about it

## About the project
The project is in `Kotlin`, implemented using MVVM pattern (I started the project using MVP but I decided to switch to MVVM)  with dependency injection using `Kodein`. For retrieving data from the network I used `Retrofit` with `RxJava` and implemented a `repository` to abstract the data load.

For paged requests I used [Android Paging Library](https://developer.android.com/topic/libraries/architecture/paging.html) from [Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)

The project is on a private repository on Github. It was developed using [gitflow](https://datasift.github.io/gitflow/IntroducingGitFlow.html) so you can check the commits and the progress of the development. Please, give me a git user which I can add as a collaborator of this repository.

Stack I used:
* Android Gradle Plugin 3+
* Android API 16+
* Kotlin
* MVVM
* Paging Library
* Retrofit
* Kodein
* RxJava
* GSon
* JUnit
* Mockk
* Ktlint
* Detekt

## Test
* Unit tests 

## Lint
Android SDK already has a [lint](https://developer.android.com/studio/write/lint.html) 
toolkit so if you want to edit the severity of problems jumps to ```lint.xml``` in this project. 

I also used [ktlint](https://ktlint.github.io/) and [detekt](https://github.com/arturbosch/detekt) for code smells. To run it, just execute: ```./gradlew check```

## TODO: Improvements if I have more time, I would
- definitely, do a better UI
- cache strategy
- increase code coverage
- integration tests
- make a search on picker dialog
- refactor PickerDialogFragment to reduce its complexity

## Author
Build with ❤️ and any feedback is welcome
Lucas Amaral
