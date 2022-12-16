package collision.listeners.hit_listeners;

import collision.listeners.hit_listeners.infrastructure.HitListener;
import programs.Sound;
import sprites.Ball;
import sprites.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;


public class SoundMaker implements HitListener {

    private Sound paddleHit;
    private Sound frameBlockHit;
    private Sound silence;
//    private Clip c;

    public SoundMaker() {
        paddleHit = new Sound("/Paddle-Hit.wav");
//        load(paddleHit.getSound());
        frameBlockHit = new Sound("/Bloop.wav");
        silence = new Sound("/Silence.wav");
        playMusic(silence.getSound());
//        load(frameBlockHit.getSound());
//        c = loadClip(paddleHit.getSound());
    }


    /**
     * @param beingHit
     * @param hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getName().equals("GameBlock")) {
//            playMusic(new Sound("/Paddle-Hit.wav").getSound());
            playMusic(paddleHit.getSound());
//            playClip(c);
        } else if (beingHit.getName().equals("FrameBlock")) {
//            playMusic(new Sound("/Bloop.wav").getSound());
            playMusic(frameBlockHit.getSound());
        }
    }

    public static void load(URL filepath) {
        try {
//         File musicPath = new File(filepath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(filepath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(URL filepath) {
        try {
//         File musicPath = new File(filepath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(filepath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Clip loadClip(URL filepath) {
        Clip clip = null;
        try {
//         File musicPath = new File(filepath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(filepath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
//            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

//    public void playClip(Clip clip) {
//        if(clip.isRunning()) {
//            clip.stop();
//        }
//        clip.setFramePosition( 0 );
//        clip.start();
//    }
}
