package programs;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class MusicPlayer {

    public static void playMusic(URL filepath) {
        try {
//         File musicPath = new File(filepath);
//            AudioInputStream audioInput = AudioSystem.getAudioInputStream(filepath);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInput);
//            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
