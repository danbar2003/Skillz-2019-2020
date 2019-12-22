package bots.missions;
//asd
import penguin_game.*;

public class UpgradeIceberg implements Mission{

    public State act(Game game, Iceberg iceberg){
        if (iceberg.canUpgrade()) {
            iceberg.upgrade();
            return State.ACTED;
        }
        return State.NOT_ACTED;
    }
}
