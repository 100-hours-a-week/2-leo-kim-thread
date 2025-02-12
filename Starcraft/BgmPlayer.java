package Starcraft;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BgmPlayer {
    private static boolean isPlaying = true;

    public static void playBgm(String race){
        Thread bgmThread = new Thread(() -> {
            while(isPlaying) {
                String filePathRoot = "bgm/"+race.toLowerCase()+"/"+race+" - ";
                int bgmIdx = (int)(Math.random()*3)+1;
                String filePath = filePathRoot + bgmIdx + ".mp3";
                try {
                    InputStream fileStream = new FileInputStream(filePath);
                    AdvancedPlayer player = new AdvancedPlayer(fileStream);
                    player.play(); // MP3 재생
                    fileStream.close();
                } catch (JavaLayerException | java.io.IOException e) {
                    e.printStackTrace();
                    isPlaying = false;
                }
            }
        });

        bgmThread.setDaemon(true);
        bgmThread.start();
    }
}
