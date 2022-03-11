# Advance Implementation : Create Own Vision Activity.

In the basic implementation, you have understood the use of the start() method in the Vision class. Really, it's the quick and easy way. 

<img width="100%" src="https://1.bp.blogspot.com/-9MiK78CFMLM/YQFurOq9AII/AAAAAAAAQ1A/lKj5GiDnO_MkPLb72XqgnvD5uxOsHO-eACLcBGAsYHQ/s0/Android-Compose-1.0-header-v2.png"/>

At an advanced level, you can create your own Liveness Detection Activity or OCR Activity with your own UI Design. 

## Create new Activity, then extends from VisionActivity.  

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
        setContentView(R.layout.activity_custom_ocr);
    }
}
```

<h1 align="center">Using LivenessCameraView Component</h1>
<img width="100%" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/liveness-camera-view.png?raw=true" />

**LivenessCameraPreview** is a component which is a SurfaceView which includes a Camera controller and a Liveness Detection Processor with Machine Learning. This component can display the camera preview and process liveness detection at the same time. 
<br/><br/>
Add this component to your activity or fragment layout page. Well, here you can design your own UI/UX.   
<br/>

```xml
<io.kredibel.vision.LivenessCameraView
  android:id="@+id/livenessView"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:layout_centerInParent="true"
  android:foregroundGravity="center"/>
```

#### Initialize
![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)   
```kotlin
val livenessView : LivenessCameraView = findViewById(R.id.livenessView)
```

![java](https://img.shields.io/badge/-Java-%23B07119)  
```kotlin
LivenessCameraView livenessView = findViewById(R.id.livenessView);
```

#### Detection
![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)  
```kotlin
livenessView.setDetections(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN, Detection.BLINK_LEFT))
```
![java](https://img.shields.io/badge/-Java-%23B07119)  
```kotlin
livenessView.setDetections(new String[]{Detection.SMILE, Detection.MOUTH_OPEN, Detection.BLINK_LEFT});
```

#### LivenessDetectionListener

LivenessDetectionListener is a java interface that will accommodate the results of the liveness detection process.

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
val listener = object : LivenessDetectionListener {
    override fun onError(message: String?) {
    	// Getting an error message if the process failed.
    }

    override fun onPrepare(isLoading: Boolean) {
    	// Get process preparation status.
    }

    override fun onDetecting(isDetection: Boolean, detection: String?) {
    	// get detection status
    }

    override fun onEachCompleted(livenessResult: LivenessResult?) {
    	// Get results on each detection.
    }

    override fun onAllCompleted(livenessResults: MutableList<LivenessResult>?) {
    	// Get all detection results.
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
To start preview and Liveness Detection process, you can call the start() method.

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
livenessView.start()
```

![java](https://img.shields.io/badge/-Java-%23B07119)  
```kotlin
livenessView.start();
```

#### Stop Preview
Don't forget to stop the process when it's finished or not in use. You can call it on onDestroy() on the Activity or on finish().

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
livenessView.stop()
```

![java](https://img.shields.io/badge/-Java-%23B07119)  
```kotlin
livenessView.stop();
```
<h1></h1>
<h1 align="center">Using OCRCameraView Component</h1>
<img width="100%" src="https://github.com/kredibel-id/VisionSample-Android/blob/main/ocr-camera-view.png?raw=true" />   
    
**OCRCameraView** is a component which is a SurfaceView which includes a Camera controller and a OCR with Kredibel API.

```xml
<io.kredibel.vision.OCRCameraView
   android:id="@+id/ocrPreview"
   android:layout_width="match_parent"
   android:layout_height="600dp"
   android:layout_alignParentTop="true"/>
```

#### Initialize

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
val ocrPreview : OCRCameraPreview = findViewById(R.id.ocrPreview)
```

![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
OCRCameraPreview ocrPreview = findViewById(R.id.ocrPreview);
```

#### Start Preview

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
ocrPreview.start()
```
![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
ocrPreview.start();
```

#### OCRCameraListener
OCRCameraListener is a java interface that will accommodate the results of the Identity OCR process.

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
val listener = object : OCRCameraListener{
    override fun onGetBitmap(bitmap: Bitmap?) {
            
    }

    override fun onError(message: String?) {
            
    }

    override fun onProgress(isLoading: Boolean) {
            
    }

    override fun onComplete(isVerified: Boolean, ocrResult: OcrResult?) {
            
    }
}
```

![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
OCRCameraListener listener = new OCRCameraListener() {
   @Override
   public void onGetBitmap(Bitmap bitmap) {
     // Getting photo as Bitmap.
   }

   @Override
   public void onError(String onError) {
      //Getting an error message if the process failed.
   }

   @Override
   public void onProgress(boolean isShow) {
     // get process status
   }

   @Override
   public void onComplete(boolean isSuccess, JSONObject response) {
     // When OCR process is completed
   }
};
```

#### Take Identity
Take identity and start OCR Process.   

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
ocrPreview.takeIdentity(Identity.KTP, listener)
```
![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
ocrPreview.takeIdentity(Identity.KTP, listener);
```
#### Stop Preview
Don't forget to stop the process when it's finished or not in use. You can call it on onDestroy() on the Activity or on finish().

![kotlin](https://img.shields.io/badge/-Kotlin-%23BA00BB)
```kotlin
ocrPreview.stop()
```

![java](https://img.shields.io/badge/-Java-%23B07119)
```kotlin
ocrPreview.stop();
```


