package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;

    public Tile[] tiles;

    public int[][] mapTileNumber;


    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tiles = new Tile[40];

        mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        int[][] map = {
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,3,3,3,3,3,3,3},
                {3,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3,3,2,4,4,4,2,3,3,6,6,6,6,6},
                {3,4,4,4,4,4,4,26,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3,3,2,4,4,4,2,3,6,6,6,6,6,6},
                {3,3,3,3,3,3,3,24,22,22,22,22,22,22,22,22,22,22,22,22,22,26,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3,3,2,4,4,4,2,3,6,6,6,6,6,6},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,24,22,22,22,22,22,27,3,3,3,3,3,3,3,3,3,3,2,2,4,2,2,3,6,6,6,6,6,6},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3,3,3,2,4,2,3,6,6,6,6,6,6,6},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,3,3,3,3,3,3,3,3,3,3,3,2,21,2,3,6,6,6,6,6,6,6},
                {0,0,0,0,0,0,0,0,0,0,23,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,4,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,6,6,6,6,6,6,6},
                {0,0,22,22,22,22,22,22,22,22,25,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,6,6,6,6,6,6},
                {0,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,21,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,6,6,6,6},
                {0,9,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,3,21,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,6,6,6,6},
                {0,15,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,14,3,5,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,6,6,6},
                {0,15,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,14,3,5,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,6,6},
                {0,15,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,14,3,5,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3},
                {0,15,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,14,3,5,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3},
                {0,15,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,14,3,5,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3},
                {0,15,16,16,16,16,16,16,16,16,16,16,16,16,17,10,10,10,10,10,10,10,10,10,10,12,3,5,3,3,3,3,3,3,3,3,3,3,3,3,21,3,3,3,3,3,3,3,3,3},
                {0,13,10,10,10,10,10,10,10,10,10,10,10,10,11,0,0,0,0,0,0,3,5,3,0,0,0,0,0,3,3,3,3,3,3,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,5,3,0,0,0,0,3,3,4,4,4,4,4,4,3,3,3,3,3,3,3,3,3},
                {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,0,0,0,0,0,0,0,0,3,5,3,0,0,0,3,3,4,4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,3,3,3,3,3,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,5,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,5,3,3,3,3,3,3,3,3,4,4,4,4,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,5,3,3,3,3,3,3,3,3,4,0,6,6,6,6,0,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,3,3,3,3,3,3,3,3,4,3,6,6,6,6,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,4,4,3,3,3,3,4,6,6,6,6,6,6,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,0,0,0,0,4,6,6,6,6,6,6,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,6,6,6,6,6,6,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,6,6,6,6,6,6,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,6,6,6,6,6,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0}

        };

        getTileImage();
        loadMap(map);
    }

    public void getTileImage() {

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_trava0.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_marichka_backdround1.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_0.png")));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_world_marichka2281.png")));
            tiles[3].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_world_marichka2282.png")));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_world_marichka2283.png")));

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_world_marichka2284.png")));
            tiles[6].collision = true;

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss00.png")));
            tiles[7].collision = true;

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss01.png")));
            tiles[8].collision = true;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss02.png")));
            tiles[9].collision = true;

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss03.png")));
            tiles[10].collision = true;

            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss04.png")));
            tiles[11].collision = true;

            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss05.png")));
            tiles[12].collision = true;

            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss06.png")));
            tiles[13].collision = true;

            tiles[14] = new Tile();
            tiles[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss07.png")));
            tiles[14].collision = true;

            tiles[15] = new Tile();
            tiles[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss08.png")));
            tiles[15].collision = true;

            tiles[16] = new Tile();
            tiles[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss09.png")));
            tiles[16].collision = true;

            tiles[17] = new Tile();
            tiles[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss10.png")));
            tiles[17].collision = true;

            tiles[18] = new Tile();
            tiles[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss11.png")));
            tiles[18].collision = true;

            tiles[19] = new Tile();
            tiles[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss12.png")));
            tiles[19].collision = true;

            tiles[20] = new Tile();
            tiles[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_watergarss13.png")));
            tiles[20].collision = true;

            tiles[21] = new Tile();
            tiles[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_path0.png")));


            tiles[22] = new Tile();
            tiles[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_path1.png")));


            tiles[23] = new Tile();
            tiles[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_path2.png")));


            tiles[24] = new Tile();
            tiles[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_path3.png")));


            tiles[25] = new Tile();
            tiles[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_path4.png")));


            tiles[26] = new Tile();
            tiles[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_path5.png")));

            tiles[27] = new Tile();
            tiles[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/sprite_path90.png")));






        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(int[][] map) {

//        try{
//
//            InputStream is = getClass().getResourceAsStream(filePath);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int col = 0;
//            int row = 0;
//
//            while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow ){
//
//                String line = br.readLine();
//
//                while(col < gamePanel.maxWorldCol) {
//
//                    String[] numbers = line.split(" ");
//
//                    int number = Integer.parseInt(numbers[row]);
//                    mapTileNumber[col][row] = number;
//                    col++;
//                }
//                    if(col == gamePanel.maxWorldCol){
//                        col = 0;
//                        row ++;
//                    }
//
//
//            }
//            br.close();
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        for (int col = 0; col < gamePanel.maxWorldCol; col++) {
            for (int row = 0; row < gamePanel.maxWorldRow; row++) {
                mapTileNumber[col][row] = map[row][col];
            }
        }


    }

    public void draw(Graphics2D g2) {


        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);


            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;

                worldRow++;

            }
        }


    }
}
