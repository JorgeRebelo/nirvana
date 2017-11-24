package org.academiadecodigo.hackathon.apologies.game.objects;

public enum IntroMessage {
    LEVEL_1("What else should I be?\n" + "All apologies."),
    LEVEL_2("What else could I say?\n" + "Everyone is gay."),
    LEVEL_3("What else should I write?\n" + "I don't have the right."),
    LEVEL_4("What else should I be?\n" + "All apologies.");

    private String message;

    IntroMessage(String string) {

    message = string;

    }
}
