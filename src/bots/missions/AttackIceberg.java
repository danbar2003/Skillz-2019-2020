package bots.missions;

import bots.wrapper.MyGame;
import bots.wrapper.MyIceberg;

public class AttackIceberg implements Mission {

    private MyIceberg target;
    private int penguinAmount;

    public AttackIceberg(MyIceberg target , int penguinAmount){
        this.target = target;
        this.penguinAmount = penguinAmount;
    }
    /**
     * @param game - game info
     * @param iceberg -
     * @return returns if the iceberg actually acted
     */
    @Override
    public boolean act(MyGame game, MyIceberg iceberg) {
        iceberg.sendPenguins(target, penguinAmount);
        return false;
    }
}