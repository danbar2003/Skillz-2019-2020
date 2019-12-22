package bots;

import bots.missions.Mission;
import penguin_game.*;

import java.util.Queue;


public class Protocol implements Mission {

    public Queue<Mission> protocol;

    @Override
    public State act(Game game, Iceberg iceberg) {
        return State.ACTED;
    }


    public void add(Mission mission) {
        protocol.add(mission);
    }

    public void remove(Mission mission) {
        protocol.remove(mission);
    }
}
