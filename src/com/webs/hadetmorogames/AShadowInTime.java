package com.webs.hadetmorogames;

import java.util.ArrayList;

import com.webs.hadetmorogames.entity.Entity;
import com.webs.hadetmorogames.renderer.Sprite;
import com.webs.hadetmorogames.renderer.TextureLoader;
import com.webs.hadetmorogames.sound.AudioManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class AShadowInTime {

    /** The normal title of the window */
    private String				WINDOW_TITLE					= "A Shadow In Time";

    /** The width of the game display area */
    private int						width									= 800;

    /** The height of the game display area */
    private int						height								= 600;

    /** The loader responsible for converting images into OpenGL textures */
    private TextureLoader textureLoader;

    /** The list of all the entities that exist in our game */
    private ArrayList<Entity>			entities							= new ArrayList<Entity>();

    /** The list of entities that need to be removed from the game this loop */
    private ArrayList<Entity>			removeList						= new ArrayList<Entity>();

    /** The message to display which waiting for a key press */
    private Sprite message;

    /** The sprite containing the "Press Any Key" message */
    private Sprite				pressAnyKey;

    /** The sprite containing the "You win!" message */
    private Sprite				youWin;

    /** The sprite containing the "You lose!" message */
    private Sprite				gotYou;

    /** Last shot index */
    private int						shotIndex;

    /** The speed at which the player's ship should move (pixels/sec) */
    private float					moveSpeed							= 300;

    /** The time at which last fired a shot */
    private long					lastFire;

    /** The interval between our players shot (ms) */
    private long					firingInterval				= 500;

    /** The number of aliens left on the screen */
    private int						alienCount;

    /** True if we're holding up game play until a key has been pressed */
    private boolean				waitingForKeyPress		= true;

    /** True if game logic needs to be applied this loop, normally as a result of a game event */
    private boolean				logicRequiredThisLoop;

    /** The time at which the last rendering looped started from the point of view of the game logic */
    private long					lastLoopTime					= getTime();

    /** True if the fire key has been released */
    private boolean				fireHasBeenReleased;

    /** The time since the last record of fps */
    private long					lastFpsTime;

    /** The recorded fps */
    private int						fps;

    private static long		timerTicksPerSecond		= Sys.getTimerResolution();

    /** True if the game is currently "running", i.e. the game loop is looping */
    public static boolean	gameRunning						= true;

    /** SoundManager to make sound with */
    private AudioManager audioManager;

    /** Whether we're running in fullscreen mode */
    private boolean				fullscreen;

    /** ID of shot effect */
    private int						SOUND_SHOT;

    /** ID of hit effect */
    private int						SOUND_HIT;

    /** ID of start sound */
    private int						SOUND_START;

    /** ID of win sound */
    private int						SOUND_WIN;

    /** ID of loose sound */
    private int						SOUND_LOOSE;

    /** Mouse movement on x axis */
    private int	mouseX;

    /** Is this an application or applet */
    private static boolean isApplication;

    /**
     * Construct our game and set it running.
     * @param fullscreen
     *
     */
    public AShadowInTime(boolean fullscreen) {
        this.fullscreen = fullscreen;
        init();
    }

    /**
     * Get the high resolution time in milliseconds
     *
     * @return The high resolution time in milliseconds
     */
    public static long getTime() {
        // we get the "timer ticks" from the high resolution timer
        // multiply by 1000 so our end result is in milliseconds
        // then divide by the number of ticks in a second giving
        // us a nice clear time in milliseconds
        return (Sys.getTime() * 1000) / timerTicksPerSecond;
    }

    /**
     * Sleep for a fixed number of milliseconds.
     *
     * @param duration The amount of time in milliseconds to sleep for
     */
    public static void sleep(long duration) {
        try {
            Thread.sleep((duration * timerTicksPerSecond) / 1000);
        } catch (InterruptedException inte) {
        }
    }

    /**
     * Initialise the common elements for the game
     */
    public void init() {
        // initialize the window beforehand
        try {
            setDisplayMode();
            Display.setTitle(WINDOW_TITLE);
            Display.setFullscreen(fullscreen);
            Display.create();

            // grab the mouse, dont want that hideous cursor when we're playing!
            if (isApplication) {
                Mouse.setGrabbed(true);
            }

            // enable textures since we're going to use these for our sprites
            glEnable(GL_TEXTURE_2D);

            // disable the OpenGL depth test since we're rendering 2D graphics
            glDisable(GL_DEPTH_TEST);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();

            glOrtho(0, width, height, 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glViewport(0, 0, width, height);

            textureLoader = new TextureLoader();

            // create our sound manager, and initialize it with 8 channels
            //1 channel for music, 1 channel for cutscene dialogue, 1 channel for cutscene background noise,
            //1 channel for in game dialogue, 1 channel for in game background noise, 2 channels for sound effects,
            //and 1 extra channel for emergencies
            audioManager = new AudioManager();
            audioManager.init(9);

            // load our sound data
            SOUND_SHOT   = audioManager.addSound("shot.wav");
            SOUND_HIT    = audioManager.addSound("hit.wav");
            SOUND_START  = audioManager.addSound("start.wav");
            SOUND_WIN    = audioManager.addSound("win.wav");
            SOUND_LOOSE  = audioManager.addSound("loose.wav");
        } catch (LWJGLException le) {
            System.out.println("Game exiting - exception in initialization:");
            le.printStackTrace();
            AShadowInTime.gameRunning = false;
            return;
        }

        // get our sprites
        gotYou = getSprite("gotyou.gif");
        pressAnyKey = getSprite("pressanykey.gif");
        youWin = getSprite("youwin.gif");

        message = pressAnyKey;

        // setup the initial game state
        startGame();
    }

    /**
     * Sets the display mode for fullscreen mode
     */
    private boolean setDisplayMode() {
        try {
            // get modes
            DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(width, height, -1, -1, -1, -1, 60, 60);

            org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
                    "width=" + width,
                    "height=" + height,
                    "freq=" + 60,
                    "bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to enter fullscreen, continuing in windowed mode");
        }

        return false;
    }

    /**
     * Start a fresh game, this should clear out any old data and
     * create a new set.
     */
    private void startGame() {
        // clear out any existing entities and intialise a new set
        entities.clear();
        initEntities();
    }

    /**
     * Initialise the starting state of the entities (ship and aliens). Each
     * entitiy will be added to the overall list of entities in the game.
     */
    private void initEntities() {
 }

    /**
     * Notification from a game entity that the logic of the game
     * should be run at the next opportunity (normally as a result of some
     * game event)
     */
    public void updateLogic() {
        logicRequiredThisLoop = true;
    }

    /**
     * Remove an entity from the game. The entity removed will
     * no longer move or be drawn.
     *
     * @param entity The entity that should be removed
     */
    public void removeEntity(Entity entity) {
        removeList.add(entity);
    }

    /**
     * Notification that the player has died.
     */
    public void notifyDeath() {
        if (!waitingForKeyPress) {
            audioManager.playSound(SOUND_LOOSE);
        }
        message = gotYou;
        waitingForKeyPress = true;
    }


    /**
     * Run the main game loop. This method keeps rendering the scene
     * and requesting that the callback update its screen.
     */
    private void gameLoop() {
        while (AShadowInTime.gameRunning) {
            // clear screen
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();

            // let subsystem paint
            frameRendering();

            // update window contents
            Display.update();
        }

        // clean up
        audioManager.destroy();
        Display.destroy();
    }

    /**
     * Notification that a frame is being rendered. Responsible for
     * running game logic and rendering the scene.
     */
    public void frameRendering() {
        //SystemTimer.sleep(lastLoopTime+10-SystemTimer.getTime());
        Display.sync(60);

        // work out how long its been since the last update, this
        // will be used to calculate how far the entities should
        // move this loop
        long delta = getTime() - lastLoopTime;
        lastLoopTime = getTime();
        lastFpsTime += delta;
        fps++;

        // update our FPS counter if a second has passed
        if (lastFpsTime >= 1000) {
            Display.setTitle(WINDOW_TITLE + " (FPS: " + fps + ")");
            lastFpsTime = 0;
            fps = 0;
        }

        // cycle round asking each entity to move itself
        if (!waitingForKeyPress && !audioManager.isPlayingSound()) {
            for ( Entity entity : entities ) {
                entity.move(delta);
            }
        }

        // cycle round drawing all the entities we have in the game
        for ( Entity entity : entities ) {
            entity.draw();
        }

        // brute force collisions, compare every entity against
        // every other entity. If any of them collide notify
        // both entities that the collision has occured
        for (int p = 0; p < entities.size(); p++) {
            for (int s = p + 1; s < entities.size(); s++) {
                Entity me = entities.get(p);
                Entity him = entities.get(s);

                if (me.collidesWith(him)) {
                    me.collidedWith(him);
                    him.collidedWith(me);
                }
            }
        }

        // remove any entity that has been marked for clear up
        entities.removeAll(removeList);
        removeList.clear();

        // if a game event has indicated that game logic should
        // be resolved, cycle round every entity requesting that
        // their personal logic should be considered.
        if (logicRequiredThisLoop) {
            for ( Entity entity : entities ) {
                entity.doLogic();
            }

            logicRequiredThisLoop = false;
        }

        // if we're waiting for an "any key" press then draw the
        // current message
        if (waitingForKeyPress) {
            message.draw(325, 250);
        }


        mouseX = Mouse.getDX();

        // we delegate input checking to submethod since we want to check
        // for keyboard, mouse & controller
        boolean leftPressed   = hasInput(Keyboard.KEY_LEFT);
        boolean rightPressed  = hasInput(Keyboard.KEY_RIGHT);
        boolean firePressed   = hasInput(Keyboard.KEY_SPACE);

        if (!waitingForKeyPress && !audioManager.isPlayingSound()) {
        } else {
            if (!firePressed) {
                fireHasBeenReleased = true;
            }
            if ((firePressed) && (fireHasBeenReleased) && !audioManager.isPlayingSound()) {
                waitingForKeyPress = false;
                fireHasBeenReleased = false;
                startGame();
                audioManager.playSound(SOUND_START);
            }
        }

        // if escape has been pressed, stop the game
        if ((Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) && isApplication) {
            AShadowInTime.gameRunning = false;
        }

    }

    private boolean hasInput(int direction) {
        switch(direction) {
            case Keyboard.KEY_LEFT:
                return
                        Keyboard.isKeyDown(Keyboard.KEY_LEFT) ||
                                mouseX < 0;

            case Keyboard.KEY_RIGHT:
                return
                        Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ||
                                mouseX > 0;

            case Keyboard.KEY_SPACE:
                return
                        Keyboard.isKeyDown(Keyboard.KEY_SPACE) ||
                                Mouse.isButtonDown(0);
        }
        return false;
    }

    public static void main(String args[]) {
        isApplication = true;
        System.out.println("Use -fullscreen for fullscreen mode");
        new AShadowInTime((args.length > 0 && "-fullscreen".equalsIgnoreCase(args[0]))).execute();
        System.exit(0);
    }


    public void execute() {
        gameLoop();
    }

    /**
     * Create or get a sprite which displays the image that is pointed
     * to in the classpath by "ref"
     *
     * @param ref A reference to the image to load
     * @return A sprite that can be drawn onto the current graphics context.
     */
    public Sprite getSprite(String ref) {
        return new Sprite(textureLoader, ref);
    }
}