# VisionSample-Android
An example of implementing liveness detection and ocr on an android application using Kredibel-Vision-SDK.

### Liveness Detection
```kotlin
Vision.with(this)
    .detection(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN))
    .listener(object : VisionListener{
        override fun onSuccess(p0: MutableList<LivenessResult>?, p1: OcrResult?) {

        }

        override fun onError(p0: String?) {
            showMessage(p0!!)
                    }
        })
    .start()
```
### Identity OCR

```kotlin
Vision.with(this)
    .identity(Identity.KTP)
    .showOCRLastResult(true)
    .start()
```