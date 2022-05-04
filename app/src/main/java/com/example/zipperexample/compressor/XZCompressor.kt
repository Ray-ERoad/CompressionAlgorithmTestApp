package com.example.zipperexample.compressor

import org.tukaani.xz.LZMA2Options

import org.tukaani.xz.XZOutputStream
import java.io.*


class XZCompressor : FileCompressor {
    override fun compress(inputPath: String, outputPath: String) {
        val inputString = getStringFromFile(inputPath)
        val xzOutput = ByteArrayOutputStream()
        val xzStream = XZOutputStream(xzOutput, LZMA2Options(LZMA2Options.PRESET_DEFAULT))
        xzStream.write(inputString.toByteArray())
        xzStream.close()
        val compressedData = xzOutput.toByteArray()
        writeToFile(outputPath, compressedData)
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