package bots.tasks;


import bots.wrapper.MyIceberg;

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
    public MyIceberg getTarget() {
        return supportedIceberg;
    }

    @Override
    public void act() {
        this.supportingIceberg.sendPenguins(this.supportedIceberg, this.penguins);
    }

    @Override
    public int loss() {
        return 0;
    }
}
