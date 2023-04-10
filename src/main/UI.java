package main;

import object.OBJ_Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    GamePanel gamePanel;
    Font arial_40;
    BufferedImage purpleKeyImage;
    BufferedImage blueKeyImage;
    BufferedImage orangeKeyImage;
    Graphics2D g2;

    public UI(GamePanel gamePanel){
      this.gamePanel = gamePanel;

      arial_40 = new Font("Arial", Font.PLAIN,40);

        OBJ_Key purpleKey = new OBJ_Key("Key_Purple", gamePanel);
        OBJ_Key blueKey = new OBJ_Key("Key_Blue", gamePanel);
        OBJ_Key orangeKey = new OBJ_Key("Key_Orange", gamePanel);
        purpleKeyImage = purpleKey.image;
        blueKeyImage = blueKey.image;
        orangeKeyImage = orangeKey.image;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(purpleKeyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawImage(blueKeyImage, gamePanel.tileSize/2, gamePanel.tileSize + gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawImage(orangeKeyImage, gamePanel.tileSize/2, gamePanel.tileSize + gamePanel.tileSize + gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawString("x" + gamePanel.player.hasPurpleKey, 70, 68);
        g2.drawString("x" + gamePanel.player.hasBlueKey, 70, 118);
        g2.drawString("x" + gamePanel.player.hasOrangeKey, 70, 166);

        if(gamePanel.gameState == gamePanel.playState){

        }
        if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {

        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/sprite_pause30.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = "PAUSED";
        int x = getXForCenteredText(text);

        int y = gamePanel.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {

        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gamePanel.screenWidth/2 - lenght/2;
        return x;

    }
}
