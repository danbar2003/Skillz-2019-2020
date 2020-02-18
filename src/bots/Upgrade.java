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
        if (this.upgradingIceberg.iceberg.canUpgrade()) {
            this.upgradingIceberg.iceberg.upgrade();
        } else
            System.out.println(this.upgradingIceberg.iceberg.toString() + " Can't upgrade");
    }

    @Override
    public int loss() {
        return upgradingIceberg.iceberg.upgradeCost;
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
