package bots.missions;

import bots.wrapper.*;
import penguin_game.*;

public class UpgradeIceberg implements Mission {

    /**
     * @param game - game info
     * @param iceberg - iceberg acting
     * @return - if acted or not
     */
    @Override
    public boolean act(MyGame game, MyIceberg iceberg) {
        System.out.println(iceberg + "\n is executing UpgradeIceberg mission");
        if (iceberg.iceberg.canUpgrade()) {
            iceberg.iceberg.upgrade();
            return true;
        }
        System.out.println("UpgradeIceberg : could not upgrade iceberg:\n" + iceberg.iceberg.__location);
        return false;
    }

}
