package bots;


public class Support implements Taskable {

    private MyIceberg supportingIceberg;
    public MyIceberg supportedIceberg;
    private int penguins;

    public Support(MyIceberg supportingIceberg, MyIceberg supportedIceberg, int penguins) {
        this.penguins = penguins;
        this.supportedIceberg = supportedIceberg;
        this.supportingIceberg = supportingIceberg;
    }

    @Override
    public MyIceberg getActor() {
        return supportingIceberg;
    }

    @Override
    public void act() {
        this.supportingIceberg.sendPenguins(this.supportedIceberg, this.penguins);
    }

    public int loss() {
        return penguins + supportingIceberg.iceberg.getTurnsTillArrival(supportedIceberg.iceberg);
    }

    @Override
    public MyIceberg getTarget(){
        return supportedIceberg;
    }

    @Override
    public int penguins() {
        return penguins;
    }

    public String toString(){
        return "Support| Supporting: " + supportingIceberg.iceberg + " Supported: " + supportedIceberg.iceberg;
    }
}
