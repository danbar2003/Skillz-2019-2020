package bots;

import bots.wrapper.*;
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
    }

    public static class IcebergGroups {
        public static Set<Set<MyIceberg>> allMyIcebergGroups;
    }
}
