package bots.actions;

import bots.wrapper.MyIceberg;

public class SupportThreatenedIceberg implements Action {

    private MyIceberg supportedIceberg;

    public SupportThreatenedIceberg(MyIceberg supportedIceberg) {
        this.supportedIceberg = supportedIceberg;
    }

    public int benefit() {
        return 2 * supportedIceberg.iceberg.level;
    }
}
