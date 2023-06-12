package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound2 {

    Clip clip;
    URL soundURL[] = new URL[31];

    public Sound2(){

        soundURL[1] = getClass().getResource("/sound/1.wav");
        soundURL[2] = getClass().getResource("/sound/2.wav");
        soundURL[3] = getClass().getResource("/sound/3.wav");
        soundURL[4] = getClass().getResource("/sound/4.wav");
        soundURL[5] = getClass().getResource("/sound/5.wav");
        soundURL[6] = getClass().getResource("/sound/6.wav");
        soundURL[7] = getClass().getResource("/sound/7.wav");
        soundURL[8] = getClass().getResource("/sound/8.wav");
        soundURL[9] = getClass().getResource("/sound/9.wav");
        soundURL[10] = getClass().getResource("/sound/11.wav");
        soundURL[11] = getClass().getResource("/sound/12.wav");
        soundURL[12] = getClass().getResource("/sound/r1.wav");
        soundURL[13] = getClass().getResource("/sound/r2.wav");
        soundURL[14] = getClass().getResource("/sound/r3.wav");
        soundURL[15] = getClass().getResource("/sound/r4.wav");
        soundURL[16] = getClass().getResource("/sound/r5.wav");
        soundURL[17] = getClass().getResource("/sound/r6.wav");
        soundURL[18] = getClass().getResource("/sound/r7.wav");
        soundURL[19] = getClass().getResource("/sound/r8.wav");
        soundURL[20] = getClass().getResource("/sound/item.wav");
        soundURL[21] = getClass().getResource("/sound/Bullet hit.wav");
        soundURL[22] = getClass().getResource("/sound/mortis.wav");
        soundURL[23] = getClass().getResource("/sound/gun.wav");
        soundURL[24] = getClass().getResource("/sound/Demon deer.wav");
        soundURL[25] = getClass().getResource("/sound/Truck.wav");
        soundURL[26] = getClass().getResource("/sound/boom.wav");
        soundURL[27] = getClass().getResource("/sound/september.wav");
        soundURL[28] = getClass().getResource("/sound/sam.wav");
        soundURL[29] = getClass().getResource("/sound/ihave.wav");
        soundURL[30] = getClass().getResource("/sound/what.wav");
    }

    public void setFile( int i ){
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){
        }
    }
    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
