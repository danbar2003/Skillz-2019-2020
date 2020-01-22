package bots.missions;

import bots.tasks.Taskable;
import bots.wrapper.MyIceberg;

import java.util.Set;

public interface Mission {

    enum State{
        ACTIVE,
        FINISHED,
        STOP
    }

    void setState(State state);

    MyIceberg getTarget();

    Set<Set<Taskable>> getWaysToExecute();

    void calcWaysToExecute();

    String getType();

    int benefit();
}
