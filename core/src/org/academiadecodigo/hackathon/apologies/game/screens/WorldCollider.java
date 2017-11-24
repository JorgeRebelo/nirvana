package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mail.vandrake.control.VSound;
import org.academiadecodigo.hackathon.apologies.SoundManager;
import org.academiadecodigo.hackathon.apologies.game.objects.Buff;

/**
 * Created by codecadet on 24/11/17.
 */
public class WorldCollider implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        if (contact.getFixtureB().getUserData() instanceof Buff) {

            contact.setEnabled(false);
            Buff buff = ((Buff) contact.getFixtureB().getUserData());
            buff.destroy();
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}