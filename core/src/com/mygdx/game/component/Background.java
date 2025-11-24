package com.mygdx.game.component;

import static com.mygdx.game.GameSettings.SCREEN_HEIGHT;
import static com.mygdx.game.GameSettings.SCREEN_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Background extends Component {

    public Background(Texture texture) {
        super(texture, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void draw(Batch batch, float tileScale) {
        batch.draw(texture, 0, y, texture.getWidth() * tileScale, texture.getHeight() * tileScale);
    }
}
