# VisionSample-Android
An example of implementing **_Liveness Detection_** and **_Identity OCR_** on an android application using **_Kredibel-Vision-SDK_**.

<img width="300" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/on-detecting.png?raw=true"/> <img width="300" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/ktp-ocr.png?raw=true"/>

## Gradle
Add this repository declaration to gradle script on project level.

```groovy
repositories {
    …
    …
    maven{url 'https://repo.repsy.io/mvn/kredibel/vision'} // <—-- Add this
}
```


Add this dependency to gradle script on app module.
```groovy
dependencies {
    implementation 'io.kredibel:vision:0.0.1-beta-20220304142741' // <—-- Add this. Please check the latest version.
}
```
## Access Token
Please contact our sales team to get the token/api key.

Add a meta tag named kredibel-apikey in the scope of the **`<application></application>`** on your AndroidManifest.xml.   
Example:
```xml
<meta-data
    android:name="kredibel-apikey"
    android:value="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6OCw bla.. Bla.. bla.."/> 
```

## Liveness Detection

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
## Identity OCR

```kotlin
Vision.with(this)
    .identity(Identity.KTP)                   // required
    .showOCRLastResult(true)                  // optional
    .onSuccessPage(MainActivity::class.java)  // optional
    .start()
```
