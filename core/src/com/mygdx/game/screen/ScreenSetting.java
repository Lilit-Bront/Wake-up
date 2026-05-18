package com.mygdx.game.screen;

import static com.mygdx.game.GameSettings.SCREEN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.component.TextButton;

public class ScreenSetting implements Screen {

    MyGdxGame myGdxGame;

    TextButton buttonResume;
    MyGdxGame game;
    TextButton buttonOptions;
    TextButton buttonExit;
    Texture background;

    public ScreenSetting(MyGdxGame myGdxGame) {
        this.game = myGdxGame;
        buttonExit = new TextButton(290, 250, "Textures/buttons/Out.png", "Textures/buttons/Out.png", 112, 64);
        buttonResume = new TextButton(240, 320, "Textures/buttons/resume.png", "Textures/buttons/resume.png", 218, 65);

        background = new Texture("Textures/background/backgroundScreen.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(game.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        buttonResume.draw(game.batch);
        buttonExit.draw(game.batch);
        game.batch.end();

        if (Gdx.input.justTouched()) {
            int touchX = Gdx.input.getX();
            int touchY = Gdx.input.getY();
            Vector3 unprojectCamera = game.camera.unproject(new Vector3(touchX, touchY, 0));
            if (buttonResume.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
                game.setScreen(game.screenMenu);
            } else if (buttonExit.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
                Gdx.app.exit();
            }

    }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        buttonOptions.dispose();
    }
}
