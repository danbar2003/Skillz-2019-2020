package bots.missions;

import bots.Utils;
import bots.wrapper.MyGame;
import penguin_game.*;

public class attackWeakest implements Mission<Iceberg> {

    public State act(Iceberg myIceberg) {
        return State.STOP;
    }
}
