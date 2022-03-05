# VisionSample-Android
[![KredibelVisionSDK: 2.5.3](https://img.shields.io/badge/Kredibel.Vision.SDK-%200.0.1.beta.20220304142741-brightgreen.svg)](https://github.com/kredibel-id/VisionSample-Android/)  
<p align="center">
<img align="center" width="200" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/logo_kredibel_new.png?raw=true"/>
</p>

A sample project of implementing **Liveness Detection** and **Identity OCR** on Android app using **Kredibel Vision SDK**.

### Support API Level

| Min Support                 | Max Support        |
|-----------------------------|--------------------|
| API 19/ Android 4.4/ KitKat | API 31/ Android 12 |

### Gradle
#### 1. Add kredibel repository.
You can do this in several alternative ways.
- In the build.gradle file at Project level. 
```groovy
allprojects {
    repositories {
       google()
       mavenCentral()
       maven{url 'https://repo.repsy.io/mvn/kredibel/vision'} // <—-- add this 
    }
}
```
- Or in dependencyResolutionManagement in setting.gradle.
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{url 'https://repo.repsy.io/mvn/kredibel/vision'} // <—-- add this
    }
}
rootProject.name = "YourProjectName"
include ':app'
```

#### 2. Add this dependency to gradle script on app module.
```groovy
dependencies {
    implementation 'io.kredibel:vision:0.0.1-beta' // <—-- Add this. Please check the latest version.
}
```
### Access Token
Please contact our sales team to get the token/api key.

Add a meta tag named kredibel-apikey in the scope of the **`<application></application>`** on your AndroidManifest.xml.   
Example:
```xml
<meta-data
    android:name="kredibel-apikey"
    android:value="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6OCw bla.. Bla.. bla.."/> 
```

### Liveness Detection

- Single Detection (Basic Implementation)
```kotlin
Vision.with(this)
    .detection(Detection.SMILE) // required
    .start()
```
- Multiple Detection
```kotlin
Vision.with(this)
    .detection(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN)) // required
    .listener(object : VisionListener{          // listener, optional
        override fun onSuccess(livenessResult: MutableList<LivenessResult>?, ocrResult: OcrResult?) {

        }

        override fun onError(s: String?) {
            showMessage(s!!)
        }
    })          
    .onSuccessPage(SecondActivity::class.java) // optional
    .showContour(true)                         // optional
    .showLabel(true)                           // optional
    .showBoundingBox(true)                     // optional
    .start()
```
### Identity OCR

```kotlin
Vision.with(this)
    .identity(Identity.KTP)                   // required
    .showOCRLastResult(true)                  // optional
    .onSuccessPage(MainActivity::class.java)  // optional
    .start()
```


<img width="300" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/on-detecting.png?raw=true"/> <img width="300" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/ktp-ocr.png?raw=true"/>

