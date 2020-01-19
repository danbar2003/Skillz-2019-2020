package bots.missions;

import bots.wrapper.MyIceberg;

public interface Mission {

    enum State{
        ACTIVE,
        FINISHED,
        STOP
    }

    void setState(State state);

    MyIceberg getTarget();

    int benefit();
}
