package main;

import entity.Player;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public int maxWorldCol = 70;
    public int maxWorldRow = 70;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);

    KeyHandler keyHandler = new KeyHandler();

    Sound sound = new Sound();


    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;



    //ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);

    public SuperObject[] obj = new SuperObject[10];

    //set player's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;


    public GamePanel() throws IOException {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void setupGame(){
        assetSetter.setObject();

        playMusic(0);
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

       player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //TILE
        tileManager.draw(g2);

        //ITEMS
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }


        //PLAYER
        player.draw(g2);

        //UI

        ui.draw(g2);

        g2.dispose();


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
