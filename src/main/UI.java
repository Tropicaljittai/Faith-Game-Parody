package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String dialogues[] = new String[20];
    public String currentDialogue = "";
    int i = 0;


    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) throws IOException, InterruptedException {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){
            // WIP
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.introState){
            drawIntroScreen();
        }
        if(gp.gameState == gp.deadState){
            drawDeadScreen();
        }
        if(gp.gameState == gp.deerEnding){
            drawEnding();
        }
        if(gp.gameState == gp.goodEnd){
            drawEnding2();
        }
        if(gp.gameState == gp.cultEnding){
            drawEnding3();
        }
    }

    public void drawEnding3() throws IOException, InterruptedException {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 10));
        String text = "PRESS SPACE/ESC TO RETRY";
        int x = getXCenter(text);
        int y = gp.screenHeight/2 + 4*gp.tileSizeRes;
        BufferedImage image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/endings/ending2.png"));
        g2.drawImage(image2, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(image, (gp.screenWidth/2) - 120, (gp.screenHeight/2)-72, 505/2, 243/2, null);
        g2.drawString(text, x, y);
    }
    public void drawEnding2() throws IOException, InterruptedException {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 10));
        String text = "PRESS SPACE/ESC TO RETRY";
        int x = getXCenter(text);
        int y = gp.screenHeight/2 + 4*gp.tileSizeRes;
        BufferedImage image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/endings/goodEnd.png"));
        g2.drawImage(image2, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(image, (gp.screenWidth/2) - 120, (gp.screenHeight/2)-72, 505/2, 243/2, null);
        g2.drawString(text, x, y);
    }
    public void drawEnding() throws IOException, InterruptedException {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 10));
        String text = "PRESS SPACE/ESC TO RETRY";
        int x = getXCenter(text);
        int y = gp.screenHeight/2 + 4*gp.tileSizeRes;
        BufferedImage image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/endings/ending1.png"));
        g2.drawImage(image2, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(image, (gp.screenWidth/2) - 120, (gp.screenHeight/2)-72, 505/2, 243/2, null);
        g2.drawString(text, x, y);
    }


    public void drawDeadScreen() throws IOException, InterruptedException {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 10));
        String text = "PRESS SPACE/ESC TO RETRY";
        int x = getXCenter(text);
        int y = gp.screenHeight/2 + 4*gp.tileSizeRes;
        BufferedImage image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/mortis.png"));
        g2.drawImage(image2, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(image, (gp.screenWidth/2) - 48, gp.screenHeight/2 - 12, 336/3, 54/3, null);
        g2.drawString(text, x, y);
    }

    public void drawPauseScreen() throws IOException {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40f));
        String text = "PAUSED";
        int x = getXCenter(text);
        int y = gp.screenHeight/2;
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/transparantBlack.png"));
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawString(text, x, y);
    }
    public void setDialogues() {
        dialogues[0] = "SEPTEMBER 21, 1987";
        dialogues[1] = "It's been one year since I first met Michael.";
        dialogues[2] = "I have to finish what I started.";
        dialogues[3] = "...what I am about to do has not been approved by the Vatican.";
        dialogues[4] = "test";
    }
    public void drawIntroScreen() throws IOException, InterruptedException {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 13f));
        setDialogues();
        currentDialogue = dialogues[i];
        int x = getXCenter(currentDialogue);
        int y = gp.screenHeight/2;
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawString(currentDialogue, x, y);
        dialogueWait();
    }

    public void dialogueWait() throws InterruptedException {
        if(i == 0) TimeUnit.SECONDS.sleep(3);
        if(i == 1) TimeUnit.SECONDS.sleep(6);
        if(i == 2) TimeUnit.SECONDS.sleep(4);
        if(i == 3) TimeUnit.SECONDS.sleep(4);
        if(i == 4) TimeUnit.SECONDS.sleep(6);
        gp.playMusicNoLoop2(27+i);
        i+=1;
        if(dialogues[i] == null) {
            gp.sound.clip.stop();
            gp.playMusic(1);
            gp.gameState = gp.playState;
        }

    }


    public int getXCenter(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
