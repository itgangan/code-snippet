package com.demo;

import sun.nio.ch.IOUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;

/**
 * The type of code-snippet.
 *
 * <p>
 * .
 *
 * @author ganxiangyong
 */
public class NIOFileCopyDemo {

    public static void main(String[] args) throws IOException {
//        NIOFileCopyDemo demo = new NIOFileCopyDemo();
//        demo.copyFile("E:\\software\\datagrip-2019.1.exe", "E:\\software\\datagrip-2019.1_new.exe");

        System.out.println(SelectionKey.OP_WRITE);
    }

    private void copyFile(String source, String dest) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;



        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(dest);

            inChannel = fis.getChannel();

            outChannel = fos.getChannel();

            final int bufferSize = 1024 * 1024;
            ByteBuffer buf = ByteBuffer.allocate(bufferSize);

            while (inChannel.read(buf) != -1) {
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }
            outChannel.force(true);

        } catch (IOException e) {
            fis.close();
            fos.close();
            inChannel.close();
            outChannel.close();
        }
    }
}

