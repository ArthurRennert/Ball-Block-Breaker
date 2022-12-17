package collision.listeners.hit_listeners;

import collision.listeners.hit_listeners.infrastructure.HitListener;
import music.MusicPlayer;
import music.Sound;
import sprites.Ball;
import sprites.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;


/**
 *
 */
public class SoundMaker implements HitListener {

    private MusicPlayer musicPlayer;


    /**
     * @param levelSounds
     */
    public SoundMaker(MusicPlayer levelSounds) {
        musicPlayer = levelSounds;
    }


    /**
     * @param beingHit
     * @param hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        musicPlayer.playMusic(beingHit);
    }
}
