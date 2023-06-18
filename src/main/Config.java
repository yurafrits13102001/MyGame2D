package main;

import java.io.*;

public class Config {

    GamePanel gamePanel;

    public Config(GamePanel gamePanel){

        this.gamePanel = gamePanel;
    }

    public void saveConfig() {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            //music value
            bw.write(String.valueOf(gamePanel.music.volumeScale));
            bw.newLine();

            //se value
            bw.write(String.valueOf(gamePanel.sound.volumeScale));
            bw.newLine();

            bw.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadConfig(){

        try {

            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            //music
            gamePanel.music.volumeScale = Integer.parseInt(s);

            //se
            s = br.readLine();
            gamePanel.sound.volumeScale = Integer.parseInt(s);

            br.close();


        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
