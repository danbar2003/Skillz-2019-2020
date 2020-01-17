package bots.missions;

import bots.wrapper.MyIceberg;

public class SupportIceberg implements Mission {

    private MyIceberg supportedIceberg;
    private State state;

    public SupportIceberg(MyIceberg supportedIceberg){
        this.supportedIceberg = supportedIceberg;
    }

    public int value(){
        return 2 * supportedIceberg.iceberg.level;
    }
}
