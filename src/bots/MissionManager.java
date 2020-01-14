package bots;


import bots.missions.Mission;
import bots.wrapper.*;
import penguin_game.*;
import java.util.*;


public class MissionManager {

    /**
     * This function creates for each iceberg a List of missions in the execute order/priority.
     * @return Map of executing Icebergs (Keys) and Lists of Missions to execute (Value).
     */
    public static Map<MyIceberg, Mission> createMissionsForIcebergs() {
        //Map of executing icebergs(Keys) and lists of missions(values) for each one of them to execute.
        Map<MyIceberg, Mission> icebergMissionMap = new HashMap<>();
        for (MyIceberg iceberg : Constant.Icebergs.myIcebergs) {
            //what mission should the iceberg have.
            //write some code here...
        }
        return icebergMissionMap;
    }
}