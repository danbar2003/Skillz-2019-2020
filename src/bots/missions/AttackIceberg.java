package bots.missions;

import bots.wrapper.*;
import penguin_game.*;

import java.util.*;

public class AttackIceberg implements Mission {

    private MyIceberg target;
    private int penguinAmount;

    public AttackIceberg(MyIceberg target, int penguinAmount) {
        this.target = target;
        this.penguinAmount = penguinAmount;
    }

    /**
     * @param iceberg -
     * @return returns if the iceberg actually acted
     */
    @Override
    public boolean act(MyIceberg iceberg) {
        iceberg.sendPenguins(target, penguinAmount);
        return false;
    }
}