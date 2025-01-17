package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import interactiveTiles.InteractiveTile;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile

    public final int maxScreenCol = 20;//960
    public final int maxScreenRow = 15;//768
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public int maxWorldCol = 70;
    public int maxWorldRow = 70;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    //FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;


    //FPS
    int FPS = 60;

    public int gameState;
    public int mainMenuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int introState = 5;
    public final int startingGameState = 4;
    public final int inventoryState = 6;
    public final int optionsState = 7;
    public final int gameOverState = 8;
    public final int betaInfoState = 9;

    public TileManager tileManager = new TileManager(this);

    public KeyHandler keyHandler = new KeyHandler(this);
    public PathFinder pFinder = new PathFinder(this);
    public SaveLoad saveLoad = new SaveLoad(this);

    Sound sound = new Sound();
    Sound music = new Sound();

    Config config = new Config(this);


    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;


    //ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);

    public Entity[] obj = new Entity[20];
    public Entity[] npc = new Entity[10];

    public Entity[] monster = new Entity[20];
    public InteractiveTile[] iTile = new InteractiveTile[80];

    public ArrayList<Entity> particleList = new ArrayList<>();

    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

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

    public void resetGame(boolean restart) {

        player.setDefaultPositions();
        player.restoreLifeAndMana();
        assetSetter.setNpc();
        assetSetter.setMonster();

        if (restart == true) {
            player.setDefaultValues();
            player.setInventory();
            assetSetter.setObject();
            assetSetter.setInteractive();
        }

    }

//    public void retry(){
//
//        player.setDefaultPositions();
//        player.restoreLifeAndMana();
//        assetSetter.setNpc();
//        assetSetter.setMonster();
//    }
//    public void restart(){
//        player.setDefaultValues();
//        player.setDefaultPositions();
//        player.restoreLifeAndMana();
//        player.setInventory();
//        assetSetter.setObject();
//        assetSetter.setNpc();
//        assetSetter.setMonster();
//        assetSetter.setInteractive();
//    }

    public void setupGame() {

        gameState = introState;


        assetSetter.setObject();
        assetSetter.setNpc();
        assetSetter.setMonster();
        assetSetter.setInteractive();



        gameState = introState;





        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

//        setFullScreen();

    }


    public void setFullScreen() {

        //get local screen device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

//        get full screen width and height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
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
//                repaint();
                drawToTempScreen();
                drawToScreen();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {







        if (gameState == playState) {

            //start


            //player update
            player.update();

            //npc update
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }

            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false) {

                        monster[i].update();
                    }
                    if (monster[i].alive == false) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }

                }
            }


            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].update();
                }
            }

            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].update();
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    particleList.get(i).update();
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }


            }
            if (gameState == pauseState) {
                //nothing
            }


        }
    }

    public void drawToTempScreen() {

        //INVENTORY
        if (gameState == inventoryState) {
            ui.draw(g2);
        }


        //MAIN MENU
        if (gameState == introState) {
            ui.draw(g2);
        }

        if (gameState == mainMenuState) {
            ui.draw(g2);


        }
        //OThERS
        else {
            //TILE
            tileManager.draw(g2);

            //ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    entityList.add(iTile[i]);
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }

            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }

            }


            //SORT

            Collections.sort(entityList, (e1, e2) -> {

                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            });

            //DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            entityList.clear();

            //UI

            ui.draw(g2);


        }
    }

    public void drawToScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

//    public void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//
//        Graphics2D g2 = (Graphics2D) g;
//
//        //INVENTORY
//        if (gameState == inventoryState) {
//            ui.draw(g2);
//        }
//
//
//        //MAIN MENU
//        if (gameState == introState) {
//            ui.draw(g2);
//        }
//
//        if (gameState == mainMenuState) {
//
//
//            ui.draw(g2);
//
//
//        }
//        //OThERS
//        else {
//
//
//            //TILE
//            tileManager.draw(g2);
//
//
//            //ADD ENTITIES TO THE LIST
//            entityList.add(player);
//
//
//            for (int i = 0; i < npc.length; i++) {
//                if (npc[i] != null) {
//                    entityList.add(npc[i]);
//                }
//            }
//
//            for (int i = 0; i < monster.length; i++) {
//                if (monster[i] != null) {
//                    entityList.add(monster[i]);
//                }
//            }
//
//            for (int i = 0; i < obj.length; i++) {
//                if (obj[i] != null) {
//                    entityList.add(obj[i]);
//                }
//            }
//            for (int i = 0; i < iTile.length; i++) {
//                if (iTile[i] != null) {
//                    entityList.add(iTile[i]);
//                }
//            }
//            for (int i = 0; i < particleList.size(); i++) {
//                if (particleList.get(i) != null) {
//                    entityList.add(particleList.get(i));
//                }
//
//            }
//
//            for (int i = 0; i < projectileList.size(); i++) {
//                if (projectileList.get(i) != null) {
//                    entityList.add(projectileList.get(i));
//                }
//
//            }
//
//
//            //SORT
//
//            Collections.sort(entityList, (e1, e2) -> {
//
//                int result = Integer.compare(e1.worldY, e2.worldY);
//                return result;
//            });
//
//            //DRAW ENTITIES
//            for (int i = 0; i < entityList.size(); i++) {
//                entityList.get(i).draw(g2);
//            }
//            //EMPTY ENTITY LIST
//            entityList.clear();
//
//
//            //UI
//
//            ui.draw(g2);
//
//
//        }
//    }






    public void playMusic(int index) {

        music.setFile(index);
        music.play();
        music.loop();

    }

    public void playSound(int index) {

        sound.setFile(index);
        sound.play();


    }

    public void stopMusic() {
        music.stop();
    }


}
