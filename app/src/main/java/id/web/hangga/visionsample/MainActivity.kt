package id.web.hangga.visionsample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.kredibel.vision.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnLiveness).setOnClickListener {
            Vision.with(this)
                .debug()
                .detection(
                    arrayOf(
                        Detection.SMILE,
                        Detection.LOOK_LEFT,
                        Detection.LOOK_RIGHT,
                        Detection.MOUTH_OPEN
                    )
                )
                .listener(object : VisionListener { // optional, if you need add custom action.
                    override fun onSuccess(
                        livenessResult: MutableList<LivenessResult>?,
                        ocrResult: OcrResult?
                    ) {

                    }

                    override fun onError(s: String?) {
                        showMessage(s!!)
                    }
                })
                //.showContour(true)     // optional
                //.showLabel(true)       // optional
                .showBoundingBox(true) // optional
                .start()
        }

        findViewById<Button>(R.id.btnOcr).setOnClickListener {
            Vision.with(this)
                .identity(Identity.KTP)
                .showOCRLastResult(true)
                .onSuccessPage(SuccessPageActivity::class.java)  // optional, you can get result data from intent
                .start()
        }

        findViewById<Button>(R.id.btnCustomLiveness).setOnClickListener {
            val intent = Intent(this, CustomLivenessActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnCustomOcr).setOnClickListener {
            val intent = Intent(this, CustomOCRActivity::class.java)
            startActivity(intent)
        }
    }

    fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}