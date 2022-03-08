package id.web.hangga.visionsample

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import io.kredibel.vision.Identity
import io.kredibel.vision.OCRCameraListener
import io.kredibel.vision.OCRCameraView
import io.kredibel.vision.OcrResult
import io.kredibel.vision.VisionActivity

class CustomOCRActivity : VisionActivity() {

    lateinit var ocrPreview : OCRCameraView
    lateinit var progressOcr : ProgressBar
    lateinit var imgAttach : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_ocr)
        imgAttach = findViewById(R.id.imgAttach)
        progressOcr = findViewById(R.id.progressOcr)
        ocrPreview = findViewById(R.id.ocrPreview)
        ocrPreview.start()

        findViewById<Button>(R.id.btnTakePicture).setOnClickListener{
            ocrPreview.takeIdentity(Identity.KTP, object : OCRCameraListener{
                override fun onGetBitmap(bitmap: Bitmap?) {
                    imgAttach.setImageBitmap(bitmap)
                    ocrPreview.visibility = View.GONE
                }

                override fun onError(message: String?) {
                    showMessage(message)
                }

                override fun onProgress(isLoading: Boolean) {
                    it.isEnabled = !isLoading
                    if (isLoading){
                        progressOcr.visibility = View.VISIBLE
                    } else {
                        progressOcr.visibility = View.GONE
                    }
                }

                override fun onComplete(isVerified: Boolean, ocrResult: OcrResult?) {
                    showMessage("Result: "+isVerified.toString())
                }
            })
        }
    }

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

    override fun onDestroy() {
        super.onDestroy()
        ocrPreview.stop()
    }

    fun showMessage(s : String?){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}