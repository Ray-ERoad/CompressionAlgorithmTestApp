package com.example.zipperexample.compressor

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream
import java.io.BufferedOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Paths


class BZip2Compressor: FileCompressor {
    override fun compress(inputPath: String, outputPath: String) {
        val inputStream: InputStream = Files.newInputStream(Paths.get(inputPath))
        val outputStream: OutputStream = Files.newOutputStream(Paths.get(outputPath))
        val out = BufferedOutputStream(outputStream)
        val bzOut = BZip2CompressorOutputStream(out)
        val buffer = ByteArray(8024)
        var n = 0
        while (-1 != inputStream.read(buffer).also { n = it }) {
            bzOut.write(buffer, 0, n)
        }
        bzOut.close()
        inputStream.close()
        outputStream.close()
        out.close()
    }
}