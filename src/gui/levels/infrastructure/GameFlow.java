package gui.levels.infrastructure;

import biuoop.KeyboardSensor;
import collision.listeners.hit_listeners.SoundMaker;
import gui.animations.*;
import gui.animations.infrastructure.AnimationRunner;
import utilities.Counter;

import java.util.List;

public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;

    /**
     * @param ar
     * @param ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        keyboardSensor = ks;
        score = new Counter();
        lives = new Counter();
        score.setValue(0);
        lives.setValue(5);
    }

    /**
     * @param levels
     */
    public void runLevels(List<LevelInformation> levels) {

        for (int i = 0; i < levels.size(); i++) {

            LevelInformation levelInfo = levels.get(i);

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, lives);

            level.initialize();
            if (i == 0) {
                this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new Instructions(keyboardSensor, level.getSpritesListForInstructionAnimation())));
            }
            this.animationRunner.run(new Countdown(3, level.getSprites(), animationRunner, keyboardSensor));

//            while (level.getNumOfBallsLeft() > 0 && level.getNumOfBlocksLeft() > 0) {
//                level.run();
//            }

            while (lives.getValue() > 0 && level.getNumOfBlocksLeft() > 0) {
                level.run();
            }

//            if (level.getNumOfBallsLeft() == 0) {
//                this.animationRunner.run(new GameOver(keyboardSensor, level.getSprites(), score.getValue()));
//                System.exit(0);
//                break;
//            }

            if (lives.getValue() == 0) {  //level.getNumOfBallsLeft() == 0
                this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new GameOver(keyboardSensor, level.getSprites(), score.getValue())));
                System.exit(0);
                break;
            }

            if (i == (levels.size() - 1) && level.getNumOfBlocksLeft() == 0) {
                this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new BetweenLevels(keyboardSensor, level.getTimer().getMinute(), level.getTimer().getSecond(), levelInfo.levelName(), level.getSprites(), true)));
                this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new GameWon(keyboardSensor, level.getSprites(), score.getValue())));
                System.exit(0);
            }
            this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new BetweenLevels(keyboardSensor, level.getTimer().getMinute(), level.getTimer().getSecond(), levelInfo.levelName(), level.getSprites(), false)));
        }
    }

    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }
}