package bots.missions;

import bots.wrapper.MyIceberg;

public class AttackEnemyIceberg implements Mission{

    private MyIceberg target;
    private State state;

    public AttackEnemyIceberg(MyIceberg target){
        this.target = target;
    }

    public int value(){
        return 2 * target.iceberg.level;
    }
}
