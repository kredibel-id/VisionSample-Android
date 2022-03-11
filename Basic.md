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

## Get Result Data
You can use the **_onSuccessPage()_** method to select your activity that will receive the result data.

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
Vision.with(this) // Context, required
    .detection(Detection.SMILE) // required
    .onSuccessPage(SuccessPageActivity.class) // optional for passing result data
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


