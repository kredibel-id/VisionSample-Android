# Vision Sample (Android)   
![targetsdk](https://img.shields.io/badge/Target%20SDK-API%2031-green) ![ktx](https://img.shields.io/badge/KTX-1.7.0-%23C800DA) ![material](https://img.shields.io/badge/Material-1.5.0-brightgreen) ![appcompat](https://img.shields.io/badge/AppCompat-1.4.1-%23EE76D0) ![vision](https://img.shields.io/badge/Vision%20SDK-0.0.1--beta--20220304142741-%230169FF)   <hr/>
A sample project of implementing **Liveness Detection** and **Identity OCR** on Android app using **Kredibel Vision SDK**.   <br/><br/>
You can checkout the source code of this project.
```text
git clone https://github.com/kredibel-id/VisionSample-Android.git
```
Then open this sample project with Android Studio or Intellij IDEA.<hr/>
<br/><br/><br/><br/>

<p align="center">  
    <img align="center" width="200" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/logo_kredibel_new.png?raw=true"/>
</p>
<h1 align="center">Vision SDK</h1>
<p align="center">  
    <img src="https://img.shields.io/badge/Version-0.0.1--beta--20220304142741-%230169FF"/>
</p>

## Introduction
Vision SDK is a library that provides computer vision services such as Liveness Detection and Identity OCR with Kredibel VisionAI technology. 

##### Liveness Detection

1. Examine the digital representation of the user's face from the camera preview in realtime.
2. Analyze multiple movements, including head movements, eye blinks, smiles and mouth opening to determine activity.
3. Determine whether it is a living person or not.
<p align="center"> 
<img width="600" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/liveness.png?raw=true"/>
</p>

##### Identity OCR
Identity OCR is an Optical Character Recognition (OCR) service that supports three types of documents such as:
1. National Identity (KTP)
2. Driving License (SIM)
3. Passport 

<p align="center">
    <img width="600" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/ocr.png?raw=true"/>  
</p>

Currently the Vision SDK can only be used on the Android platform.  
#### Support API Level
![minsdk](https://img.shields.io/badge/Min%20SDK-API%2019-green) ![targetsdk](https://img.shields.io/badge/Max%20Support-API%2031-green)
## Install/ Setup
### Gradle
#### 1. Add kredibel repository.   
```groovy
maven{url 'https://repo.repsy.io/mvn/kredibel/vision'}
```
You can do this in two alternative ways.
- Latest way(Gradle 7+) : Add repository in dependencyResolutionManagement in setting.gradle.
```groovy
dependencyResolutionManagement {
    repositories {
        ...
        ...
        maven{url 'https://repo.repsy.io/mvn/kredibel/vision'} // <—-- add this
    }
}
```
- Old way : Add repository in build.gradle file at Project level.
```groovy
allprojects {
    repositories {
       ...
       ...
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
## How to Use (Basic Implementation)
### Liveness Detection   
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
### Identity OCR   
![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this)
    .identity(Identity.KTP)  // required. Identity type.
    .showOCRLastResult(true) // optional
    .onSuccessPage(MainActivity::class.java)  // optional
    .start()
```
![java](https://img.shields.io/badge/-Java-%23B07119)
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
## Advance Implementation : Create Own Vision Activity.

In the basic implementation, you have understood the use of the start() method in the Vision class. Really, it's the quick and easy way. 

At an advanced level, you can create your own Liveness Detection Activity or OCR Activity with your own UI Design. 

1. Create new Activity, then extends from VisionActivity.  
![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
class CustomLivenessActivity : VisionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_liveness)
    }
}
```

![java](https://img.shields.io/badge/-Java-%23B07119)
In Java, you must change Access Modifiers of onCreate method from protected to public.
```kotlin
public class CustomLivenessActivity extends VisionActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_my);
    }
}
```

### LivenessCameraView
LivenessCameraPreview is a component which is a SurfaceView which includes a Camera controller and a Liveness Detection Processor with Machine Learning. This component can display the camera preview and process liveness detection at the same time. 
<br/><br/>
Add this component to your activity or fragment layout page. Well, here you can design your own UI/UX.

#### XML
![xml](https://img.shields.io/badge/-xml-important)
```xml
<io.kredibel.vision.LivenessCameraView
  android:id="@+id/livenessView"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:layout_centerInParent="true"
  android:foregroundGravity="center"/>
```

#### Initialize
```kotlin
livenessView = findViewById(R.id.livenessView)
```

#### Detection

```kotlin
livenessView.setDetections(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN, Detection.BLINK_LEFT))
```

#### LivenessDetectionListener

LivenessDetectionListener is a java interface that will accommodate the results of the liveness detection process.

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
object listener : LivenessDetectionListener {
    override fun onError(message: String?) {
            TODO("Not yet implemented")
    }

    override fun onPrepare(isLoading: Boolean) {
            TODO("Not yet implemented")
    }

    override fun onDetecting(isDetection: Boolean, detection: String?) {
            TODO("Not yet implemented")
    }

    override fun onEachCompleted(livenessResult: LivenessResult?) {
            TODO("Not yet implemented")
    }

    override fun onAllCompleted(livenessResults: MutableList<LivenessResult>?) {
            TODO("Not yet implemented")
	}
}

livenessView.setLivenessDetectionListener(listener)
```

![java](https://img.shields.io/badge/-Java-%23B07119)   
```kotlin
LivenessDetectionListener listener = new LivenessDetectionListener() {
  @Override
  public void onError(String message) {
     // Getting an error message if the process failed.
  }

  @Override
  public void onPrepare(boolean isLoading) {
     // Get process preparation status.
  }

  @Override
  public void onDetecting(boolean isDetecting, String detection) {
     // get detection status
  }

  @Override
  public void onEachCompleted(LivenessResult livenessResult) {
     // Get results on each detection.
  }

  @Override
  public void onAllCompleted(List<LivenessResult> livenessResults) {
     // Get all detection results.
  }
};

livenessView.setLivenessDetectionListener(listener);
```

#### Start Preview
To start the preview, you can call the start() method.

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
livenessView.start()
```

![java](https://img.shields.io/badge/-Java-%23B07119)  
```kotlin
livenessView.start()
```

#### Stop Preview
Don't forget to stop the process when it's finished or not in use.

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
livenessView.stop()
```

![java](https://img.shields.io/badge/-Java-%23B07119)  
```kotlin
livenessView.stop()
```


