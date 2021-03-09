package util;

import engine.Soundable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SoundLoader {
    public static SoundLoader sl = new SoundLoader();

    public static Soundable getSound(String fileName) {
        InputStream input = sl.getClass().getResourceAsStream("/" + fileName);

        AudioInputStream ais = null;
        Clip clip = null;

        try {

            clip = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(input);

            clip.open(ais);
        } catch (Exception e) {
            System.out.println("SOUND LOAD FAILURE: " + fileName);

            return null;
        }

        System.out.println("LOADED: " + fileName);

        Soundable s = new Soundable(clip, false);
        s.start();

        return s;

    }
}
