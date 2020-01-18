package bots.missions;

import bots.wrapper.MyIceberg;

public class UpgradeIceberg implements Mission {

    private MyIceberg iceberg;
    private State state;

    public UpgradeIceberg(MyIceberg iceberg){
        this.iceberg = iceberg;
    }

    public int benefit(){
        return 1;
    }
}