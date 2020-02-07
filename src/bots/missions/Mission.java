package bots.missions;

import bots.tasks.TaskGroup;
import bots.tasks.Taskable;
import bots.wrapper.MyIceberg;

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
