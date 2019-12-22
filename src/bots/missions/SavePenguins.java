package bots.missions;
//asd

import penguin_game.*;

public class SavePenguins implements Mission {

    @Override
    public State act(Game game, Iceberg iceberg) {
        return State.FINISHED;
    }
}
