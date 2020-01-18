package bots.missions;

import bots.wrapper.MyIceberg;

public class AttackEnemyIceberg implements Mission{

    private MyIceberg target;
    private State state;

    public AttackEnemyIceberg(MyIceberg target){
        this.target = target;
    }

    /**
     *
     * @return (penguins - penguinsUsed) * turnsLeft
     */
    public int benefit(){
        return 0;
    }
}
