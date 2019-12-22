package bots.missions;
import penguin_game.*;

public interface Mission{

    enum State{
        NOT_ACTED,
        ACTED;
    }

   State act(Game game, Iceberg iceberg);
}
