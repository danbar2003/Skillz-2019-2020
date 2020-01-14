package bots.actions;

import bots.wrapper.MyIceberg;

public class AttackEnemyIceberg implements Action{

    private MyIceberg enemyIceberg;

    public AttackEnemyIceberg(MyIceberg enemyIceberg){
        this.enemyIceberg = enemyIceberg;
    }

    public int benefit(){
        return 2*enemyIceberg.iceberg.level;
    }
}
