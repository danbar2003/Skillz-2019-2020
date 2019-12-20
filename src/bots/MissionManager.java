package bots;

import bots.missions.AttackWeakest;
import bots.wrapper.MyGame;
import penguin_game.*;

public class MissionManager {

    public static Protocol<Iceberg> createIcebergMission(Iceberg iceberg){
        Protocol<Iceberg> protocol = new Protocol<>();
        protocol.add(new AttackWeakest());
        return protocol;
    }
}