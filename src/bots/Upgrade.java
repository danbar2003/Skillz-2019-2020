package bots;


public class Upgrade implements Taskable {
    private MyIceberg upgradingIceberg;

    public Upgrade(MyIceberg iceberg) {
        this.upgradingIceberg = iceberg;
    }

    @Override
    public MyIceberg getActor() {
        return upgradingIceberg;
    }

    @Override
    public void act() {
        this.upgradingIceberg.iceberg.upgrade();
    }

    @Override
    public int loss() {
        return upgradingIceberg.iceberg.upgradeCost/upgradingIceberg.iceberg.penguinAmount;
    }

    @Override
    public MyIceberg getTarget(){
        return upgradingIceberg;
    }

    @Override
    public int penguins() {
        return upgradingIceberg.iceberg.upgradeCost;
    }
}
