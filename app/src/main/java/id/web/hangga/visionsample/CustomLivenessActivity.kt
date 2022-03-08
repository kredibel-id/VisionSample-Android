package id.web.hangga.visionsample

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import io.kredibel.vision.*

class CustomLivenessActivity : VisionActivity() {

    lateinit var livenessView : LivenessCameraView
    lateinit var imgIcResult : ImageView
    lateinit var txtLabel : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_liveness)
        imgIcResult = findViewById(R.id.imgIcResult)
        txtLabel = findViewById(R.id.txtLabel)

        livenessView = findViewById(R.id.livenessView)
        livenessView.setDetections(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN, Detection.BLINK_LEFT))
        livenessView.setLivenessDetectionListener(listener)
        livenessView.start()
    }

    val listener  = object : LivenessDetectionListener {
        override fun onError(message: String?) {
            showMessage(message)
        }

        @SuppressLint("SetTextI18n")
        override fun onPrepare(isLoading: Boolean) {
            txtLabel.text = "Tunggu sebentar.."
        }

        @SuppressLint("SetTextI18n")
        override fun onDetecting(isDetection: Boolean, detection: String?) {
            if (isDetection) {
                txtLabel.text = "Please " + detection?.uppercase()
            } else {
                txtLabel.text = "Pastikan wajah Anda berada di frame."
            }
        }

        override fun onEachCompleted(livenessResult: LivenessResult?) {
            showMessage(livenessResult?.value.toString())
        }

        @SuppressLint("SetTextI18n")
        override fun onAllCompleted(livenessResults: MutableList<LivenessResult>?) {
            txtLabel.text = "Selesai.."
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        livenessView.stop()
    }

    fun showMessage(s: String?){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}