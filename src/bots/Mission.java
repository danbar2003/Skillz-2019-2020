package bots;


import bots.TaskGroup;
import bots.MyIceberg;

import java.util.List;

public interface Mission {

    enum State{
        ACTIVE,
        FINISHED,
        STOP
    }

    void setState(State state);

    MyIceberg getTarget();

    List<TaskGroup> getWaysToExecute();

    void calcWaysToExecute();

    String getType();

    int benefit();
}
