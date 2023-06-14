package main;

import entity.Entity;
import object.OBJ_Apple;
import object.OBJ_Coin;
import object.OBJ_Health;
import object.OBJ_Mana;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gamePanel;

    Font daydream, gamer;
    BufferedImage apple1;
    BufferedImage heartFull, heartHalf, heartBlank;
    BufferedImage manaFull, manaHalf, manaBlank;


    Graphics2D g2;

    public String currentDialogue = "";
    public String currentSpeech = "";
    public int dialogueIndex = 0;
    public int commandNum = 0;

    public int slotCol = 0;
    public int slotRow = 0;
    public int subState = 0;
    public ArrayList<String> message = new ArrayList<>();
    public ArrayList<Integer> messageCounter = new ArrayList<>();

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {

            InputStream is = getClass().getResourceAsStream("/fonts/Daydream.ttf");
            daydream = Font.createFont(Font.TRUETYPE_FONT, is);

            InputStream is2 = getClass().getResourceAsStream("/fonts/Dited.ttf");
            gamer = Font.createFont(Font.TRUETYPE_FONT, is2);
        } catch (TypeNotPresentException | FontFormatException | IOException e) {
            e.printStackTrace();
        }

        //KEYS


        //DOORS



        //HEALTH
        Entity heart = new OBJ_Health(gamePanel);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartBlank = heart.image3;

        //MANA
        Entity mana = new OBJ_Mana(gamePanel);
        manaFull = mana.image;
        manaHalf = mana.image2;
        manaBlank = mana.image3;


        //APPLE
        OBJ_Apple apple = new OBJ_Apple("Apple", gamePanel);
        apple1 = apple.imageApple;

        OBJ_Coin coin = new OBJ_Coin(gamePanel);
        apple1 = coin.image;


    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(gamer);
        g2.setColor(Color.white);
//        g2.drawImage(apple1, 600, 68, gamePanel.tileSize, gamePanel.tileSize, null);
//
//        g2.drawString("coins" + gamePanel.player.value, 70, 68);


        if (gamePanel.gameState == gamePanel.introState) {
            //drawIntroScreen();

        }

        if (gamePanel.gameState == gamePanel.mainMenuState) {
            drawMainMenuScreen();
        }

        if (gamePanel.gameState == gamePanel.playState) {
            drawPlayerHealth();
            drawPlayerMana();
            drawMessage();

        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPlayerHealth();
            drawPlayerMana();

            drawPauseScreen();
        }
        if (gamePanel.gameState == gamePanel.dialogueState) {
            drawPlayerHealth();
            drawPlayerMana();
            drawDialogueScreen();

        }
        if(gamePanel.gameState == gamePanel.inventoryState){
            drawInventory();
            drawPlayerHealth();
            drawPlayerMana();
        }
        if(gamePanel.gameState == gamePanel.optionsState){
            drawOptionScreen();
        }

    }

    public void addMessage(String text){

        message.add(text);
        messageCounter.add(0);
    }

    public void drawMessage(){

        int messageX = gamePanel.tileSize;
        int messageY = gamePanel.tileSize * 4;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32));

        for(int i = 0; i < message.size(); i++){

            if(message.get(i) != null){

                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawPlayerMana() {

        int x = gamePanel.tileSize / 2;
        int y = gamePanel.tileSize / 2 + gamePanel.tileSize;
        int i = 0;

        //draw max life

        while (i < gamePanel.player.maxMana / 2) {
            g2.drawImage(manaBlank, x, y, null);
            i++;
            x += gamePanel.tileSize;
        }

        //reset

        x = gamePanel.tileSize / 2;
        y = gamePanel.tileSize / 2 + gamePanel.tileSize;
        i = 0;

        //draw current life
        while (i < gamePanel.player.mana) {
            g2.drawImage(manaHalf, x, y, null);
            i++;
            if (i < gamePanel.player.mana) {
                g2.drawImage(manaFull, x, y, null);
                i++;
                x += gamePanel.tileSize;
            }


        }

    }



    public void drawPlayerHealth() {

        int x = gamePanel.tileSize / 2;
        int y = gamePanel.tileSize / 2;
        int i = 0;

        //draw max life

        while (i < gamePanel.player.maxLife / 2) {
            g2.drawImage(heartBlank, x, y, null);
            i++;
            x += gamePanel.tileSize;
        }

        //reset

        x = gamePanel.tileSize / 2;
        y = gamePanel.tileSize / 2;
        i = 0;

        //draw current life
        while (i < gamePanel.player.life) {
            g2.drawImage(heartHalf, x, y, null);
            i++;
            if (i < gamePanel.player.life) {
                g2.drawImage(heartFull, x, y, null);
                i++;
                x += gamePanel.tileSize;
            }


        }
    }

    public void drawMainMenuScreen() {

        g2.setColor(new Color(150, 80, 80));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        //TILE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "Marichka Advanture 2D";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenHeight / 2;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.PINK);
        g2.drawString(text, x, y);

        x = gamePanel.screenWidth / 2;
        y = (gamePanel.screenHeight / 2) - 117;
        g2.drawImage(gamePanel.player.stay1, x, y, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);


        text = "New Game";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize * 4;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "Continue Game";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize + 24;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "Quit";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize + 24;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gamePanel.tileSize, y);
        }

    }

    public void drawIntroScreen() {
        BufferedImage[] images = new BufferedImage[10];
        try {
            images[0] = ImageIO.read(getClass().getResourceAsStream("/slides/alarmClock1.jpg"));

            images[1] = ImageIO.read(getClass().getResourceAsStream("/slides/marichkaSlide1.jpg"));
            images[1].getScaledInstance(gamePanel.screenWidth,gamePanel.screenHeight,Image.SCALE_DEFAULT);
            images[2] = ImageIO.read(getClass().getResourceAsStream("/slides/marichkaSlide2.jpg"));
            images[3] = ImageIO.read(getClass().getResourceAsStream("/slides/marichkaSlide3.jpg"));
            images[4] = ImageIO.read(getClass().getResourceAsStream("/slides/marichkaSlide$.jpg"));
            images[5] = ImageIO.read(getClass().getResourceAsStream("/slides/marichkaSlide5.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Graphics2D g2 = (Graphics2D) gamePanel.getGraphics();
        float alpha = 0f;
        int index = 0;

        while (alpha <= 1f) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            BufferedImage image = images[index];
            double x = (gamePanel.screenWidth - 960) / 2;
            double y = (gamePanel.screenHeight - 768) / 2;
            AffineTransform transform = AffineTransform.getTranslateInstance(x, y);
            transform.translate(x, y);
            transform.scale((double) gamePanel.screenWidth / image.getWidth(), (double) gamePanel.screenHeight / image.getHeight());
            g2.drawImage(image, transform, null);
//            g2.drawImage(images[index], 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
            alpha += 0.01f;

            try {
                Thread.sleep(30); // чекаємо 30 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(3000); // чекаємо 3 секунди
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alpha = 0f;


        while (alpha <= 1f) {
            index = 1;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            BufferedImage image = images[index];
            double x = (gamePanel.screenWidth - 960) / 2;
            double y = (gamePanel.screenHeight - 768) / 2;
            AffineTransform transform = AffineTransform.getTranslateInstance(x, y);
            transform.translate(x, y);
            g2.drawImage(image, transform, null);
            alpha += 0.01f;

            try {
                Thread.sleep(30); // чекаємо 30 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(3000); // чекаємо 3 секунди
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alpha = 0f;

        while (alpha <= 1f) {
            index = 2;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            BufferedImage image = images[index];
            int x = (gamePanel.screenWidth - image.getWidth()) / 2;
            int y = (gamePanel.screenHeight - image.getHeight()) / 2;
            AffineTransform transform = AffineTransform.getTranslateInstance(x, y);
            g2.drawImage(image, transform, null);
            alpha += 0.01f;

            try {
                Thread.sleep(30); // чекаємо 30 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(3000); // чекаємо 3 секунди
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alpha = 0f;

        while (alpha <= 1f) {
            index = 3;
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(images[index], 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
            alpha += 0.01f;

            try {
                Thread.sleep(30); // чекаємо 30 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(3000); // чекаємо 3 секунди
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alpha = 0f;

        while (alpha <= 1f) {
            index = 4;
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(images[index], 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
            alpha += 0.01f;

            try {
                Thread.sleep(30); // чекаємо 30 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(3000); // чекаємо 3 секунди
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alpha = 0f;

        while (alpha <= 1f) {
            index = 5;
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(images[index], 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
            alpha += 0.01f;

            try {
                Thread.sleep(30); // чекаємо 30 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(3000); // чекаємо 3 секунди
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alpha = 0f;
    }

    public void drawInventory(){

        //FRAME
        int frameX = gamePanel.tileSize * 11;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize * 8;
        int frameHeight = gamePanel.tileSize * 7;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        //CURSOR
        int cursorX = slotXStart + (gamePanel.tileSize * slotCol);
        int cursorY = slotYStart + (gamePanel.tileSize * slotRow);
        int cursorWidth = gamePanel.tileSize;
        int cursorHeight = gamePanel.tileSize;

        //DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        //DRAW INVENTORY
        for(int i = 0; i < gamePanel.player.inventory.size(); i++){

            //DRAW ITEM EQUIP
            if(gamePanel.player.inventory.get(i) == gamePanel.player.currentInstrument){
                g2.setColor(new Color(234, 134, 243));
                g2.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }

            g2.drawImage(gamePanel.player.inventory.get(i).stay1, slotX, slotY, null);

            slotX += gamePanel.tileSize;


        }

        //DESCRIPTION FRAME

        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gamePanel.tileSize * 3;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        //DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gamePanel.tileSize;
        g2.setFont(g2.getFont().deriveFont(40F));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gamePanel.player.inventory.size()){



            for(String line: gamePanel.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
        if(itemIndex >= gamePanel.player.inventory.size()){
            g2.drawString("< none >", textX, textY);
        }



    }
    public void drawOptionScreen(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //sub window
        int frameX = gamePanel.tileSize*6;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize*8;
        int frameHeight = gamePanel.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState){
            case 0: optionsTop(frameX, frameY); break;
            case 1: optionsControl(frameX, frameY); break;
            case 3: mainMenuConfirmation(frameX, frameY); break;
        }
    }

    public void optionsTop(int frameX, int frameY){

        int textX;
        int textY;

        //title
        String text = "Options";
        textX = getXForCenteredText(text);
        textY = frameY + gamePanel.tileSize;
        g2.drawString(text, textX, textY);

        //music
        textX = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize*2;
        g2.drawString("Music", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
        }


        //se
        textY += gamePanel.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
        }


        //control
        textY += gamePanel.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true){
                subState = 1;
                commandNum = 0;
                gamePanel.keyHandler.enterPressed = false;

            }
        }

        //end game
        textY += gamePanel.tileSize;
        g2.drawString("Main Menu", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true){
                subState = 3;
                commandNum = 0;
                gamePanel.keyHandler.enterPressed = false;

            }
        }

        //back
        textY += gamePanel.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true){
                gamePanel.gameState = gamePanel.playState;
                gamePanel.keyHandler.enterPressed = false;

            }
        }

        //music volume
        textX = frameX +  gamePanel.tileSize*5;
        textY = frameY + gamePanel.tileSize*2 + 24;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gamePanel.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);


        //se volume
        textY += gamePanel.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gamePanel.sound.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
    }

    public void optionsControl(int frameX, int frameY){

        int textX;
        int textY;

        //title
        String text = "Control";
        textX = getXForCenteredText(text);
        textY = frameY + gamePanel.tileSize;

        g2.drawString(text, textX, textY);

        textX = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize;
        g2.drawString("Move", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("Confirm", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("Use instrument", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("Inventory Screen", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("Pause", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("Options", textX, textY); textY += gamePanel.tileSize;

        textX = frameX + gamePanel.tileSize*6;
        textY = frameY + gamePanel.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("Enter", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("K", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("J", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("C", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("P", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("ESC", textX, textY); textY += gamePanel.tileSize;

        //back
        textX = frameX + gamePanel.tileSize;
        textY = frameY + gamePanel.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true){
                subState = 0;
                commandNum = 0;
            }
        }


    }

    public void mainMenuConfirmation(int frameX, int frameY){

        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize*3;

        currentDialogue = "Are you sure to exit?";

        for(String line: currentDialogue.split("/n")) {

            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //yes
        String text = "Yes";
        textX = getXForCenteredText(text);
        textY = gamePanel.tileSize*5;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true){
                subState = 0;
                gamePanel.gameState = gamePanel.mainMenuState;
                gamePanel.keyHandler.enterPressed = false;
            }
        }


        //no
        text = "No";
        textX = getXForCenteredText(text);
        textY = gamePanel.tileSize*7;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true){
                subState = 0;
                commandNum = 3;
                gamePanel.keyHandler.enterPressed = false;



            }
        }
    }

    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow * 6);
        return itemIndex;
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

    public void drawDialogueScreen() {

        //WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 2);
        int height = gamePanel.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));


        x += gamePanel.tileSize;
        y += gamePanel.tileSize;


        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }


    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);


    }

    public int getXForCenteredText(String text) {

        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gamePanel.screenWidth / 2 - lenght / 2;
        return x;

    }

}
