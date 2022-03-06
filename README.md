# Vision Sample (Android)   
![jdk](https://img.shields.io/badge/JDK-1.8-red) ![ktx](https://img.shields.io/badge/KTX-1.7.0-%23C800DA) ![material](https://img.shields.io/badge/Material-1.5.0-brightgreen) ![appcompat](https://img.shields.io/badge/AppCompat-1.4.1-%23EE76D0) ![vision](https://img.shields.io/badge/Vision%20SDK-0.0.1--beta--20220304142741-%230169FF)   <hr/>
A sample project of implementing **Liveness Detection** and **Identity OCR** on Android app using **Kredibel Vision SDK**.
You can checkout the source code of this project.
#### Checkout
```text
git clone https://github.com/kredibel-id/VisionSample-Android.git
```
then open this sample project with Android Studio or Intellij IDEA.<hr/>
<br/><br/><br/><br/>

<p align="center">  
    <img align="center" width="200" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/logo_kredibel_new.png?raw=true"/>
</p>
<h1 align="center">Vision SDK</h1>  

### Support API Level

| Min Support                 | Max Support        |
|-----------------------------|--------------------|
| API 19/ Android 4.4/ KitKat | API 31/ Android 12 |

### Gradle
#### 1. Add kredibel repository.
You can do this in two alternative ways.
- Latest way : Add repository in dependencyResolutionManagement in setting.gradle.
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
- Old way : Add repository in build.gradle file at Project level. Don't forget to remove dependencyResolutionManagement, if you use this way.
```groovy
allprojects {
    repositories {
       google()
       mavenCentral()
       maven{url 'https://repo.repsy.io/mvn/kredibel/vision'} // <—-- add this 
    }
}
```

#### 2. Add this dependency to gradle script on app module.
```groovy
dependencies {
    implementation 'io.kredibel:vision:<latest version>' // <—-- Add this.
}
```
Check <a target="_blank" href="https://repo.repsy.io/mvn/kredibel/vision/io/kredibel/vision/">latest version</a>.
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

#### 1. Single Detection (Basic Implementation)   
![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this) // Context, required
    .detection(Detection.SMILE) // required
    .start()
```
![java](https://img.shields.io/badge/-Java-brightgreen)
```kotlin
Vision.with(this)
    .detection(Detection.SMILE) // required
    .start();
```
#### 2. Multiple Detection     

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this)
    .detection(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN, Detection.BLINK_LEFT)) // required
    .delay(2000)  // milliseconds, optional. Default = 1000
    .start()
```
![java](https://img.shields.io/badge/-Java-brightgreen)
```kotlin
Vision.with(this)
    .detection(new String[]{Detection.SMILE, Detection.MOUTH_OPEN, Detection.BLINK_LEFT}) // required
    .delay(2000)  // milliseconds, optional. Default = 1000
    .start();
```
### Identity OCR
![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this)
    .identity(Identity.KTP)  // required. Identity type.
    .showOCRLastResult(true) // optional
    .onSuccessPage(MainActivity::class.java)  // optional
    .start()
```
![java](https://img.shields.io/badge/-Java-brightgreen)
```kotlin
Vision.with(this)
    .identity(Identity.KTP)  // required. Identity type.
    .showOCRLastResult(true) // optional
    .onSuccessPage(MainActivity.class)  // optional
    .start();
```
### Using VisionListener   
You can use Vision Listener for capture all detection results and or add a custom action.  

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this)
    .detection(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN)) // required
    .listener(object : VisionListener{   // listener, optional on Liveness & OCR
        override fun onSuccess(livenessResult: MutableList<LivenessResult>?, ocrResult: OcrResult?) {
            // if you want to capture all detection results and or add a custom action.
        }

        override fun onError(s: String?) {
            showMessage(s!!)
        }
    })          
    .delay(2000)  // milliseconds, optional. Default = 1000
    .start()
```
![java](https://img.shields.io/badge/-Java-brightgreen)
```kotlin
Vision.with(this)
    .detection(new String[]{Detection.SMILE, Detection.MOUTH_OPEN}) // required
    .listener(new VisionListener() { // listener, optional on Liveness & OCR
        @Override
        public void onSuccess(List<LivenessResult> list, OcrResult ocrResult) {
            // if you want to capture all detection results and or add a custom action.
        }

        @Override
        public void onError(String s) {

        }
     })        
    .delay(2000)  // milliseconds, optional. Default = 1000
    .start();
```
### Optional Features   
Some optional features that you can use.   

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this)
    .detection(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN)) // required
    .delay(2000)  // milliseconds, optional. Default = 1000
    .onSuccessPage(SecondActivity::class.java) // optional
    .finishOnSuccess(true) // optional, for auto destroy current activity/context after liveness/ocr process.
    .showContour(true)     // optional
    .showLabel(true)       // optional
    .showBoundingBox(true) // optional
    .start()
```
![java](https://img.shields.io/badge/-Java-brightgreen)
```kotlin
Vision.with(this)
    .detection(new String[]{Detection.SMILE, Detection.MOUTH_OPEN}) // required  
    .delay(2000)  // milliseconds, optional. Default = 1000
    .onSuccessPage(SecondActivity.class) // optional
    .finishOnSuccess(true) // optional, for auto destroy current activity/context after liveness/ocr process.
    .showContour(true)     // optional
    .showLabel(true)       // optional
    .showBoundingBox(true) // optional
    .start();
```
<img width="300" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/on-detecting.png?raw=true"/> <img width="300" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/ktp-ocr.png?raw=true"/>

### Customizing instruction
You can customize instructions and some text by adding the following string resource to your project. Add only the strings you need and make sure the string name is correct, don't be mistaken.

```xml
<!--Vision General-->
<string name="kv_title_close" translatable="false">Close</string>
<string name="kv_title_next" translatable="false">Next</string>
<string name="kv_msg_loading_data" translatable="false">Loading...</string>

<!--Vision Liveness-->
<string name="kv_title_instruction" translatable="false">Follow instruction:</string>
<string name="kv_title_liveness" translatable="false">Liveness Detection</string>
<string name="kv_title_identity_type" translatable="false">Identity Type</string>
<string name="kv_msg_verification_complete" translatable="false">Verification Complete</string>

<!--Before detection-->
<string name="kv_clue_yourface_inframe" translatable="false">Make sure your face is in the frame and in a well-lit place.
</string>

<!--After detection, then face out of circle.-->
<string name="kv_msg_yourface_out_circle" translatable="false">Oops! Your face should stay in circle during liveness. We will try again from the beginning.</string>
<string name="kv_msg_liveness_oncomplete" translatable="false">You have successfully followed all instructions, congrats!
</string>

<!-- Face orientation-->
<string name="kv_smile" translatable="false">Please Smile</string>
<string name="kv_left_eye_blink" translatable="false">Left Eye Blink</string>
<string name="kv_right_eye_blink" translatable="false">Right Eye Blink</string>
<string name="kv_look_up" translatable="false">Look Up</string>
<string name="kv_look_down" translatable="false">Look Down</string>
<string name="kv_look_left" translatable="false">Look Left</string>
<string name="kv_look_right" translatable="false">Look Right</string>
<string name="kv_open_mouth" translatable="false">Open your Mouth</string>

<!-- Vision Identity OCR-->
<string name="kv_title_ocr_last_result" translatable="false">See Last Result</string>
<string name="kv_title_identity_result" translatable="false">Identity Result</string>
<string name="kv_title_scan_identity" translatable="false">Scan Identity</string>
<string name="kv_title_scan_ktp" translatable="false">Scan Identity - KTP</string>
<string name="kv_title_scan_sim" translatable="false">Scan Identity - SIM</string>
<string name="kv_title_scan_passport" translatable="false">Scan Identity - PASSPORT</string>
<string name="kv_title_hand_held" translatable="false">Selfie holding Identity card</string>
<string name="kv_title_ocr_start" translatable="false">Start Verification</string>
<string name="kv_title_ocr_take_picture" translatable="false">Take Picture</string>
<string name="kv_title_ocr_uploading" translatable="false">Uploading...</string>
<string name="kv_msg_upload_identity" translatable="false">Uploading Identity ...</string>
<string name="kv_msg_ocr_succeded" translatable="false">Verification succeeded</string>
<string name="kv_msg_ocr_see_result" translatable="false">Click the "See Last Result" button to see your verification livenessResult.</string>
<string name="kv_msg_upload" translatable="false">Your identity is being uploaded and processed by our system, it may take some time.</string>
<string name="kv_msg_ocr_verification_failed" translatable="false">Verification Failed</string>
<string name="kv_clue_card_inframe" translatable="false">Position your identity card in the frame and in a well-lit place.
</string>
```


