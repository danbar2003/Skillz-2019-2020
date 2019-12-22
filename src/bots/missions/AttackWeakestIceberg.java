package bots.missions;

import penguin_game.*;

public class AttackWeakestIceberg implements Mission {

    @Override
    public State act(Game game, Iceberg iceberg) {
        return State.FINISHED;
    }
}
