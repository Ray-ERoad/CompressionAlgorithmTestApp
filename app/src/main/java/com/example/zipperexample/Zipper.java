package com.example.zipperexample;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Zipper {
    public static byte[] compress(String string) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream(string.length());
        GZIPOutputStream gos = new GZIPOutputStream(os);
        gos.write(string.getBytes());
        gos.close();
        byte[] compressed = os.toByteArray();
        os.close();
        return compressed;
    }

    public static void compressUsingBZIP(String inputPath) throws Exception{
        InputStream in = Files.newInputStream(Paths.get(inputPath));
        OutputStream fout = Files.newOutputStream(Paths.get( inputPath + ".bz2"));
        BufferedOutputStream out = new BufferedOutputStream(fout);
        BZip2CompressorOutputStream bzOut = new BZip2CompressorOutputStream(out);
        final byte[] buffer = new byte[8024];
        int n = 0;
        while (-1 != (n = in.read(buffer))) {
            bzOut.write(buffer, 0, n);
        }
        bzOut.close();
        in.close();
    }

    public static void compressUsingGZIP(String inputPath) throws Exception{
        InputStream in = Files.newInputStream(Paths.get(inputPath));
        OutputStream fout = Files.newOutputStream(Paths.get(inputPath + ".gz"));
        BufferedOutputStream out = new BufferedOutputStream(fout);
        GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(out);
        final byte[] buffer = new byte[8024];
        int n = 0;
        while (-1 != (n = in.read(buffer))) {
            gzOut.write(buffer, 0, n);
        }
        gzOut.close();
        in.close();
    }
}
