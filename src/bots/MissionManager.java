package bots;

import bots.missions.SavePenguins;
import penguin_game.*;

public class MissionManager {

    public static Protocol<Iceberg> createIcebergMission(Iceberg iceberg){
        Protocol<Iceberg> protocol = new Protocol<>();

        if (Utils.attackingPenguinsComing(iceberg) > Utils.friendlyPenguinsComing(iceberg)) {
            protocol.add(new SavePenguins());
            return protocol;
        }
        return protocol;
    }
}