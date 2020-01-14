package bots.actions;

import bots.wrapper.MyIceberg;

public class UpgradeIceberg implements Action {

    private MyIceberg iceberg;

    public UpgradeIceberg(MyIceberg iceberg){
        this.iceberg = iceberg;
    }

    public int benefit(){
        return 1;
    }
}
