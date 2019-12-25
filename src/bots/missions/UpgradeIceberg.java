package bots.missions;

import penguin_game.*;

public class UpgradeIceberg implements Mission {

    /**
     * @param game
     * @param iceberg
     * @return
     */
    @Override
    public boolean act(Game game, Iceberg iceberg) {
        System.out.println(iceberg + "\n is executing UpgradeIceberg mission");
        if (iceberg.canUpgrade()) {
            iceberg.upgrade();
            return true;
        }
        System.out.println("UpgradeIceberg : could not upgrade iceberg:\n" + iceberg.__location);
        return false;
    }

}
