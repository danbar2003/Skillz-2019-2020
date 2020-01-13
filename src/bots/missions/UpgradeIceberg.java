package bots.missions;
import bots.wrapper.*;
import penguin_game.*;
import java.util.*;


public class UpgradeIceberg implements Mission {

    /**
     * @param iceberg - iceberg acting
     * @return - if acted or not
     */
    @Override
    public boolean act(MyIceberg iceberg) {
        System.out.println(iceberg + "\n is executing UpgradeIceberg mission");
        if (iceberg.iceberg.canUpgrade()) {
            iceberg.iceberg.upgrade();
            return true;
        }
        System.out.println("UpgradeIceberg : could not upgrade iceberg:\n" + iceberg.iceberg.__location);
        return false;
    }

}
