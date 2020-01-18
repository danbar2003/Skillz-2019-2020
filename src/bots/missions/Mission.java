package bots.missions;

public interface Mission {

    enum State{
        ACTIVE,
        FINISHED,
        STOP
    }

    int benefit();
}
