package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile  = new Tile[7];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map01.txt");
//        loadMap("/maps/map01.txt", 1);

    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rock.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/box.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String s){
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col< gp.maxWorldCol && row< gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2)
        {
             int worldCol = 0;
             int worldRow = 0;



             while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

                 int tileNum = mapTileNum[worldCol][worldRow];

                 int worldX = worldCol * gp.tileSizeRes;
                 int worldY = worldRow * gp.tileSizeRes;
                 int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
                 int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

                 if ( worldX + gp.tileSizeRes > gp.player.worldX - gp.player.screenX &&
                         worldX - gp.tileSizeRes < gp.player.worldX + gp.player.screenX &&
                         worldY + gp.tileSizeRes > gp.player.worldY - gp.player.screenY &&
                         worldY - gp.tileSizeRes < gp.player.worldY + gp.player.screenY){
                     g2.drawImage(tile[tileNum].image,screenX, screenY, gp.tileSizeRes, gp.tileSizeRes, null);
                 }

                 worldCol++;
                 if(worldCol == gp.maxWorldCol){
                     worldCol = 0;
                     worldRow++;
                 }
             }
        }
}
