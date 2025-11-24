package com.mygdx.game;

import static com.mygdx.game.GameSettings.POSITION_ITERATIONS;
import static com.mygdx.game.GameSettings.STEP_TIME;
import static com.mygdx.game.GameSettings.VELOCITY_ITERATIONS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.physics.ContactManager;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.ScreenMenu;
import com.mygdx.game.screen.ScreenRestart;

public class MyGdxGame extends Game {

    public Box2DDebugRenderer debugRenderer;
    public OrthographicCamera camera;
    public Batch batch;

    public World world;
    private float accumulator;

    public GameScreen gameScreen;
    public ScreenMenu screenMenu;
    public ScreenRestart screenRestart;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);


        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -10), true);
        world.setContactListener(new ContactManager());

        gameScreen = new GameScreen(this);
        screenMenu = new ScreenMenu(this);
        screenRestart = new ScreenRestart(this);
        setScreen(screenMenu);
    }


    public void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }
}