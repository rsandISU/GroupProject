package engine;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Soundable extends Thread{
    private Clip clip;
    private boolean repeats;
    private FloatControl gainControl;


    private boolean isRunning;
    private boolean callRun;


    private float volume;
    private static float globalVolume = 1.0F;

    public Soundable(Clip clip, boolean repeats) {
        this.clip = clip;
        this.repeats = repeats;

        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        setVolume(1.0F);

        callRun = false;
    }

    public void run() {
        while (true) {

            if (callRun) {
                callRun = false;
                do {
                    startPlay();
                    if (!isRunning) break;
                } while (repeats);
            }

            dwell();
        }
    }

    private void startPlay() {
        isRunning = true;

        clip.setFramePosition(0);
        clip.start();

        //Wait for clip to start
        while (!clip.isRunning()) dwell();

        //Terrible clip polling code
        while (clip.isRunning()) {

            dwell();

            if (!isRunning) {
                clip.stop();
            }
        }

    }

    private void dwell() {
        //Wait a bit to not eat up all the CPU time
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void haltClip() {
        isRunning = false;
        callRun = false;
    }

    public void play() {
        haltClip();
        callRun = true;
    }

    public boolean clipPlaying() {
        return clip.isRunning();
    }

    private void updateVolume() {
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * (volume * globalVolume)) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public boolean isRepeats() {
        return repeats;
    }

    public void setRepeats(boolean repeats) {
        this.repeats = repeats;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        updateVolume();
    }

    public static float getGlobalVolume() {
        return globalVolume;
    }

    public void setGlobalVolume(float globalVolume) {
        this.globalVolume = globalVolume;
        updateVolume();
    }
}
