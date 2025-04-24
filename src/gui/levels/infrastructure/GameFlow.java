package gui.levels.infrastructure;

import biuoop.KeyboardSensor;
import collision.listeners.hit_listeners.SoundMaker;
import gui.animations.*;
import gui.animations.infrastructure.AnimationRunner;
import music.MusicPlayer;
import music.Sound;
import utilities.Counter;

import java.util.List;

/**
 * The GameFlow class manages the overall flow of the game.
 *
 * It initializes and runs levels one by one, handles transitions between levels,
 * manages player score and lives, and determines when the game ends or is won.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;
    private MusicPlayer musicPlayer;

    /**
     * Creates a new GameFlow manager.
     *
     * @param ar the animation runner used to run animations
     * @param ks the keyboard sensor for user input
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        keyboardSensor = ks;
        score = new Counter();
        lives = new Counter();
        score.setValue(0);
        lives.setValue(5);
        musicPlayer = new MusicPlayer();
    }

    /**
     * Runs the list of levels in sequence.
     *
     * Initializes and plays each level. Handles the instruction screen, countdown,
     * background music, transitions between levels, and end screens for win or loss.
     *
     * @param levels a list of LevelInformation objects representing the game's levels
     */
    public void runLevels(List<LevelInformation> levels) {

        for (int i = 0; i < levels.size(); i++) {

            LevelInformation levelInfo = levels.get(i);

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, lives, musicPlayer);

            level.initialize();
            if (i == 0) {
                this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new Instructions(keyboardSensor, level.getSpritesListForInstructionAnimation())));
            }
            level.playBackgroundMusic();
            this.animationRunner.run(new Countdown(3, level.getSprites(), animationRunner, keyboardSensor, musicPlayer));

            while (lives.getValue() > 0 && level.getNumOfBlocksLeft() > 0) {
                level.run();
            }

            if (lives.getValue() == 0) {  //level.getNumOfBallsLeft() == 0
                level.stopBackgroundMusic();
                musicPlayer.playMusic(new Sound("/Fail-Sounds.wav").getSound());
                this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new GameOver(keyboardSensor, level.getSprites(), score.getValue())));
                System.exit(0);
                break;
            }

            if (i == (levels.size() - 1) && level.getNumOfBlocksLeft() == 0) {
                level.stopBackgroundMusic();
                this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new BetweenLevels(keyboardSensor, level.getTimer().getMinute(), level.getTimer().getSecond(), levelInfo.levelName(), level.getSprites(), true)));
                level.stopBackgroundMusic();
                musicPlayer.playMusic(new Sound("/Win-Sound.wav").getSound());
                this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new GameWon(keyboardSensor, level.getSprites(), score.getValue())));
                System.exit(0);
            }
            level.stopBackgroundMusic();
            this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new BetweenLevels(keyboardSensor, level.getTimer().getMinute(), level.getTimer().getSecond(), levelInfo.levelName(), level.getSprites(), false)));
        }
    }

    /**
     * Returns the animation runner used in this game flow.
     *
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    /**
     * Returns the keyboard sensor used in this game flow.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }
}