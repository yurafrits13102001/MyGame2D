package main;

import javax.sound.sampled.*;
import java.awt.geom.Arc2D;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;

    URL soundURL[] = new URL[30];

    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound(){

        soundURL[0] = getClass().getResource("/sounds/back_song_demo.wav");
        soundURL[1] = getClass().getResource("/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/sounds/sound83.wav");
        soundURL[3] = getClass().getResource("/sounds/20279__koops__apple_crunch_16.wav");
        soundURL[4] = getClass().getResource("/sounds/korotkiy-moschnyiy-zamah.wav");
        soundURL[5] = getClass().getResource("/sounds/korotkiy-gluhoy-zvuk-udara-po-derevu.wav");
        soundURL[6] = getClass().getResource("/sounds/Sound - Magic - FIRECAST.wav");
        soundURL[7] = getClass().getResource("/sounds/soundOuchMarichka.wav");
        soundURL[8] = getClass().getResource("/sounds/fireballHit2.wav");
        soundURL[9] = getClass().getResource("/sounds/drink.wav");
        soundURL[10] = getClass().getResource("/sounds/openDoor.wav");
        soundURL[11] = getClass().getResource("/sounds/doorOpen2.wav");
        soundURL[12] = getClass().getResource("/sounds/restoring.wav");
    }

    public void setFile(int index) {

        try{

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();



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

    public void checkVolume(){

        switch(volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
