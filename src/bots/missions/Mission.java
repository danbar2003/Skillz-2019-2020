package bots.missions;

import bots.wrapper.MyIceberg;
import penguin_game.*;

public interface Mission {

    boolean act(Game game, Iceberg iceberg);
}
