package id.web.hangga.visionsample

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import io.kredibel.vision.*

class CustomOCRActivity : VisionActivity() {

    lateinit var ocrPreview: OCRCameraView
    lateinit var progressOcr: ProgressBar
    lateinit var imgAttach: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_ocr)
        imgAttach = findViewById(R.id.imgAttach)
        progressOcr = findViewById(R.id.progressOcr)
        ocrPreview = findViewById(R.id.ocrPreview)
        ocrPreview.start()

        findViewById<Button>(R.id.btnTakePicture).setOnClickListener {
            ocrPreview.takeIdentity(Identity.KTP, object : OCRCameraListener {
                override fun onGetBitmap(bitmap: Bitmap?) {
                    imgAttach.setImageBitmap(bitmap)
                    ocrPreview.visibility = View.GONE
                }

                override fun onError(message: String?) {
                    showMessage(message)
                }

                override fun onProgress(isLoading: Boolean) {
                    it.isEnabled = !isLoading
                    if (isLoading) {
                        progressOcr.visibility = View.VISIBLE
                    } else {
                        progressOcr.visibility = View.GONE
                    }
                }

                override fun onComplete(isVerified: Boolean, ocrResult: OcrResult?) {
                    showMessage("Result: $isVerified")
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ocrPreview.stop()
    }

    fun showMessage(s: String?) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}