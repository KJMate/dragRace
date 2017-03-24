/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Trajer
 */
public class Zene {

    private Clip clip;

    public Zene(String fileName) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(Zene.class.getResource(fileName));

            clip = AudioSystem.getClip();

            clip.open(ais);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void play() {

        try {

            if (clip != null) {

                new Thread() {

                    public void run() {

                        synchronized (clip) {

                            clip.stop();

                            clip.setFramePosition(0);

                            clip.start();

                        }

                    }

                }.start();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void stop() {

        if (clip == null) {
            return;
        }

        clip.stop();

    }

    public void loop() {
        try {

            if (clip != null) {

                new Thread() {

                    public void run() {

                        synchronized (clip) {

                            clip.stop();

                            clip.setFramePosition(0);

                            clip.loop(Clip.LOOP_CONTINUOUSLY);

                        }

                    }

                }.start();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public boolean isActive() {

        return clip.isActive();

    }
}
