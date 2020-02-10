package bots.wrapper;

import bots.wrapper.MyGameObject;
import penguin_game.PenguinGroup;

public class MyPenguinGroup extends MyGameObject {

    public final PenguinGroup penguinGroup;

    public MyPenguinGroup(PenguinGroup penguinGroup){
        super(penguinGroup);
        this.penguinGroup = penguinGroup;
    }
}
