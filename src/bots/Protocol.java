package bots;

import bots.missions.Mission;
import penguin_game.*;
import java.util.Queue;


public class Protocol implements Mission{
    public Queue<Mission> protocol;

    @Override
    public State act(Game game, Iceberg iceberg){
        State state = State.FINISHED;
        for (Mission mission: protocol) {
             state = mission.act(game, iceberg);
        }
        return state;
    }


    public void add(Mission mission){
        protocol.add(mission);
    }

    public void remove(Mission mission){
        protocol.remove(mission);
    }
}
