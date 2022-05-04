package com.example.zipperexample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.zipperexample.compressor.*
import java.io.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val gzip = GZipCompressor()
    private val gzipLibCompressor = GZIPLibCompressor()
    private val bZip2Compressor = BZip2Compressor()
    private val sevenZip = SevenZipCompressor()
    private val xzZip = XZCompressor()
    private val lzmaCompressor = LZMACompressor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission()

        findViewById<Button>(R.id.compress_button).setOnClickListener {
            Thread {
                val inputPath = "/sdcard/Download/test_log.log"
                gzip(inputPath)
                gzipApache(inputPath)
                bzip2(inputPath)
                sevenZip(inputPath)
                xzZip(inputPath)
                lzmaZip(inputPath)
//                testZip(inputPath)
            }.start()
        }
    }

    private fun testZip(inputPath: String) {
        val outputPath = inputPath + ".lzma"
        lzmaCompressor.compress(inputPath, outputPath)
        Log.d(TAG, "Done")
    }

    private fun gzip(inputPath: String) {
        runOnUiThread {
            findViewById<ProgressBar>(R.id.gzip_size_progress_bar).isVisible = true
        }
        val outputPath = inputPath + ".gz"
        val startDate = Date()
        gzip.compress(inputPath, outputPath)
        val duration = Date().time - startDate.time

        runOnUiThread {
            findViewById<TextView>(R.id.gzip_duration_text_view).text = "${duration/1000.0} seconds"
            findViewById<TextView>(R.id.gzip_size_text_view).text = "${File(outputPath).length() / 1024} KB"
            findViewById<ProgressBar>(R.id.gzip_size_progress_bar).visibility = View.INVISIBLE
        }
    }

    private fun gzipApache(inputPath: String) {
        runOnUiThread {
            findViewById<ProgressBar>(R.id.gzip_apache_size_progress_bar).isVisible = true
        }
        val outputPath = inputPath + ".apache.gz"
        val startDate = Date()
        gzipLibCompressor.compress(inputPath, outputPath)
        val duration = Date().time - startDate.time

        runOnUiThread {
            findViewById<TextView>(R.id.gzip_apache_duration_text_view).text = "${duration/1000.0} seconds"
            findViewById<TextView>(R.id.gzip_apache_size_text_view).text = "${File(outputPath).length() / 1024} KB"
            findViewById<ProgressBar>(R.id.gzip_apache_size_progress_bar).visibility = View.INVISIBLE
        }
    }

    private fun bzip2(inputPath: String) {
        runOnUiThread {
            findViewById<ProgressBar>(R.id.bzip2_size_progress_bar).isVisible = true
        }
        val outputPath = inputPath + ".bz2"
        val startDate = Date()
        bZip2Compressor.compress(inputPath, outputPath)
        val duration = Date().time - startDate.time

        runOnUiThread {
            findViewById<TextView>(R.id.bzip2_duration_text_view).text = "${duration/1000.0} seconds"
            findViewById<TextView>(R.id.bzip2_size_text_view).text = "${File(outputPath).length() / 1024} KB"
            findViewById<ProgressBar>(R.id.bzip2_size_progress_bar).visibility = View.INVISIBLE
        }
    }

    private fun sevenZip(inputPath: String) {
        runOnUiThread {
            findViewById<ProgressBar>(R.id.seven_zip_size_progress_bar).isVisible = true
        }
        val outputPath = inputPath + ".7z"
        val startDate = Date()
        sevenZip.compress(inputPath, outputPath)
        val duration = Date().time - startDate.time

        runOnUiThread {
            findViewById<TextView>(R.id.seven_zip_duration_text_view).text = "${duration/1000.0} seconds"
            findViewById<TextView>(R.id.seven_zip_size_text_view).text = "${File(outputPath).length() / 1024} KB"
            findViewById<ProgressBar>(R.id.seven_zip_size_progress_bar).visibility = View.INVISIBLE
        }
    }

    private fun xzZip(inputPath: String) {
        runOnUiThread {
            findViewById<ProgressBar>(R.id.xz_zip_size_progress_bar).isVisible = true
        }
        val outputPath = inputPath + ".xz"
        val startDate = Date()
        xzZip.compress(inputPath, outputPath)
        val duration = Date().time - startDate.time

        runOnUiThread {
            findViewById<TextView>(R.id.xz_zip_duration_text_view).text = "${duration/1000.0} seconds"
            findViewById<TextView>(R.id.xz_zip_size_text_view).text = "${File(outputPath).length() / 1024} KB"
            findViewById<ProgressBar>(R.id.xz_zip_size_progress_bar).visibility = View.INVISIBLE
        }
    }

    private fun lzmaZip(inputPath: String) {
        runOnUiThread {
            findViewById<ProgressBar>(R.id.lzma_zip_size_progress_bar).isVisible = true
        }
        val outputPath = inputPath + ".lzma"
        val startDate = Date()
        lzmaCompressor.compress(inputPath, outputPath)
        val duration = Date().time - startDate.time

        runOnUiThread {
            findViewById<TextView>(R.id.lzma_zip_duration_text_view).text = "${duration/1000.0} seconds"
            findViewById<TextView>(R.id.lzma_zip_size_text_view).text = "${File(outputPath).length() / 1024} KB"
            findViewById<ProgressBar>(R.id.lzma_zip_size_progress_bar).visibility = View.INVISIBLE
        }
    }

    private fun requestPermission() {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"Permission is granted");
        } else {
            Log.v(TAG,"Permission is revoked");
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1);
        }
    }

    companion object {
        private val TAG = "MainActivity"
    }
}