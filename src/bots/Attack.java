    package bots;


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
        return penguinAmount + attackingIceberg.iceberg.getTurnsTillArrival(targetIceberg.iceberg);
    }

    @Override
    public MyIceberg getTarget(){
        return targetIceberg;
    }

    @Override
    public int penguins() {
        return penguinAmount;
    }

        @Override
        public String toString(){
            return "Attack| Attacking: " + attackingIceberg.iceberg + " Attacked: " + targetIceberg.iceberg;
        }
}
