# MyTaxi Android challenge
You can download the APK [here](https://github.com/diegolucasb/car-selection/tree/master/apk) or see the [images](https://github.com/diegolucasb/car-selection/tree/master/screenshots) here.

## Assumption I made
- As I could see, the list of vehicles we fetch from the endpoint has some random position because it is weird to see that some position were above the river for example. So I assumed that it is ok since the data seems to be mocked.

- I assumed that the property `heading` from `Vehicle` was the angle of the front of the car. So, my car image vector starts heading at zero angles and it rotated on the map according to retrieved `heading` property.

- As I'm not a good designer at all, I also assumed that is ok my poor layout and the terrible icons and colors I choose. Sorry for that :-D 

## About the project
The project is in `Kotlin`, implemented following `MVP pattern` with dependency injection using `Kodein`. For retrieving data from the network I used `Retrofit` and implemented, what I called `handler` to abstract the data load (`repository pattern`). 
 
In `VehicleHandler` class you can see two functions to get data from Retrofit. One of them is using `RxJava` and the other is using simple `Callback<T>`. I did this to demonstrate how I believe how easy is to change the data retrieve implementation.

The project is on a private repository on Github. It was developed using [gitflow](https://datasift.github.io/gitflow/IntroducingGitFlow.html) so you can check the commits and the progress of the development. Please, give me a git user which I can add as a collaborator of this repository.

Stack I used:
* Android Gradle Plugin 3+
* Android API 19+
* Kotlin
* MVP Pattern
* Retrofit
* Kodein
* RxJava
* GSon
* JUnit
* Espresso
* Roboletric
* Mockk
* Ktlint
* Detekt

## Test
* Unit tests 
* Instrumented test 

## Lint
Android SDK already has a [lint](https://developer.android.com/studio/write/lint.html) 
toolkit so if you want to edit the severity of problems jumps to ```lint.xml``` in this project. 

I also used [ktlint](https://ktlint.github.io/) and [detekt](https://github.com/arturbosch/detekt) for code smells

## TODO: Improvements if I have more time, I would
- definitely, do a better UI
- cache strategy
- increase code coverage
- used a better solution for [`idleResource`](https://developer.android.com/training/testing/espresso/idling-resource), but I preferred using `Thread.sleep` to keep it simple.
- refactor VehicleMapActivity

## Author
Build with ❤️ and any feedback is welcome