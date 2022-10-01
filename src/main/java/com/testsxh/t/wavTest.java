package com.testsxh.t;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class wavTest {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; ++i) {
            System.out.println(i + "=" + System.currentTimeMillis());
            playSound("sound/bullet.wav");
            System.out.println(System.currentTimeMillis());
            Thread.sleep(500);
        }
    }

    public static void playSound(String url) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
