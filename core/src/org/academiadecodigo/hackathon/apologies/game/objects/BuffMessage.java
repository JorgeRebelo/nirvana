package org.academiadecodigo.hackathon.apologies.game.objects;

public enum BuffMessage {
    EMPATHY("Respect other people's feelings. It might mean nothing to you,but everything to them"),
    GRATITUDE("Be grateful for what you already have while you pursue your goals. If you aren't grateful for what" +
            " you already have, what makes you think you would be happy with more."),
    SELF_WORTH("Accept yourself, love yourself, and keep moving forward. If you want to fly, " +
            "you have to give up what weighs you down.");

    private String message;

    BuffMessage(String string) {

        message = string;

    }
}
