package org.academiadecodigo.hackathon.apologies.game.objects;

import java.util.Iterator;

public interface Collidable {

    boolean collidesWith( GameObject gameObject);

    void destroy(Iterator iterator);
}
