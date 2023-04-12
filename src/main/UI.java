package main;

import object.OBJ_Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gamePanel;
    Font daydream, gamer;
    BufferedImage purpleKeyImage;
    BufferedImage blueKeyImage;
    BufferedImage orangeKeyImage;
    Graphics2D g2;

    public String currentDialogue = "";
    public int dialogueIndex = 0;
    public int commandNum = 0;

    public UI(GamePanel gamePanel) {
      this.gamePanel = gamePanel;

      try {

          InputStream is = getClass().getResourceAsStream("/res/fonts/Daydream.ttf");
          daydream = Font.createFont(Font.TRUETYPE_FONT, is);

          InputStream is2 = getClass().getResourceAsStream("/res/fonts/Gamer.ttf");
          gamer = Font.createFont(Font.TRUETYPE_FONT, is2);
      }catch (TypeNotPresentException | FontFormatException | IOException e){
          e.printStackTrace();
      }

        OBJ_Key purpleKey = new OBJ_Key("Key_Purple", gamePanel);
        OBJ_Key blueKey = new OBJ_Key("Key_Blue", gamePanel);
        OBJ_Key orangeKey = new OBJ_Key("Key_Orange", gamePanel);
        purpleKeyImage = purpleKey.image;
        blueKeyImage = blueKey.image;
        orangeKeyImage = orangeKey.image;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(gamer);
        g2.setColor(Color.white);
        g2.drawImage(purpleKeyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawImage(blueKeyImage, gamePanel.tileSize/2, gamePanel.tileSize + gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawImage(orangeKeyImage, gamePanel.tileSize/2, gamePanel.tileSize + gamePanel.tileSize + gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawString("x" + gamePanel.player.hasPurpleKey, 70, 68);
        g2.drawString("x" + gamePanel.player.hasBlueKey, 70, 118);
        g2.drawString("x" + gamePanel.player.hasOrangeKey, 70, 166);

        if(gamePanel.gameState == gamePanel.mainMenuState){
            drawMainMenuScreen();
        }

        if(gamePanel.gameState == gamePanel.playState){

        }
        if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }
        if(gamePanel.gameState == gamePanel.dialogueState){
            drawDialogueScreen();

        }
    }

    public void drawMainMenuScreen(){

        g2.setColor(new Color(150, 80, 80));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        //TILE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "Marichka Advanture 2D";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenHeight/2;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.PINK);
        g2.drawString(text, x, y);

        x = gamePanel.screenWidth/2;
        y = (gamePanel.screenHeight/2) - 117;
        g2.drawImage(gamePanel.player.stay1, x, y,gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);

        text = "New Game";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize * 4;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "Continue Game";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize + 24;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "Quit";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize + 24;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x - gamePanel.tileSize, y);
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

    public void drawDialogueScreen(){

        //WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize/2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize*2);
        int height = gamePanel.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));


        x += gamePanel.tileSize;
        y += gamePanel.tileSize;


        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }



    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);


    }

    public int getXForCenteredText(String text) {

        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gamePanel.screenWidth/2 - lenght/2;
        return x;

    }

}
