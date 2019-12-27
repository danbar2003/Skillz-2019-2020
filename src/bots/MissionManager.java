package bots;

import bots.missions.*;
import bots.wrapper.*;
import penguin_game.*;

import java.util.*;

public class MissionManager {

    /**
     * This function creates for each iceberg a List of missions in the execute order/priority.
     *
     * @param game
     * @return Map of executing Icebergs (Keys) and Lists of Missions to execute (Value).
     */
    public static Map<MyIceberg, List<Mission>> createMissionsForIcebergs(MyGame game) {
        //Map of executing icebergs(Keys) and lists of missions(values) for each one of them to execute.
        Map<MyIceberg, List<Mission>> icebergMissionMap = new HashMap<>();
        for (MyIceberg iceberg : game.getMyIcebergs()) {
            //what missions should the iceberg have.
            List<Mission> icebergMissions = new LinkedList<>();
            icebergMissionMap.put(iceberg, icebergMissions);
        }
        return icebergMissionMap;
    }
}