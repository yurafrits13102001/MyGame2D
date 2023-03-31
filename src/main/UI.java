package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gamePanel;
    Font arial_40;
    BufferedImage purpleKeyImage;
    BufferedImage blueKeyImage;
    BufferedImage orangeKeyImage;

    public UI(GamePanel gamePanel){
      this.gamePanel = gamePanel;

      arial_40 = new Font("Arial", Font.PLAIN,40);

        OBJ_Key purpleKey = new OBJ_Key("Key_Purple");
        OBJ_Key blueKey = new OBJ_Key("Key_Blue");
        OBJ_Key orangeKey = new OBJ_Key("Key_Orange");
        purpleKeyImage = purpleKey.image;
        blueKeyImage = blueKey.image;
        orangeKeyImage = orangeKey.image;
    }

    public void draw(Graphics2D graphics2D){

        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);
        graphics2D.drawImage(purpleKeyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawImage(blueKeyImage, gamePanel.tileSize/2, gamePanel.tileSize + gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawImage(orangeKeyImage, gamePanel.tileSize/2, gamePanel.tileSize + gamePanel.tileSize + gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawString("x" + gamePanel.player.hasPurpleKey, 70, 68);
        graphics2D.drawString("x" + gamePanel.player.hasBlueKey, 70, 118);
        graphics2D.drawString("x" + gamePanel.player.hasOrangeKey, 70, 166);
    }
}
