package bots.missions;

public interface Mission {

    enum State{
        ACTIVE,
        FINISHED,
        STOP
    }

    void setState(State state);

    int benefit();
}
