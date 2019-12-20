package bots;

import bots.missions.Mission;
import penguin_game.Iceberg;

import java.util.Queue;

public class Protocol<T> implements Mission<T> {
    public Queue<Mission<T>> protocol;

    public State act(T t){
        return State.FINISHED;
    }

    public void add(Mission<T> mission){
        protocol.add(mission);
    }

    public void remove(Mission<T> mission){
        protocol.remove(mission);
    }
}
