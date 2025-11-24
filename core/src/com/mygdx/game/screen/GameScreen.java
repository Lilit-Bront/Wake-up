package com.mygdx.game.screen;

import static com.mygdx.game.GameSettings.SCALE;
import static com.mygdx.game.GameSettings.SCREEN_WIDTH;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.component.Background;
import com.mygdx.game.component.Life;
import com.mygdx.game.component.MobileButtons;
import com.mygdx.game.component.Player;
import com.mygdx.game.component.StaticBlock;
import com.mygdx.game.component.TextButton;
import com.mygdx.game.physics.WorldManager;

public class GameScreen extends ScreenAdapter {
    MyGdxGame game;
    WorldManager worldManager;
    Background background;
    MobileButtons mobileButtons;
    TextButton buttonStop;
    Life life;


    public GameScreen(MyGdxGame game) {
        this.game = game;
        life = new Life();
        worldManager = new WorldManager();
        mobileButtons = new MobileButtons();
        background = new Background(new Texture("Textures/background/backgroundGame.png"));
        buttonStop = new TextButton(1130, 600, "Textures/buttons/Stop.png", "Textures/buttons/Stop.png", 80, 80);
    }

    public void handleInput() {
        for (int i = 0; i < Gdx.input.getMaxPointers(); i++) {
            int touchX = Gdx.input.getX(i);
            int touchY = Gdx.input.getY(i);
            Vector3 unprojectCamera = game.camera.unproject(new Vector3(touchX, touchY, 0));
            if (Gdx.input.isKeyPressed(Input.Keys.W) || mobileButtons.buttonStop.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
                game.camera.position.x = SCREEN_WIDTH / 2;
                game.setScreen(game.screenRestart);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || mobileButtons.buttonBackwardTexture.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
                worldManager.player.moveLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || mobileButtons.buttonForwardTexture.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
                worldManager.player.moveRight();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || mobileButtons.buttonUpTexture.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
                worldManager.player.moveUp();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.F) || mobileButtons.buttonAttackTexture.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
                worldManager.player.moveAttackRight();
            }
        }
    }

    public void moveCamera() {
        if (worldManager.player.physicsObject.getX() > SCREEN_WIDTH / 2) {
            game.camera.position.x = worldManager.player.physicsObject.getX();
        } else {
            game.camera.position.x = SCREEN_WIDTH / 2;
        }
        if (worldManager.player.physicsObject.getX() > worldManager.getWorldWidth() - SCREEN_WIDTH / 2) {
            game.camera.position.x = worldManager.getWorldWidth() - SCREEN_WIDTH / 2;
        }
        game.camera.update();
    }

    @Override
    public void render(float delta) {
        handleInput();

        worldManager.stepWorld();
        moveCamera();
        game.batch.setProjectionMatrix(game.camera.combined);

        ScreenUtils.clear(Color.CLEAR);
        game.batch.begin();
        background.draw(game.batch, worldManager.tileScale);
        game.batch.end();

        worldManager.mapRenderer.setView(game.camera);
        worldManager.mapRenderer.render();

        game.batch.begin();
        worldManager.player.draw(game.batch);
        for (int i = 0; i < worldManager.enemyList.size(); i++) {
            worldManager.enemyList.get(i).draw(game.batch);
        }
        for (int i = 0; i < worldManager.noteList.size(); i++) {
            worldManager.noteList.get(i).draw(game.batch);

            if (Gdx.input.justTouched()) {
                worldManager.noteList.get(i).isTouchPlayer = false;
            }
        }
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            mobileButtons.draw(game.batch, game.camera.position.x);
        }

        life.draw(game.batch, worldManager.player.countOfLives, game.camera.position.x);

        for (int i = 0; i < worldManager.noteList.size(); i++) {
            worldManager.noteList.get(i).drawNote(game.batch, (int) game.camera.position.x);

        }
        game.batch.end();


        //game.debugRenderer.render(worldManager.world, game.camera.combined.cpy().scl(1 / SCALE));

    }
}
