package id.web.hangga.visionsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.kredibel.vision.LivenessResult
import io.kredibel.vision.OcrResult
import io.kredibel.vision.Vision

class SuccessPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_page)
        val livenessResults : List<LivenessResult> = intent.getParcelableArrayListExtra(Vision.RESULT_LIVENESS)!!
        val ocrResult : OcrResult = intent.getParcelableExtra(Vision.RESULT_OCR)!!
    }
}