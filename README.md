# Local Instant Search

Local Instant Search using RxBinding (Reactive Programming Project)

we will use RxBinding library for binding our search with our RecyclerView by making the typing of characters in the search as a stream of data (observable) and every time we type a character the RecyclerView get filtered.

## Dependencies

Don't forget to add Java 8 to your gradle file

```java
android {
    compileSdkVersion 28
    defaultConfig {
       ...
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    buildTypes {
        ...
    }
}
```
Then add these dependencies
```java
dependencies {
    ...

    // RxJava & RxAndroid
    implementation 'io.reactivex.rxjava2:rxjava:2.1.9'
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'

    // butter knife
    implementation "com.jakewharton:butterknife:8.8.1"
    annotationProcessor "com.jakewharton:butterknife-compiler:8.8.1"

    // RxBinding
    implementation "com.jakewharton.rxbinding2:rxbinding:2.0.0"
}

```
## Reactive Programming
if you're new to reactive programming or you want to know more, then check this article that i wrote on this subject that we introduce and wrap up everything you need to know about reactive programming using Rx:

[Link to the article](https://www.linkedin.com/pulse/reactivex-reactive-programming-principles-mahdi-chtioui/?trackingId=%2F8XzEJB0R%2BiQOPhR%2BU1nrw%3D%3D)

Let's keep moving forward

### RxBinding

***RxBinding*** is library for RxJava that handles UI events (Click, Swipe…) and bind them using data streams. IT basically UI interactions with simplified code. Specially when you have multiple event handling, the logic can be greatly simplified. Instead of using the combination of *listeners*, *handlers* and *AsyncTasks*, you can just impelements ***RxJava*** and ***RxBinding***. You don't have to worry about *threading* problems and *memory leaks* by using *Schedulers* from the ***RxAndroid*** library.

### Example Of Using RxBinding (Example used in the demo)
For more details, just go to the MainActivity of this repository

```java
...

disposable.add(RxTextView.textChangeEvents(inputSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchMoodsTextWatcher()));

...
```

The operators of Rx used in this example: 

- ***debounce***: delay time between emits.
- ****distinctUntilChanged***: avoid repeating the same request twice in a row.

These operators will handle threading for us by specifying which schedulers will handles which task:

- ***subscribeOn***: specify the scheduler that our obervables will operates on.
- ****observeOn***: specify the scheduler on which we will oberve this observable.

The event used to listen to our Search EditTextView provided by *RxBinding* library
- ***RxTextView.textChangeEvents(someText)***: triggers an event to listen to any change to the text inside of the EditTextView


