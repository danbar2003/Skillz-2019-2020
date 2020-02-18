package bots;

import penguin_game.PenguinGroup;

public class MyPenguinGroup extends MyGameObject {

    public final PenguinGroup penguinGroup;

    public MyPenguinGroup(PenguinGroup penguinGroup){
        super(penguinGroup);
        this.penguinGroup = penguinGroup;
    }
}
