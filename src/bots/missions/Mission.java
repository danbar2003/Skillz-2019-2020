package bots.missions;
import penguin_game.*;
public interface Mission<T> {

    enum State{
        CONTINUE,
        FINISHED,
        STOP;
    }

    State act(T t);

}
