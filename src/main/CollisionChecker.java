package main;

import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public int checkObject(Entity e, boolean player){
        int index = 999;

        for(int i = 0; i< gp.obj.length; i++){

            if(gp.obj[i]!=null){
                e.solidArea.x = (int) e.worldX + e.solidArea.x;
                e.solidArea.y =(int) e.worldY + e.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (e.direction){
                    case "up":
                        e.solidArea.y -= (e.speed*gp.player.multiplier);
                        if(e.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true) e.collisionOn = true;
                            if(player == true) index = i;
                        }
                        break;
                    case "down":
                        e.solidArea.y += (e.speed*gp.player.multiplier);
                        if(e.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true) e.collisionOn = true;
                            if(player == true) index = i;
                        }
                        break;
                    case "right":
                        e.solidArea.x += (e.speed*gp.player.multiplier);
                        if(e.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true) e.collisionOn = true;
                            if(player == true) index = i;
                        }
                        break;
                    case "left":
                        e.solidArea.x -= (e.speed*gp.player.multiplier);
                        if(e.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true) e.collisionOn = true;
                            if(player == true) index = i;
                        }
                        break;
                }
                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }


    public void checkObjectM(){

         gp.monster.solidArea.x = (int) gp.monster.worldX + gp.monster.solidArea.x;
         gp.monster.solidArea.y =(int) gp.monster.worldY + gp.monster.solidArea.y;

         gp.bullet.solidArea.x =  gp.bullet.worldX + gp.bullet.solidArea.x;
         gp.bullet.solidArea.y = gp.bullet.worldY + gp.bullet.solidArea.y;

         gp.monster.solidArea.x += (gp.monster.speed);
         if(gp.monster.solidArea.intersects(gp.bullet.solidArea)){
             if(!gp.bullet.hit) gp.playMusicNoLoop2(21);
             Random r = new Random();
             int j = 12 + r.nextInt(7) ;
             if(!gp.bullet.hit)gp.playMusicNoLoop2(j);
             gp.monster.speed = 1;
             gp.bullet.hit = true;
         }

        gp.monster.solidArea.x = gp.monster.solidAreaDefaultX;
        gp.monster.solidArea.y = gp.monster.solidAreaDefaultY;
        gp.bullet.solidArea.x = gp.bullet.solidAreaDefaultX;
        gp.bullet.solidArea.y = gp.bullet.solidAreaDefaultY;

    }

    public void checkObjectC(){

        gp.leader.solidArea.x = (int) gp.leader.worldX + gp.leader.solidArea.x;
        gp.leader.solidArea.y =(int) gp.leader.worldY + gp.leader.solidArea.y;

        gp.player.solidArea.x = (int) (gp.player.worldX + gp.player.solidArea.x);
        gp.player.solidArea.y = (int) (gp.player.worldY + gp.player.solidArea.y);

        gp.leader.solidArea.x += (gp.leader.speed);
        if(gp.leader.solidArea.intersects(gp.player.solidArea)){
            if(!gp.leader.killedPlayer) {
                gp.leader.speed = 0;
                gp.playMusicNoLoop(2);
                gp.leader.killedPlayer = true;
                gp.player.state = gp.player.deadState;
                gp.gameState = gp.cultEnding;
            }
        }

        gp.leader.solidArea.x = gp.leader.solidAreaDefaultX;
        gp.leader.solidArea.y = gp.leader.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }



    public void checkObjectTE(){

        gp.truck.solidArea.x = gp.truck.worldX + gp.truck.solidArea.x;
        gp.truck.solidArea.y = gp.truck.worldY + gp.truck.solidArea.y;

        gp.monster.solidArea.x = (int) (gp.monster.worldX + gp.monster.solidArea.x);
        gp.monster.solidArea.y = (int) (gp.monster.worldY + gp.monster.solidArea.y);

        gp.truck.solidArea.x += (gp.truck.speed);
        if(gp.truck.solidArea.intersects(gp.monster.solidArea)){
            if(gp.monster.state != gp.monster.deadState){
                gp.playMusicNoLoop2(25);
                gp.playMusicNoLoop(2);
            }
            gp.monster.speed = 0;
            gp.monster.state = gp.monster.deadState;
        }

        gp.truck.solidArea.x = gp.truck.solidAreaDefaultX;
        gp.truck.solidArea.y = gp.truck.solidAreaDefaultY;
        gp.monster.solidArea.x = gp.monster.solidAreaDefaultX;
        gp.monster.solidArea.y = gp.monster.solidAreaDefaultY;

    }


    public void checkObjectT(){

        gp.player.solidArea.x = (int) (gp.player.worldX + gp.player.solidArea.x);
        gp.player.solidArea.y = (int) (gp.player.worldY + gp.player.solidArea.y);

        gp.truck.solidArea.x =  gp.truck.worldX + gp.truck.solidArea.x;
        gp.truck.solidArea.y = gp.truck.worldY + gp.truck.solidArea.y;

        switch (gp.player.direction){
            case "up":
                gp.player.solidArea.y -= (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.truck.solidArea)){
                    gp.player.state = gp.player.deadState;
                    if(!gp.monster.killedPlayer) {
                        gp.stopMusic();
                        gp.playMusicNoLoop(2);
                    }
                    gp.monster.killedPlayer = true;
                }
                break;
            case "down":
                gp.player.solidArea.y += (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.truck.solidArea)){
                    gp.player.state = gp.player.deadState;
                    if(!gp.monster.killedPlayer) {
                        gp.stopMusic();
                        gp.playMusicNoLoop(2);
                    }
                    gp.monster.killedPlayer = true;
                }
                break;
            case "right":
                gp.player.solidArea.x += (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.truck.solidArea)){
                    gp.player.state = gp.player.deadState;
                    if(!gp.monster.killedPlayer) {
                        gp.stopMusic();
                        gp.playMusicNoLoop(2);
                    }
                    gp.monster.killedPlayer = true;
                }
                break;
            case "left":
                gp.player.solidArea.x -= (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.truck.solidArea)){
                    gp.player.state = gp.player.deadState;
                    if(!gp.monster.killedPlayer) {
                        gp.stopMusic();
                        gp.playMusicNoLoop(2);
                    }
                    gp.monster.killedPlayer = true;
                }
                break;
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        gp.truck.solidArea.x = gp.truck.solidAreaDefaultX;
        gp.truck.solidArea.y = gp.truck.solidAreaDefaultY;
    }

    public void checkObjectDemonic(){

        gp.player.solidArea.x = (int) (gp.player.worldX + gp.player.solidArea.x);
        gp.player.solidArea.y = (int) (gp.player.worldY + gp.player.solidArea.y);

        gp.demonDeer.solidArea.x = (int) (gp.demonDeer.worldX + gp.demonDeer.solidArea.x);
        gp.demonDeer.solidArea.y = (int) (gp.demonDeer.worldY + gp.demonDeer.solidArea.y);

        switch (gp.player.direction){
            case "up":
                gp.player.solidArea.y -= (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.demonDeer.solidArea)){
                    if(!gp.demonDeer.killedPlayer) {
                        gp.stopMusic();
                        gp.playMusicNoLoop(2);
                    }
                    gp.demonDeer.killedPlayer(gp);
                }
                break;
            case "down":
                gp.player.solidArea.y += (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.demonDeer.solidArea)){
                    if(!gp.demonDeer.killedPlayer) {
                        gp.stopMusic();
                        gp.playMusicNoLoop(2);
                    }
                    gp.demonDeer.killedPlayer(gp);
                }
                break;
            case "right":
                gp.player.solidArea.x += (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.demonDeer.solidArea)){
                    if(!gp.demonDeer.killedPlayer) {
                        gp.stopMusic();
                        gp.playMusicNoLoop(2);
                    }
                    gp.demonDeer.killedPlayer(gp);
                }
                break;
            case "left":
                gp.player.solidArea.x -= (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.demonDeer.solidArea)){
                    if(!gp.demonDeer.killedPlayer) {
                        gp.stopMusic();
                        gp.playMusicNoLoop(2);
                    }
                    gp.demonDeer.killedPlayer(gp);
                }
                break;
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        gp.demonDeer.solidArea.x = gp.demonDeer.solidAreaDefaultX;
        gp.demonDeer.solidArea.y = gp.demonDeer.solidAreaDefaultY;
    }

    public void checkObjectP(){

        gp.player.solidArea.x = (int) (gp.player.worldX + gp.player.solidArea.x);
        gp.player.solidArea.y = (int) (gp.player.worldY + gp.player.solidArea.y);

        gp.photo.solidArea.x =  gp.photo.worldX + gp.photo.solidArea.x;
        gp.photo.solidArea.y = gp.photo.worldY + gp.photo.solidArea.y;

        switch (gp.player.direction){
            case "up":
                gp.player.solidArea.y -= (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.photo.solidArea)){
                    gp.photo.state = gp.photo.defState;
                    gp.picture = true;
                }
                break;
            case "down":
                gp.player.solidArea.y += (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.photo.solidArea)){
                    gp.photo.state = gp.photo.defState;
                    gp.picture = true;
                }
                break;
            case "right":
                gp.player.solidArea.x += (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.photo.solidArea)){
                    gp.photo.state = gp.photo.defState;
                    gp.picture = true;
                }
                break;
            case "left":
                gp.player.solidArea.x -= (gp.player.speed);
                if(gp.player.solidArea.intersects(gp.photo.solidArea)){
                    gp.photo.state = gp.photo.defState;
                    gp.picture = true;

                }
                break;
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        gp.photo.solidArea.x = gp.photo.solidAreaDefaultX;
        gp.photo.solidArea.y = gp.photo.solidAreaDefaultY;
    }

    public void checkObjectF(){

        gp.bullet.solidArea.x = gp.bullet.worldX + gp.bullet.solidArea.x;
        gp.bullet.solidArea.y = gp.bullet.worldY + gp.bullet.solidArea.y;

        gp.fox.solidArea.x =  gp.fox.worldX + gp.fox.solidArea.x;
        gp.fox.solidArea.y = gp.fox.worldY + gp.fox.solidArea.y;

        switch (gp.player.direction){
            case "up":
                gp.bullet.solidArea.y -= (gp.bullet.speed);
                if(gp.bullet.solidArea.intersects(gp.fox.solidArea)){
                    gp.fox.attacked = true;
                    if(!gp.bullet.hit) {
                        gp.playMusicNoLoop2(21);
                    }
                    gp.bullet.hit = true;
                }
                break;
            case "down":
                gp.bullet.solidArea.y += (gp.bullet.speed);
                if(gp.bullet.solidArea.intersects(gp.fox.solidArea)){
                    gp.fox.attacked = true;
                    if(!gp.bullet.hit) {
                        gp.playMusicNoLoop2(21);
                    }
                    gp.bullet.hit = true;
                }
                break;
            case "right":
                gp.bullet.solidArea.x += (gp.bullet.speed);
                if(gp.bullet.solidArea.intersects(gp.fox.solidArea)){
                    gp.fox.attacked = true;
                    if(!gp.bullet.hit) {
                        gp.playMusicNoLoop2(21);
                    }
                    gp.bullet.hit = true;
                }
                break;
            case "left":
                gp.bullet.solidArea.x -= (gp.bullet.speed);
                if(gp.bullet.solidArea.intersects(gp.fox.solidArea)){
                    gp.fox.attacked = true;
                    if(!gp.bullet.hit) {
                        gp.playMusicNoLoop2(21);
                    }
                    gp.bullet.hit = true;
                }
                break;
        }

        gp.bullet.solidArea.x = gp.bullet.solidAreaDefaultX;
        gp.bullet.solidArea.y = gp.bullet.solidAreaDefaultY;
        gp.fox.solidArea.x = gp.fox.solidAreaDefaultX;
        gp.fox.solidArea.y = gp.fox.solidAreaDefaultY;
    }

    public void checkObjectD(){

        gp.bullet.solidArea.x = gp.bullet.worldX + gp.bullet.solidArea.x;
        gp.bullet.solidArea.y = gp.bullet.worldY + gp.bullet.solidArea.y;

        gp.deer.solidArea.x = (int) (gp.deer.worldX + gp.deer.solidArea.x);
        gp.deer.solidArea.y = (int) (gp.deer.worldY + gp.deer.solidArea.y);

        switch (gp.player.direction){
            case "up":
                gp.bullet.solidArea.y -= (gp.bullet.speed);
                if(gp.bullet.solidArea.intersects(gp.deer.solidArea)){
                    if(!gp.bullet.hit) gp.playMusicNoLoop2(21);
                    gp.bullet.hit = true;
                    gp.deer.state = gp.deer.deadState;
                }
                break;
            case "down":
                gp.bullet.solidArea.y += (gp.bullet.speed);
                if(gp.bullet.solidArea.intersects(gp.deer.solidArea)){
                    if(!gp.bullet.hit) gp.playMusicNoLoop2(21);
                    gp.bullet.hit = true;
                    gp.deer.state = gp.deer.deadState;
                }
                break;
            case "right":
                gp.bullet.solidArea.x += (gp.bullet.speed);
                if(gp.bullet.solidArea.intersects(gp.deer.solidArea)){
                    if(!gp.bullet.hit) gp.playMusicNoLoop2(21);
                    gp.bullet.hit = true;
                    gp.deer.state = gp.deer.deadState;
                }
                break;
            case "left":
                gp.bullet.solidArea.x -= (gp.bullet.speed);
                if(gp.bullet.solidArea.intersects(gp.deer.solidArea)){
                    if(!gp.bullet.hit) gp.playMusicNoLoop2(21);
                    gp.bullet.hit = true;
                    gp.deer.state = gp.deer.deadState;
                }
                break;
        }

        gp.bullet.solidArea.x = gp.bullet.solidAreaDefaultX;
        gp.bullet.solidArea.y = gp.bullet.solidAreaDefaultY;
        gp.deer.solidArea.x = gp.deer.solidAreaDefaultX;
        gp.deer.solidArea.y = gp.deer.solidAreaDefaultY;
    }
}

//    public void checkTile(Entity entity){
//
//        // ENTITY.WORLD(x/y) = CURRENT POSITION, SOLIDAREA = COLLISION HITBOX OF PLAYER
//        double entityLeftWorldX =  (entity.worldX + entity.solidArea.x);
//        double entityRightWorldX =  (entity.worldX + entity.solidArea.x + entity.solidArea.width);
//        double entityTopWorldY =  (entity.worldY + entity.solidArea.y);
//        double entityBottomWorldY =  (entity.worldY + entity.solidArea.y + entity.solidArea.height);
//
//        double entityLeftCol = entityLeftWorldX/gp.tileSizeRes;
//        double entityRightCol = entityRightWorldX/gp.tileSizeRes;
//        double entityTopRow = entityTopWorldY/gp.tileSizeRes;
//        double entityBottomRow = entityBottomWorldY/gp.tileSizeRes;
//
//        int tileNum1, tileNum2;
//
//        if(entity.direction == "up") {
//            entityTopRow = ((entityTopWorldY - entity.speed ) / gp.tileSizeRes);
//            tileNum1 = gp.tileM.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
//            tileNum2 = gp.tileM.mapTileNum[(int) entityRightCol][(int) entityTopRow];
//            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
//                entity.collisionOn = true;
//            }
//        }
//        if(entity.direction == "down"){
//            entityBottomRow = ((entityBottomWorldY + entity.speed )/gp.tileSizeRes);
//            tileNum1 = gp.tileM.mapTileNum[(int)entityLeftCol][(int)entityBottomRow];
//            tileNum2 = gp.tileM.mapTileNum[(int)entityRightCol][(int)entityBottomRow];
//            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
//                entity.collisionOn = true;
//            }
//        }
//        if(entity.direction == "left"){
//            entityLeftCol =  ((entityLeftWorldX - entity.speed )/gp.tileSizeRes);
//            tileNum1 = gp.tileM.mapTileNum[(int)entityLeftCol][(int)entityTopRow];
//            tileNum2 = gp.tileM.mapTileNum[(int)entityLeftCol][(int)entityBottomRow];
//            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
//                entity.collisionOn = true;
//            }
//        }
//        if(entity.direction == "right"){
//            entityRightCol = ((entityRightWorldX + entity.speed )/gp.tileSizeRes);
//            tileNum1 = gp.tileM.mapTileNum[(int)entityRightCol][(int)entityTopRow];
//            tileNum2 = gp.tileM.mapTileNum[(int)entityRightCol][(int)entityBottomRow];
//            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
//                entity.collisionOn = true;
//            }
//        }
//    }
