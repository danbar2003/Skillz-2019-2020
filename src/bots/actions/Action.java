package bots.actions;

public interface Action {

    enum State{
        CONTINUE,
        FINISHED,
        ABORT
        ;
    }

    int benefit();
}
