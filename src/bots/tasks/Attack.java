package bots.tasks;

import bots.wrapper.MyIceberg;


public class Attack implements Taskable {
    private MyIceberg attackingIceberg;
    private MyIceberg targetIceberg;
    private int penguinAmount;

    public Attack(MyIceberg attackingIceberg, MyIceberg targetIceberg, int penguinAmount) {
        this.attackingIceberg = attackingIceberg;
        this.targetIceberg = targetIceberg;
        this.penguinAmount = penguinAmount;
    }

    @Override
    public MyIceberg getActor() {
        return attackingIceberg;
    }

    @Override
    public void act() {
        this.attackingIceberg.sendPenguins(this.targetIceberg, penguinAmount);
    }

    @Override
    public int loss() {
        return 0;
    }
}
