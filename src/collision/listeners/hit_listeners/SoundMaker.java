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
 * A HitListener that plays a sound when a block is hit.
 *
 * Uses a MusicPlayer to trigger the appropriate sound based on the block that was hit.
 */
public class SoundMaker implements HitListener {

    private MusicPlayer musicPlayer;

    /**
     * Creates a new SoundMaker.
     *
     * @param levelSounds the music player responsible for playing block hit sounds
     */
    public SoundMaker(MusicPlayer levelSounds) {
        musicPlayer = levelSounds;
    }


    /**
     * Called when the specified block is hit.
     * Triggers the music player to play the sound associated with the block.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        musicPlayer.playMusic(beingHit);
    }
}
