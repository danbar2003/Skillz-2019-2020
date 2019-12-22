package bots.missions;

import penguin_game.*;

public interface Mission {

    enum State {
        CONTINUE,
        FINISHED,
        STOP;
    }

    State act(Game game, Iceberg iceberg);
}
