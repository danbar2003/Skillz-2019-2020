package bots.missions;
import penguin_game.*;

public class UpgradeIceberg implements Mission{

    /**
     * @param game
     * @param iceberg
     * @return
     */
    @Override
    public boolean act(Game game, Iceberg iceberg) {
        if (iceberg.canUpgrade()){
            iceberg.upgrade();
            return true;
        }
        System.out.println("UpgradeIceberg : could not upgrade iceberg at location:"+iceberg.__location);
        return false;
    }

}
