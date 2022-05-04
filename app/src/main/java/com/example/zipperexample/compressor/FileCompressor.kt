package com.example.zipperexample.compressor

import java.io.*
import java.util.zip.GZIPOutputStream

interface FileCompressor {
    fun compress(inputPath: String, outputPath: String)
}