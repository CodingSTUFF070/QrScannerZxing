package com.example.qrcodescanner

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.zxing.client.android.Intents.Scan
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {

    private lateinit var scanQrBtn: Button
    private lateinit var scannedValueTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanQrBtn = findViewById(R.id.scanQrBtn)
        scannedValueTv = findViewById(R.id.scannedValueTv)

        registerUiListener()
    }

    private fun registerUiListener() {
        scanQrBtn.setOnClickListener {
            scannerLauncher.launch(
                ScanOptions().setPrompt("Scan Qr Code")
                    .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            )
        }
    }

    private val scannerLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result ->

        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            scannedValueTv.text = buildString {
                append("Scanned Value : ")
                append(result.contents)
            }
        }

    }

}