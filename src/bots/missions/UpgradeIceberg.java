package bots.missions;
//asd
import penguin_game.*;

public class UpgradeIceberg implements Mission<Iceberg> {

    public State act(Iceberg iceberg){
        if (iceberg.canUpgrade()) {
            iceberg.upgrade();
            return State.FINISHED;
        }
        return State.STOP;
    }
}
