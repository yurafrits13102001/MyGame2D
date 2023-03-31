package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;

    URL soundURL[] = new URL[30];

    public Sound(){

        soundURL[0] = getClass().getResource("/res/sounds/back_song_demo.wav");
        soundURL[1] = getClass().getResource("/res/sounds/coin.wav");
    }

    public void setFile(int index) {

        try{

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);



        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(0);

    }

    public void stop() {
        clip.stop();
    }
}
