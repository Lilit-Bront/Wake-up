package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class StaticBlock {
    PhysicsObject physicsObject;

    public StaticBlock(int width, int height, int x, int y, World world) {
        physicsObject = new PhysicsObject.PhysicalObjectBuilder(world, BodyDef.BodyType.StaticBody)
                .addRectangularFixture(width, height, (short) 1)
                .setInitialPosition(x + width / 2, y + height / 2)
                .build(this);
    }

}
