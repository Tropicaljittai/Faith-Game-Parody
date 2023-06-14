package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    int attackState;
    public boolean upPressed, leftPressed, downPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(gp.gameState == gp.playState){
            if(gp.picture){
                if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE){
                    gp.picture = false;
                    gp.photo.worldX = -1000;
                    gp.photo.worldY = -1000;
                }
            }
            else {
                if(code == KeyEvent.VK_W){
                    upPressed = true;
                }
                if(code == KeyEvent.VK_A){
                    leftPressed = true;
                }
                if(code == KeyEvent.VK_S){
                    downPressed = true;
                }
                if(code == KeyEvent.VK_D){
                    rightPressed = true;
                }
                if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_P){
                    gp.gameState = gp.pauseState;
                }
                if(code == KeyEvent.VK_F){
                    if(gp.player.state == gp.player.defaultState){

                        if(gp.player.hasGun && !gp.bullet.launched){
                            gp.bullet.launched = true;
                            if (!gp.bullet.directionFound) {
                                gp.playMusicNoLoop2(23);
                                gp.bullet.direction = gp.player.direction;
                                gp.bullet.directionFound = true;
                            }
                        }
                        else gp.player.state = gp.player.attackState;

                    }
                    else if (gp.player.state == gp.player.attackState) {
                        gp.player.state = gp.player.defaultState;
                    }
                }
            }
        }
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
                gp.playMusic();
            }
        }
        else if(gp.gameState == gp.photoState){
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.deerEnding || gp.gameState == gp.goodEnd || gp.gameState == gp.deadState || gp.gameState == gp.cultEnding){
            if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE){
                gp.resetAll();
                gp.playMusic(1);
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
