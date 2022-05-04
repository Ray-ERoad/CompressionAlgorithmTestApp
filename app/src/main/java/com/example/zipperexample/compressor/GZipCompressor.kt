package com.example.zipperexample.compressor

import java.io.*
import java.util.zip.GZIPOutputStream

class GZipCompressor: FileCompressor {
    override fun compress(inputPath: String, outputPath: String) {
        val data = getStringFromFile(inputPath)
        val compressedData = compress(data)
        writeToFile(outputPath, compressedData!!)
    }

    private fun compress(string: String): ByteArray? {
        val os = ByteArrayOutputStream(string.length)
        val gos = GZIPOutputStream(os)
        gos.write(string.toByteArray())
        gos.close()
        val compressed = os.toByteArray()
        os.close()
        return compressed
    }

    private fun getStringFromFile(filePath: String) : String{
        val fl = File(filePath)
        val fin = FileInputStream(fl)
        val ret: String = convertStreamToString(fin)
        //Make sure you close all streams.
        fin.close()
        return ret
    }

    private fun convertStreamToString(`is`: InputStream?): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var line: String? = null
        while (reader.readLine().also { line = it } != null) {
            sb.append(line).append("\n")
        }
        reader.close()
        return sb.toString()
    }

    private fun writeToFile(outputPath: String, bytes: ByteArray) {
        val outputFile: File = File(outputPath)
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }
        val fos = FileOutputStream(outputFile)
        fos.write(bytes)
        fos.close()
    }
}