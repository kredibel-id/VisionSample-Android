# VisionSample-Android
An example of implementing liveness detection and ocr on an android application using Kredibel-Vision-SDK.

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
        }) // optional
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