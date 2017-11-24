package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by codecadet on 24/11/17.
 */
public class WorldCollider implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        System.out.println("WorldCollider.beginContact" + contact);
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