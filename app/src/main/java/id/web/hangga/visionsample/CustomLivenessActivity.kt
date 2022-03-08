package id.web.hangga.visionsample

import android.os.Bundle
import io.kredibel.vision.*

class CustomLivenessActivity : VisionActivity() {

    lateinit var livenessView : LivenessCameraView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_liveness)
        livenessView = findViewById(R.id.livenessView)
        livenessView.setDetections(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN, Detection.BLINK_LEFT))
        livenessView.setLivenessDetectionListener(listener)
        livenessView.start()
    }

    val listener  = object : LivenessDetectionListener {
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
}