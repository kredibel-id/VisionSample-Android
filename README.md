# VisionSample-Android
An example of implementing liveness detection and ocr on an android application using Kredibel-Vision-SDK.

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
    .listener(object : VisionListener{
        override fun onSuccess(p0: MutableList<LivenessResult>?, p1: OcrResult?) {

        }

        override fun onError(p0: String?) {
            showMessage(p0!!)
                    }
        })          // listener, optional
    .onSuccessPage(SecondActivity::class.java) // optional
    .showContour(true)     // optional
    .showLabel(true)       // optional
    .showBoundingBox(true) // optional
    .start()
```
## Identity OCR

```kotlin
Vision.with(this)
    .identity(Identity.KTP)  // required
    .showOCRLastResult(true) // optional
    .onSuccessPage(MainActivity::class.java)  // optional
    .start()
```