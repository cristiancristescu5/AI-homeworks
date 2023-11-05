package org.example;

public enum Player {
    AI(-1), HUMAN(1);

    private final int tag;

    Player(int tag){
        this.tag = tag;
    }

    public int getTag(){
        return this.tag;
    }
}
