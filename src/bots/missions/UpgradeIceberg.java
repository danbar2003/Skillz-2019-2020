package bots.missions;

import penguin_game.*;

public class UpgradeIceberg implements Mission {

    public State act(Game game, Iceberg iceberg) {
        if (iceberg.canUpgrade()) {
            iceberg.upgrade();
            return State.FINISHED;
        }
        return State.STOP;
    }
}
