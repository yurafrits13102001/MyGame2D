package main;

import entity.Entity;
import entity.Player;
import javafx.geometry.Rectangle2D;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16
            ; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile

    public final int maxScreenCol = 20;//960
    public final int maxScreenRow = 16;//768
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public int maxWorldCol = 70;
    public int maxWorldRow = 70;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    public int gameState;
    public int mainMenuState = 0;
    public int playState = 1;
    public int pauseState = 2;
    public int dialogueState = 3;
    public int introState = 5;
    public int startingGameState = 4;
    public int inventoryState = 6;

    TileManager tileManager = new TileManager(this);

    public KeyHandler keyHandler = new KeyHandler(this);

    Sound sound = new Sound();


    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;



    //ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);

    public Entity[] obj = new Entity[10];
    public Entity[] npc = new Entity[10];

    public Entity[] monster = new Entity[20];

    ArrayList<Entity> entityList = new ArrayList<>();

    //set player's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;


    public GamePanel() throws IOException, FontFormatException {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);


    }

    public void setupGame(){

        gameState = introState;




        assetSetter.setObject();
        assetSetter.setNpc();
        assetSetter.setMonster();

        playMusic(0);

        gameState = mainMenuState;





       ;;

    }

    public void drawIntroScreen() {


        try {
            // Створення масиву зображень для слайд-шоу
            Image[] images = new Image[3];
            images[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/slides/image1.png")));
            images[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/slides/image2.png")));
            images[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/slides/image3.png")));

            Dimension size = getSize();
            for (int i = 0; i < images.length; i++) {
                images[i] = images[i].getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            }

            // Створення нового об'єкту SlideshowPanel
            SlideshowPanel slideshowPanel = new SlideshowPanel(images, 5000);


            // Додавання об'єкту SlideshowPanel до JPanel
            this.add(slideshowPanel);
            this.revalidate();
            this.repaint();

            // Початок показу слайдів
            slideshowPanel.start();

            // Очікування до закінчення слайд-шоу
            Thread.sleep(15000);

            // Зупинка показу слайдів
            slideshowPanel.stop();

            // Видалення об'єкту SlideshowPanel з JPanel
            this.remove(slideshowPanel);
            this.revalidate();
            this.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }







    @Override
//    public void run() {
//
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while(gameThread != null){
//
//           long currentTime = System.nanoTime();
//            System.out.println("Current time: " + currentTime);
//
//            // 1 UPDATE: update information such as character position
//            update();
//
//            //2 DRAW: draw the screen with the updated information
//            repaint();
//
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//
//                }
//                Thread.sleep((long)remainingTime);
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }
//    }

    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {



        if(gameState == playState) {

            //start


            //player update
            player.update();

            //npc update
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    npc[i].update();
                }

            }
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    monster[i].update();
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if(obj[i] != null){
                   obj[i].update();
                }
            }


        }
        if(gameState == pauseState){
            //nothing
        }






    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;



        //MAIN MENU
        if(gameState == introState){
            ui.draw(g2);
        }

        if(gameState == mainMenuState){
            ui.draw(g2);


        }
        //OThERS
        else{



            //TILE
            tileManager.draw(g2);


            //ADD ENTITIES TO THE LIST
            entityList.add(player);


            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            //SORT

            Collections.sort(entityList, (e1, e2) -> {

                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            });

            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            entityList.clear();









            //UI

            ui.draw(g2);



        }




    }

    public void playMusic(int index) {

        sound.setFile(index);
        sound.play();
        sound.loop();

    }

    public void stopMusic(){
        sound.stop();
    }


}
