package gui.levels;

import biuoop.KeyboardSensor;
import gui.*;
import utilities.Counter;

import java.util.List;

public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        keyboardSensor = ks;
        score = new Counter();
        score.setValue(0);
    }

    public void runLevels(List<LevelInformation> levels) {

        for (int i = 0; i < levels.size(); i++) {

            LevelInformation levelInfo = levels.get(i);

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score);

            level.initialize();

            while (level.getNumOfBallsLeft() > 0 && level.getNumOfBlocksLeft() > 0) {
                level.run();
            }

            if (level.getNumOfBallsLeft() == 0) {
                this.animationRunner.run(new GameOver(keyboardSensor, level.getSprites(), score.getValue()));
                System.exit(0);
                break;
            }

            if (i == (levels.size() - 1) && level.getNumOfBlocksLeft() == 0) {
                this.animationRunner.run(new GameWon(keyboardSensor, level.getSprites(), score.getValue()));
                System.exit(0);
            }
            this.animationRunner.run(new BetweenLevelsScreen(keyboardSensor, levelInfo.getName(), level.getSprites()));
        }
    }

    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }
}