package bots.missions;

import bots.Utils;
import bots.wrapper.MyGame;
import penguin_game.*;

public class AttackWeakestIceberg implements Mission<Iceberg> {

    @Override
    public State act(Iceberg iceberg){
        return State.FINISHED;
    }
}
