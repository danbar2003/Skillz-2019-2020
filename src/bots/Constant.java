package bots;

import bots.missions.Mission;
import bots.wrapper.*;
import penguin_game.*;
import java.util.*;

public class Constant {

    public static class Icebergs {
        public static List<MyIceberg> myIcebergs;
        public static List<MyIceberg> enemyIcebergs;
        public static List<MyIceberg> neutralIcebergs;
        public static List<MyIceberg> allIcebergs;
        public static List<MyIceberg> notMyIcebergs;
        public static List<MyIceberg> myAvailableIcebergs;
    }

    public static class PenguinGroups {
        public static List<MyPenguinGroup> myPenguinGroups;
        public static List<MyPenguinGroup> enemyPenguinGroups;
        public static List<MyPenguinGroup> allPenguinGroup;
    }

    public static class Game {
        public static int turn;
        public static int maxTurns;
        public static int turnsLeft;
    }

    public static class Players{
        public static Player[] allPlayers;
        public static Player enemyPlayer;
        public static Player mySelf;
        public static Player neutral;
    }

    public static class Groups {
        public static Set<Set<MyIceberg>> allMyIcebergGroups;
        public static Set<Mission> allMissions;
        public static Set<Set<Mission>> allMissionGroups;
    }

}
