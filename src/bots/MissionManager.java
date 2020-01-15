package bots;


import bots.missions.Mission;
import bots.wrapper.*;

import java.util.*;


public class MissionManager {

    /**
     * this function decides what missions we want to execute without telling it how.
     * @return missions we want to execute.
     */
    public static Set<Mission> missionsToExecute(){
        //TODO create this function (we need to create it together)
        Set<Mission> missionsToExec = new HashSet<>();
        return missionsToExec;
    }

    /**
     * this function decides how to execute each mission (which group of icebergs).
     * @return - for each mission (key) a set of icebergs.
     */
    public static Map<Mission, Set<MyIceberg>> howToExecute() {
        //TODO - how to choose which group will execute each mission (we need to create it together)
        return null;
    }
}