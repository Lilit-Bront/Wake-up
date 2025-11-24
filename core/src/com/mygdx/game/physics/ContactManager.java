package com.mygdx.game.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.component.Enemy;
import com.mygdx.game.component.Note;
import com.mygdx.game.component.Player;
import com.mygdx.game.component.StaticBlock;

public class ContactManager implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object object1 = fixtureA.getUserData();
        Object object2 = fixtureB.getUserData();

        if (object1 instanceof Player && object2 instanceof StaticBlock) {
            ((Player) object1).onTouchFlour();
        } else if (object1 instanceof StaticBlock && object2 instanceof Player) {
            ((Player) object2).onTouchFlour();
        }

        if (object1 instanceof Player && object2 instanceof Enemy) {
            ((Player) object1).onTouchEnemy();
            ((Enemy) object2).onTouchPlayer();
        } else if (object1 instanceof Enemy && object2 instanceof Player) {
            ((Player) object2).onTouchEnemy();
            ((Enemy) object1).onTouchPlayer();
        }

        if (object1 instanceof Player && object2 instanceof Note) {
            ((Player) object1).onTouchEnemy();
            ((Note) object2).onTouchPlayer();
        } else if (object1 instanceof Note && object2 instanceof Player) {
            ((Player) object2).onTouchEnemy();
            ((Note) object1).onTouchPlayer();
        }


        System.out.println("Categorial bit a: " + fixtureA.getFilterData().categoryBits);
        System.out.println("User data a     : " + fixtureA.getUserData());
        System.out.println("Categorial bit b: " + fixtureB.getFilterData().categoryBits);
        System.out.println("User data b     : " + fixtureB.getUserData());


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
