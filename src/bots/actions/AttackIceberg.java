package bots.actions;

import bots.Constant;
import bots.wrapper.MyIceberg;

public class AttackIceberg implements Action{

    private MyIceberg attackedIceberg;

    public AttackIceberg(MyIceberg attackedIceberg){
        this.attackedIceberg = attackedIceberg;
    }

    public int benefit(){
        if (attackedIceberg.iceberg.owner.equals(Constant.Players.enemyPlayer))
            return 2 * attackedIceberg.iceberg.level;
        return attackedIceberg.iceberg.level;
    }
}
