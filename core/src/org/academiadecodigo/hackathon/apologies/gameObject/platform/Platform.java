package org.academiadecodigo.hackathon.apologies.gameObject.platform;

public class Platform {

    //Properties
    private int width;
    private int height;
    private float x;
    private float y;
    private PlatformType platformType;
    private boolean goThrough;

    //Constructor
    public Platform(int height, int width, float x, float y) {

        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        goThrough = true;

    }

    //Methods
    public void paint() {

        if(y < 90) {

            //platformType = thorns or something

        }

        if(y < 80) {



        }

    }

    public void setGoThrough(boolean goThrough) {
        this.goThrough = goThrough;
    }

    public boolean isGoThrough() {
        return goThrough;
    }
}
