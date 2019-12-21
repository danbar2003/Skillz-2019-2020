package bots.missions;
//asd
import penguin_game.*;

public class SavePenguins implements Mission<Iceberg> {

    @Override
    public State act(Iceberg iceberg){
        return State.FINISHED;
    }
}
