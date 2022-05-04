package com.example.zipperexample.compressor

import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream


class SevenZipCompressor: FileCompressor {
    override fun compress(inputPath: String, outputPath: String) {
        val instream = BufferedInputStream(FileInputStream(inputPath))

        val sevenZOutput = SevenZOutputFile(File(outputPath))
        val entry = sevenZOutput.createArchiveEntry(File(inputPath), inputPath.split("/").last())
        sevenZOutput.putArchiveEntry(entry)
        val buffer = ByteArray(8024)
        var len: Int
        while (instream.read(buffer).also { len = it } > 0) {
            sevenZOutput.write(buffer, 0, len)
        }

        sevenZOutput.closeArchiveEntry()
        sevenZOutput.close()
        instream.close()
    }
}