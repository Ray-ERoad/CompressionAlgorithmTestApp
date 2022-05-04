package com.example.zipperexample.compressor

import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream
import java.io.BufferedOutputStream
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths


class LZMACompressor: FileCompressor {
    override fun compress(inputPath: String, outputPath: String) {
        val inputStream: InputStream = Files.newInputStream(Paths.get(inputPath))
        val outputStream = Files.newOutputStream(Paths.get(outputPath))
        val out = BufferedOutputStream(outputStream)
        val lzOut = LZMACompressorOutputStream(out)
        val buffer = ByteArray(8024)
        var n = 0
        while (-1 != inputStream.read(buffer).also { n = it }) {
            lzOut.write(buffer, 0, n)
        }
        lzOut.close()
        inputStream.close()
    }

}