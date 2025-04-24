# Ball Block Breaker

**Ball Block Breaker** is a Java-based arcade-style brick-breaking game, inspired by classics like *Arkanoid*. The player controls a paddle to bounce a ball and break all blocks on the screen without losing the ball. This project demonstrates object-oriented programming (OOP) principles, animation handling, collision detection, sound effects, and game flow management.

Each level in the game is designed with a unique theme and nostalgic inspiration:

- Level 1: "Friends" – based on the iconic Friends TV show, featuring a cheerful background and matching color palette.

- Level 2: "Terminator" – inspired by the legendary Terminator movies, with a darker, more intense design and sound effects.

These references add a fun and familiar twist to the gameplay while showcasing dynamic background animations and personalized audio effects.

The game includes:

- Multiple levels with themed designs and sounds

- Paddle control with adjustable speed

- Score tracking and limited lives

- Countdown, pause, win and game over screens

- Background music and sound effects for each level

## 🎮 Project Overview

The goal of the game is to break all blocks in each level using a bouncing ball. The player controls a paddle at the bottom of the screen using keyboard keys, trying to keep the ball in play. The game features:

- Multiple levels with different designs, backgrounds, and block arrangements.
- Player controls for movement and speed adjustment.
- A scoring system and life count.
- Countdown screen, pause/resume, win and game over animations.
- Background music and sound effects for a richer experience.

Additional demo programs (in the `programs` package) showcase ball bouncing, artistic animations, and simple GUI usage.

## 🧰 Technologies Used

- **Language:** Java
- **Graphics Library:** [biuoop](https://www.cs.biu.ac.il/~baraks/oop/biuoop.jar) – a lightweight GUI and animation library used in academic courses
- **Sound:** `javax.sound.sampled` for background music and collision sounds
- **Paradigms:** Full Object-Oriented Design with interfaces, listeners, and modular structure

## 🚀 How to Run

### Requirements

- Java JDK 8 or higher
- `biuoop.jar` file (download from course or project resources)

### Option 1: Run with an IDE (e.g., IntelliJ, Eclipse)

1. Open the `src/` folder as a Java project.
2. Add `biuoop.jar` as an external library.
3. Compile the project.
4. Run the `main()` method in `main.BallBlockBreaker`.

### Option 2: Run from the Terminal

Compile all Java files
```bash
javac -classpath biuoop.jar -d out src/**/*.java
```
Run the main class
```bash
java -classpath biuoop.jar:out main.BallBlockBreaker
```

### Option 3: Run the JAR file

1. Download the `BallBlockBreaker.jar` file from here: https://drive.google.com/file/d/1tgSLcptNafGhFDfttMK9HIEtu2JlLwsX/view?usp=drive_link
2. Run from terminal:
```bash
java -jar BallBlockBreaker.jar
```


### Controls
- ← / → or A / D: Move paddle left/right

- W / ↑: Increase paddle speed

- S / ↓: Decrease paddle speed


### 📁 Project Structure
```text
src/
├── collision/                    # Collision detection & listeners
│   └── listeners/hit_listeners/  # BlockRemover, BallRemover, ScoreTracking, SoundMaker
├── gui/
│   ├── animations/               # Animation framework (GameLevel, PauseScreen, etc.)
│   ├── levels/                   # Level definitions (DirectHit, Friends, Terminator, etc.)
│   ├── shapes/                   # Geometry helpers: Point, Line, Rectangle
│   └── ScreenSettings.java       # Global screen size constants
├── music/                        # Sound engine: MusicPlayer, Sound
├── programs/                     # Main entry point and sample programs
│   └── BallBlockBreaker.java     # Main game launcher
├── sprites/                      # Game objects: Ball, Block, Paddle
│   └── backgrounds/              # Custom animated backgrounds per level
├── tests/                        # Helper/demo test classes (optional)
└── utilities/                    # Tools: Counter, Timer, ConsoleColors
```

### 🔊 Audio Support

The game optionally plays sound effects and background music for each level. Ensure .wav files are available under a matching resources/ folder (e.g., resources/Friends/). If the audio files are missing, the game will still run but without sound.


### 🧠 Key OOP Concepts Demonstrated

- Use of interfaces like Sprite, Collidable, HitListener, Animation

- Decoupled event systems using listeners (e.g., for hit tracking or sound effects)

- Composition of game entities and infrastructure

- Reusable animation engine (AnimationRunner)

- Modular design for levels and game flow (GameFlow, LevelInformation)


### ✨ Screens Included

- Countdown before each level

- Pause screen

- Win screen

- Game over screen

- Instructions screen


### 🙏 Credits

- **biuoop library** provided as part of an academic OOP course at Bar-Ilan University.

- **Game logic and design** implemented independently by the project developer as part of a full OOP assignment.

- **Inspiration:** Classic games like Breakout and Arkanoid.

### 📄 License

This project was created by **Arthur Rennert**. You are free to **use, copy, modify, distribute, and share** this code for any purpose — personal, academic, or commercial. No permission is needed. Just give credit if you can 😊
