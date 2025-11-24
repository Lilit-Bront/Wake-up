package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Component {
    Texture texture;
    int x;
    int y;
    int width;
    int height;

    public Component(Texture texture, int width, int height) {
        this(texture, width, height, 0, 0);
    }

    public Component(Texture texture, int width, int height, int x, int y) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void draw(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }
}
