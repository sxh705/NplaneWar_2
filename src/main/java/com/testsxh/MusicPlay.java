package com.testsxh;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MusicPlay implements Runnable {
    File soundFile;
    Thread thread;
    boolean circulate;
    SourceDataLine auline = null;

    public MusicPlay(String filepath) throws FileNotFoundException {
        this(filepath, false);
    }

    public MusicPlay(String filepath, boolean circulate)
            throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filepath);
        if (!soundFile.exists()) {
            throw new FileNotFoundException(filepath + "aaa");
        }
    }

    public void play() {
        thread = new Thread(this);
        thread.start();
    }

    @SuppressWarnings("deprecation")
    public void stop() {
        //        this.stop();
        thread.stop();

        //        auline.drain();
        System.out.println("AULINE");
        auline = null;
    }

    public void run() {
        byte[] auBuffer = new byte[1024 * 128];
        do {
            AudioInputStream audioInputStream = null;
            auline = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
                auline.start();
                int byteCount = 0;
                while (byteCount != -1) {
                    byteCount = audioInputStream.read(auBuffer, 0, auBuffer.length);
                    if (byteCount >= 0) {
                        auline.write(auBuffer, 0, byteCount);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                auline.drain();
                auline.close();
            }
        } while (circulate);
    }
}