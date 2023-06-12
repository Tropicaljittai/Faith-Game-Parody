package main;

import entity.*;
import monster.m_Capybara;
import object.*;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    //WORLD SETTINGS
    long pause;
    final int originalTileSize = 7;
    final int originalTileHeight = 19;
    final int scale = 3;
    public final int originaltileSizeRes = 16;
    public final int tileSizeMonster = 17;
    public final int tileHeightMonster = 14;
    public final int tileSizeShed = 33;
    public final int tileHeightShed = 38;
    public final int tileSizeWater = 39;
    public final int tileHeightWater = 14;
    public final int deerWidth = 25*scale;
    public final int deerHeight = 23*scale;
    public final int deerRunWidth = 22*scale;
    public final int deerRunHeight = 21*scale;
    public final int ghostHeight = 21;
    public final int ghostWidth = 8;
    public final int stonesHeight = 47;
    public final int stonesWidth = 100;
    public final int tileSizeRes = originaltileSizeRes*scale;
    public final int tileSize = originalTileSize*scale;
    public final int tileHeight = originalTileHeight*scale;
    public final int tileSizeTree = 24*scale;
    public final int tileHeightTree = 29*scale;
    public final int tileSizeKey = 5*scale;
    public final int tileHeightKey = 7*scale;
    public final int carWidth = 56*scale;
    public final int carHeight = 19*scale;
    public final int tileSizeWell = 23*scale;
    public final int tileHeightWell= 18*scale;
    public final int tileSizeBones = 48*scale;
    public final int tileHeightBones = 37*scale;
    public final int tileSizeSac = 92*scale;
    public final int tileHeightSac = 56*scale;
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 13;
    public final int screenWidth = 550; //512 550
    public final int screenHeight = 490; //448 490

    // MAP PARAMETERS
    public final int maxWorldCol = 84;
    public final int maxWorldRow = 93;
    public final int worldWidth = tileSizeRes * maxWorldCol;
    public final int worldHeight = tileSizeRes * maxWorldRow;

    // FPS
    int FPS =  60;

    // SYSTEM
    public boolean ending;
    public boolean picture = false;
    int alreadyTried = 0;
    boolean objectCleared = false;
    public int lastObject;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    Sound2 sound2 = new Sound2();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSet = new AssetSetter(this);
    Thread gameThread;
    UI ui = new UI(this);

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[750];
    public Cultist cultist[] = new Cultist[45];
    public Cultist leader = new Cultist(this);
    public m_Capybara monster = new m_Capybara();
    public Ghost ghost = new Ghost(this);
    public Deer deer = new Deer(this);
    public Deer deer2 = new Deer(this, 1);
    public obj_Bullet bullet = new obj_Bullet();
    public Hunter hunter = new Hunter(this);
    public obj_Fox fox = new obj_Fox(this);
    public obj_Truck truck = new obj_Truck(this);
    public obj_Photo photo = new obj_Photo(this);
    public DemonicDeer demonDeer = new DemonicDeer(this);

    // GAME STATES
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int introState = 3;
    public final int photoState = 4;
    public final int deadState = 5;
    public final int deerEnding = 6;
    public final int goodEnd = 7;
    public final int cultEnding = 8;


    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void findCenter(Ghost g, int i){
        if(obj[i].name == "Well") {
            g.worldX = obj[i].worldX + 24;
            g.worldY = obj[i].worldY - tileSizeRes;
        }
        if(obj[i].name == "Stones") {
            g.worldX = obj[i].worldX + 96;
            g.worldY = obj[i].worldY;
        }
        if(obj[i].name == "Water") {
            g.worldX = obj[i].worldX + 48;
            g.worldY = obj[i].worldY - 48;
        }
        if(obj[i].name == "Bones") {
            g.worldX = obj[i].worldX + 50;
            g.worldY = obj[i].worldY - 18;
        }
        ghost.positionSet = true;
    }
    public void monsterRandomizer(){
        if(monster.state == monster.sleepState){
            Random r = new Random();
            int i = r.nextInt(2) + 1;

            int a;
            int b;

            if(i == 1){
                a = (int) player.worldX + 300;
                b = (int) player.worldX - 300;
                monster.worldX = r.nextBoolean() ? a : b;
                monster.worldY = r.nextInt((int) (((player.worldY+269) - (player.worldY-269)) + 1)) + (player.worldY-269);
            }
            else {
                a = (int) player.worldY + 269;
                b = (int) player.worldY - 269;
                monster.worldY = r.nextBoolean() ? a : b;
                monster.worldX = r.nextInt((int) (((player.worldX+300) - (player.worldX-300)) + 1)) + (player.worldX-300);
            }
            monster.positionSet = true;
            monster.state = monster.defState;
            int j = r.nextInt(10) + 1;
            stopMusic();
            playMusicNoLoop2(j);
        }
    }

    public void photoGenerator(){
        photo.worldX = obj[lastObject].worldX + 144;
        photo.worldY = obj[lastObject].worldY;
        photo.state = photo.foundState;
    }
    public void keyGenerator(){
        obj[0] = new obj_Key();
        obj[0].worldX = obj[lastObject].worldX - 24;
        obj[0].worldY = obj[lastObject].worldY + 24;
        objectCleared = true;
    }
    public void setupGame() throws IOException {
        aSet.setCultists();
        aSet.setCar();
        aSet.setGun();
//        aSet.setKey();
        aSet.setTree();
        aSet.setWell();
        aSet.setStones();
        aSet.setWater();
        aSet.setBones();
        aSet.setSac();

        gameState = introState;
        playMusic(0);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override

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
            timer += (currentTime-lastTime);
            lastTime = currentTime;
            Random rn = new Random();
            int answer = rn.nextInt(10) + 1;
            if(gameState == playState && player.state != player.deadState && player.state != player.deadState2 && !player.hasGun && !picture){
                if(timer/1000000000 >= 10 ){
                    if((timer/1000000000) % 10 == 1 && alreadyTried == 0){
                        if(answer <= 2){
                            monsterRandomizer();
                        }
                        alreadyTried=1;
                        System.out.println(answer);
                    }
                }
                if((timer/1000000000) % 10 != 1 && alreadyTried == 1){
                    alreadyTried = 0;
                }
            }

            if(delta>=1){
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
                drawCount++;
            }

//            if(timer>=1000000000){
//                System.out.println("FPS:"+ drawCount);
//                drawCount = 0;
//                timer = 0;
//            }


        }
    }
    public void resetAll(){
        monster.state = monster.sleepState;
        monster.repel = 0;
        monster.speed = 3;
        monster.killedPlayer = false;

        player.state = player.defaultState;
        player.worldX = 16*tileSizeRes;
        player.worldY = 89*tileSizeRes;
        player.banishedGhosts = 0;
        player.hasKey = 0;
        player.hasGun = false;
        player.played = false;
        player.direction = "up";

        aSet.setGun();
        aSet.setCar();
        aSet.setWell();
        aSet.setStones();
        aSet.setWater();
        aSet.setBones();
        aSet.setSac();

        hunter.worldX = 56*tileSizeRes; //57
        hunter.worldY = 22*tileSizeRes; //22
        hunter.state = hunter.defState;
        hunter.found = false;

        deer.worldX = 37*tileSizeRes; //37
        deer.worldY = 53*tileSizeRes; //53
        deer.speed = 2.5F;
        deer.state = deer.defState;
        deer.attacked = 0;

        deer2.worldX = 59*tileSizeRes;
        deer2.worldY = 22*tileSizeRes;
        deer2.speed = 3.5F;
        deer2.state = deer2.hunterState2;
        deer2.attacked = 0;

        picture = false;

        ghost.state = ghost.defState;
        ghost.positionSet = false;

        bullet.hit = false;
        bullet.launched = false;
        bullet.positionFound = false;
        bullet.directionFound = false;

        objectCleared = false;

        demonDeer.positionFound = false;
        demonDeer.state = demonDeer.sleepState;
        demonDeer.killedPlayer = false;

        fox.attacked = false;

        ending = false;
    }
    public void update() throws IOException, InterruptedException {

        if(gameState == playState){
            leader.update();
            demonDeer.update();
            truck.update();
            bullet.update(this);
            deer.update();
            hunter.update();
            deer2.update();
            player.update();
            if(monster.state == monster.defState || monster.state == monster.attState || player.hasGun || monster.state == monster.deadState || monster.state == monster.endState) {
//                if(!monster.positionSet && monster.spawned) monsterRandomizer();
                if(!monster.positionSet && !player.hasGun) monsterRandomizer();

                monster.update(this);
            }
            if(ghost.state == ghost.wellState) {
                if(!ghost.positionSet) findCenter(ghost, 4);
                ghost.update();
            }
            if(ghost.state == ghost.stonesState) {
                if(!ghost.positionSet) findCenter(ghost, 2);
                ghost.update();
            }
            if(ghost.state == ghost.waterState) {
                if(!ghost.positionSet) findCenter(ghost, 3);
                ghost.update();
            }
            if(ghost.state == ghost.bonesState) {
                if(!ghost.positionSet) findCenter(ghost, 5);
                ghost.update();
            }
            if(player.state == player.deadState && !ending){
                if(Math.abs(truck.worldX-player.worldX) >= 500) player.state = player.deadState2;
            }
        }
        if(gameState == pauseState){
            stopMusic();
            // WIP
        }
        if(gameState == introState){

            // WIP
        }




    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        if(gameState == introState || gameState == playState || gameState == pauseState || gameState == deadState || gameState == deerEnding || gameState == goodEnd || gameState == cultEnding){

            tileM.draw(g2);
            try {
                fox.drawFox(g2, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            obj[2].drawStones(g2, this);
            obj[3].drawWater(g2, this);
            obj[5].drawBones(g2, this);
            try {
                obj[6].drawSac(g2, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(player.banishedGhosts < 5 && photo.state == photo.foundState) photo.drawPhoto(g2, this);
            int i = 2;
            while(obj[i]!= null){
                if(obj[i].worldY<player.worldY && obj[i].name == "Tree") obj[i].drawTree(g2, this);
                if(obj[i].worldY<player.worldY && obj[i].name == "Well") obj[i].drawWell(g2, this);
                if(obj[i].worldY<player.worldY && obj[i].name == "Car") obj[i].drawCar(g2, this);
                i++;
            }


            if(deer.state == deer.defState || deer.state == deer.attackedState || deer.state == deer.deadState ) deer.draw(g2, this);
            if(deer2.state == deer2.defState || deer2.state == deer2.attackedState || deer2.state == deer2.hunterState || deer2.state == deer2.hunterState2) deer2.draw(g2, this);
            if(hunter.state == hunter.defState || hunter.state == hunter.hunterState || hunter.found) hunter.draw(g2, this);

            if(bullet.launched && !bullet.hit) bullet.drawBullet(g2, this);
            if(player.hasGun && player.direction =="up" && !bullet.launched) obj[1].drawGun(g2, this);
            player.draw(g2);
            if((player.direction != "up" && !bullet.launched) || hunter.found) obj[1].drawGun(g2, this);
            if(demonDeer.state == demonDeer.defState) demonDeer.draw(g2, this);
            if(fox.attacked) leader.draw(g2, this);

            i = 2;
            while(obj[i]!= null){
                if(obj[i].worldY>=player.worldY && obj[i].name == "Tree")obj[i].drawTree(g2, this);
                if(obj[i].worldY>=player.worldY && obj[i].name == "Well")obj[i].drawWell(g2, this);
                if(obj[i].worldY>=player.worldY && obj[i].name == "Car") obj[i].drawCar(g2, this);
                i++;
            }
            if(monster.state == monster.defState || monster.state == monster.attState || monster.state == monster.endState || monster.state == monster.deadState) monster.draw(g2, this);
            truck.drawTruck(g2, this);
            int j = 0;
            while (cultist[j]!=null){
                if(fox.attacked) cultist[j].draw(g2, this);
                j++;
            }

            if(ghost.state == ghost.wellState|| ghost.state == ghost.stonesState || ghost.state == ghost.waterState || ghost.state == ghost.bonesState) ghost.draw(g2, this);

            if(picture){
                BufferedImage image = null;
                try {
                    if(player.banishedGhosts == 1) image = ImageIO.read(getClass().getResourceAsStream("/objects/180.png"));
                    if(player.banishedGhosts == 2) image = ImageIO.read(getClass().getResourceAsStream("/objects/190.png"));
                    if(player.banishedGhosts == 3) image = ImageIO.read(getClass().getResourceAsStream("/objects/195.png"));
                    if(player.banishedGhosts == 4) image = ImageIO.read(getClass().getResourceAsStream("/objects/nateDraw.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int x = screenWidth/2 - 2*tileSizeRes;
                int y = screenHeight/2 - 2*tileSizeRes;
                if(player.banishedGhosts == 4) g2.drawImage(image, x-48, y-100, 558/2, 676/2, null);
                else g2.drawImage(image, x, y, 180, 202, null);
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20f));
                g2.setColor(Color.WHITE);
                String text = "PRESS SPACE/ESC TO EXIT";
                g2.drawString(text, player.screenX-116, player.screenY+4*tileSizeRes);
            }
            try {
                ui.draw(g2);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
                g2.dispose();
        }
    }



    public void playMusic( int i ){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void playMusicNoLoop( int i ){
        sound.setFile(i);
        sound.play();
    }
    public void playMusicNoLoop2( int i ){
        sound2.setFile(i);
        sound2.play();
    }

    public void playMusic(){
        sound.clip.setMicrosecondPosition(pause);
        sound.clip.start();
        sound.loop();
    }

    public void stopMusic(){
        pause = sound.clip.getMicrosecondPosition();
        sound.stop();
    }


    public void playSE( int i){
        sound.setFile(i);
        sound.play();
    }

}

// SLEEP METHOD GAME RUNNING
/*    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            update();
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    } */
