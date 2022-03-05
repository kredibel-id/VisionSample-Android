package id.web.hangga.visionsample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.kredibel.vision.Detection
import io.kredibel.vision.Identity
import io.kredibel.vision.LivenessResult
import io.kredibel.vision.OcrResult
import io.kredibel.vision.Vision
import io.kredibel.vision.VisionListener

class MainActivity : AppCompatActivity() {

    lateinit var btnLiveness: Button
    lateinit var btnOcr : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLiveness = findViewById(R.id.btnLiveness)
        btnOcr = findViewById(R.id.btnOcr)


        btnLiveness.setOnClickListener {
            Vision.with(this)
                .detection(arrayOf(Detection.SMILE, Detection.MOUTH_OPEN))
                .listener(object : VisionListener{
                    override fun onSuccess(livenessResult: MutableList<LivenessResult>?, ocrResult: OcrResult?) {

                    }

                    override fun onError(s: String?) {
                        showMessage(s!!)
                    }
                })
                .onSuccessPage(MainActivity::class.java)  // optional
                .showContour(true)     // optional
                .showLabel(true)       // optional
                .showBoundingBox(true) // optional
                .start()
        }

        btnOcr.setOnClickListener{
            Vision.with(this)
                .identity(Identity.KTP)
                .showOCRLastResult(true)
                .start()
        }
    }

    fun showMessage(s : String){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}