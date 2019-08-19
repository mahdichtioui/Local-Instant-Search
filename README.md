# Local-Instant-Search
Local Instant Search using RxBinding (Reactive Programming Project)

we will use RxBinding library for binding our search with our RecyclerView by making the typing of characters in the search as a stream of data (observable) and every time we type a character the RecyclerView get filtered.

## Dependencies

Don't forget to add Java 8 to your gradle file

```android
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
```android
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
