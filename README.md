# Vision Sample (Android)   
![gradle](https://img.shields.io/badge/Gradle-7.0.2-critical?logo=gradle) ![targetsdk](https://img.shields.io/badge/Target%20SDK-API%2031-%233DDC84?logo=android) ![ktx](https://img.shields.io/badge/KTX-1.7.0-%237E52FF?logo=kotlin) <a href="https://repo.repsy.io/mvn/kredibel/sdk/io/kredibel/vision/"><img src="https://img.shields.io/badge/Vision%20SDK-0.0.1--beta--20220313085304-%230169FF"/></a>   

A sample project of implementing **Liveness Detection** and **Identity OCR** on Android app using **Kredibel Vision SDK**.   <br/><br/>
You can checkout the source code of this project.
```text
git clone https://github.com/kredibel-id/VisionSample-Android.git
```
Then open this sample project with Android Studio or Intellij IDEA.
<h1></h1>
<br/><br/><br/><br/>

<p align="center">  
    
</p>
<h1 align="center">
    <a href="https://www.kredibel.co.id/"><img align="center" width="260" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/logo_kredibel_new.png?raw=true"/></a>
    <br/><br/>
    Vision SDK
</h1>
<p align="center">  
    <a href="https://repo.repsy.io/mvn/kredibel/sdk/io/kredibel/vision/"><img src="https://img.shields.io/badge/Version-0.0.1--beta--20220313085304-%230169FF"/></a>
</p>

## Introduction
Vision SDK is a library that provides computer vision services such as Liveness Detection and Identity OCR with Kredibel VisionAI technology. 
### Features
##### Liveness Detection

1. Examine the digital representation of the user's face from the camera preview in realtime.
2. Out-of-frame face detection to prevent spoofing.
3. Analyze multiple movements, including head movements, eye blinks, smiles and mouth opening to determine activity.
4. Determine whether it is a living person or not.
##### Identity OCR
Identity OCR is an Optical Character Recognition (OCR) service that supports some types :
1. National Identity (KTP)
2. Driving License (SIM)
3. Passport
4. Handheld with Id Card selfie    

Currently the Vision SDK can only be used on the Android platform.  
### Support API Level
![minsdk](https://img.shields.io/badge/Min%20SDK-API%2019-%233DDC84?logo=android) ![targetsdk](https://img.shields.io/badge/Max%20Support-API%2031-%233DDC84?logo=android)
  
# Install / Setup
- Aar file size : 2,7 MB 
## Gradle
#### 1. Add kredibel repository.   
```groovy
maven{url 'https://repo.repsy.io/mvn/kredibel/sdk'}
```
You can do this in two alternative ways.
- Latest way(Gradle 7+) : Add repository in dependencyResolutionManagement in setting.gradle.
```groovy
dependencyResolutionManagement {
    repositories {
        ...
        ...
        maven{url 'https://repo.repsy.io/mvn/kredibel/sdk'} // <—-- add this
    }
}
```
- Old way : Add repository in build.gradle file at Project level.
```groovy
allprojects {
    repositories {
       ...
       ...
       maven{url 'https://repo.repsy.io/mvn/kredibel/sdk'} // <—-- add this 
    }
}
```

#### 2. Add this dependency to gradle script on app module.
```groovy
dependencies {
    implementation 'io.kredibel:vision:0.0.1-beta-20220511170731' // Please check latest version
}
```

## API-Key
Please read the instructions <a href="https://docs.kredibel.io/authentication-1">here</a> to get the API-Key or contact our sales team.

## Config AndroidManifest.xml
After getting API-Key then open your **AndroidManifest.xml** than add a this meta-data in the scope of `<application></application>` tag.
```xml
<meta-data android:name="io.kredibel.sdk.APIKEY" android:value="Your API-Key" /> 
```
Please name the attribute name with `io.kredibel.sdk.APIKEY`.   

Example :     
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest 
    xmlns:android="http://schemas.android.com/apk/res/android"
    package="id.web.hangga.visionsample">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.VisionSample">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>

        <meta-data
            android:name="io.kredibel.sdk.APIKEY"
            android:value="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6OCw bla.. Bla.. bla.."/>
    </application>
</manifest>
```

# How to Use (Basic Implementation)   
The Vision class is the main class in the Kredibel Vision SDK. This class contains methods or functions to handle Liveness Detection and OCR quickly. You don't need to create a layout/UI, because we have provided everything. You just use all the functions/methods in the Vision class.   
<p align="center">
<img width="48%" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/liveness.png?raw=true"/> <img width="48%" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/ocr.png?raw=true"/>  
</p>   

## Liveness Detection   
#### 1. Single Detection   
![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this) // Context, required
    .detection(Detection.SMILE) // required
    .start()
```
![java](https://img.shields.io/badge/-Java-%23B07119)
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
![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
Vision.with(this)
    .detection(new String[]{Detection.SMILE, Detection.MOUTH_OPEN, Detection.BLINK_LEFT}) // required
    .delay(2000)  // milliseconds, optional. Default = 1000
    .start();
```  
The following are some of the head and facial movements supported by the Vision SDK.   

|Face and Head Movements|Parameters|
|-----------------------|----------|
|Smile|`Detection.SMILE`|
|Open mouth|`Detection.MOUTH_OPEN`|
|Look Up|`Detection.LOOK_UP`|
|Look to the right|`Detection.RIGHT`|
|Looking down|`Detection.LOOKING_DOWN`|
|Look to the left|`Detection.LEFT`|
|Get random head and face movements|`Detection.RANDOM_HEAD_ANGLE`|
|Left eye wink|`Detection.BLINK_LEFT`|
|Right eye wink|`Detection.BLINK_RIGHT`|
|Getting random winks|`Detection.RANDOM_EYE_BLINK`|
## Identity OCR   
![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this)
    .identity(Identity.KTP)  // required. Identity type.
    .showOCRLastResult(true) // optional
    .onSuccessPage(SuccessPageActivity::class.java)  // optional
    .start()
```
![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
Vision.with(this)
    .identity(Identity.KTP)  // required. Identity type.
    .showOCRLastResult(true) // optional
    .onSuccessPage(SuccessPageActivity.class)  // optional
    .start();
```
The following are some of the supported document types and their parameter names.
|Dosument Type|Parameters|
|--|--|
|Indonesian National Identity Card/ Kartu Tanda Penduduk(KTP)|`Identity.KTP`|
|Driver's license|`Identity.SIM`|
|Passport|`Identity.PASSPORT`|
|Handheld with id card selfie|`Identity.HANDHELD`|
## Get Result Data
You can use the **_onSuccessPage()_** method to select your activity that will receive the result data.

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this) // Context, required
    .detection(Detection.SMILE) // required
    .onSuccessPage(SuccessPageActivity::class.java) // optional for passing result data
    .start()
```
![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
Vision.with(this)
    .detection(Detection.SMILE) // required
    .onSuccessPage(SuccessPageActivity.class) // optional for passing result data
    .start();
```

Then you can get result data from intent in your **_SuccessPageActivity_** on activity created override method with this parameters.

```kotlin
getParcelableArrayListExtra(Vision.RESULT_LIVENESS)

getParcelableExtra(Vision.RESULT_OCR)
```  
Example :

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
class SuccessPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_page)
        // get result data
        val livenessResults : List<LivenessResult> = intent.getParcelableArrayListExtra(Vision.RESULT_LIVENESS)!!
        val ocrResult : OcrResult = intent.getParcelableExtra(Vision.RESULT_OCR)!!
    }
}
```

![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
public class SuccessPageActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        // get result data
        List<LivenessResult> livenessResults = getIntent().getParcelableArrayListExtra(Vision.RESULT_LIVENESS);
        OcrResult ocrResult = intent.getParcelableExtra(Vision.RESULT_OCR);
    }
}
```

## Using VisionListener   
You can use **_VisionListener_** for capture all detection results and or add a custom action after process. 

If you use a **_VisionListener_**, then you don't need to call the **_onSuccessPage()_** method, because it won't run.

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
![java](https://img.shields.io/badge/-Java-%23B07119)
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
## Optional Features   
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
![java](https://img.shields.io/badge/-Java-%23B07119)
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

## Customizing String
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
<a href="https://github.com/kredibel-id/VisionSample-Android/blob/main/Advance.md"><h1>Next : Advance Implementation >></h1></a>


