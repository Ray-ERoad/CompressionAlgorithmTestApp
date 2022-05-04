package com.example.zipperexample.compressor

import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream
import java.io.BufferedOutputStream
import java.nio.file.Files
import java.nio.file.Paths

class GZIPLibCompressor: FileCompressor {
    override fun compress(inputPath: String, outputPath: String) {
        val inputStream = Files.newInputStream(Paths.get(inputPath))
        val outputStream = Files.newOutputStream(
            Paths.get(
                outputPath
            )
        )
        val out = BufferedOutputStream(outputStream)
        val gzOut = GzipCompressorOutputStream(out)
        val buffer = ByteArray(8024)
        var n = 0
        while (-1 != inputStream.read(buffer).also { n = it }) {
            gzOut.write(buffer, 0, n)
        }
        gzOut.close()
        inputStream.close()
    }
}