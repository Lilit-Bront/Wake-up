package com.mygdx.game.screen;

import static com.badlogic.gdx.graphics.g2d.Gdx2DPixmap.GDX2D_FORMAT_ALPHA;
import static com.mygdx.game.GameSettings.SCREEN_HEIGHT;
import static com.mygdx.game.GameSettings.SCREEN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.component.TextButton;

public class ScreenMenu implements Screen {
    MyGdxGame myGdxGame;
    TextButton buttonStart;
    TextButton buttonMenu;
    MyGdxGame game;
    TextButton buttonOut;
    TextButton buttonOptions;
    Texture background;


    public ScreenMenu(MyGdxGame myGdxGame) {
        this.game = myGdxGame;
        buttonStart = new TextButton(250, 390, "Textures/buttons/Start.png", "Textures/buttons/Start.png", 217, 63);
        buttonOptions = new TextButton(260, 320, "Textures/buttons/Options.png", "Textures/buttons/Options.png", 188, 63);
        buttonOut = new TextButton(300, 250, "Textures/buttons/Out.png", "Textures/buttons/Out.png", 112, 64);
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
        buttonStart.draw(game.batch);
        buttonOptions.draw(game.batch);
        buttonOut.draw(game.batch);
        System.out.println(game.camera.position.x);
        System.out.println(game.camera.position.y);
        game.batch.end();

        if (Gdx.input.justTouched()) {
            int touchX = Gdx.input.getX();
            int touchY = Gdx.input.getY();
            Vector3 unprojectCamera = game.camera.unproject(new Vector3(touchX, touchY, 0));
            if (buttonStart.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
                game.setScreen(game.gameScreen);
            } else if (buttonOut.isTouched((int) unprojectCamera.x, (int) unprojectCamera.y)) {
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
        buttonStart.dispose();
        buttonOut.dispose();
    }
}
