package bots;


import bots.missions.Mission;
import bots.wrapper.MyIceberg;
import bots.wrapper.MyPenguinGroup;
import penguin_game.Iceberg;
import penguin_game.PenguinGroup;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Utils {

    public static List<MyIceberg> convertToMyIcebergType(Iceberg[] arr) {
        LinkedList<MyIceberg> myIcebergs = new LinkedList<>();
        for (Iceberg iceberg : arr) {
            myIcebergs.add(new MyIceberg(iceberg));
        }
        return myIcebergs;
    }

    public static List<MyPenguinGroup> convertToMyPenguinGroupType(PenguinGroup[] arr) {
        LinkedList<MyPenguinGroup> myPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : arr) {
            myPenguinGroups.add(new MyPenguinGroup(penguinGroup));
        }
        return myPenguinGroups;
    }

    public static List<MyIceberg> getNotMyIcebergs() {
        List<MyIceberg> notMyIcebergs = Constant.Icebergs.allIcebergs;
        notMyIcebergs.removeAll(Constant.Icebergs.myIcebergs);
        return notMyIcebergs;
    }

    public static void setupIcebergPenguins() {
        for (MyIceberg iceberg : Constant.Icebergs.myIcebergs) {
            iceberg.savePenguins(iceberg.amountToDefend());
        }
    }

    public static void missionCalculation() {
        for (Mission mission : Constant.Groups.allMissions)
            mission.calcWaysToExecute();
    }

    static int[] nextSet(int[] a, int m) {
        int n = a.length;
        int[] currentSet = new int[m];
        for (int k = 0; k < m; k++)
            currentSet[k] = a[k];
        for (int i = m - 1; i >= 0; --i) {
            if (a[i] < n - m + i) {
                a[i]++;
                for (int j = i + 1; j < m; ++j)
                    a[j] = a[j - 1] + 1;
                return currentSet;


            }

        }
        return currentSet;

    }

    static int numOfComb(int n, int m) {
        int k = n - m;
        if (m > k)
            m = k;
        if (m == 0)
            return 1;
        int akk1 = k = n + 1 - m, akk2 = 1;
        k++;
        for (int i = 2; i <= m; i++, k++) {
            akk1 *= k;
            akk2 *= i;
        }
        return akk1 / akk2;
    }

    public static <T> Set<Set<T>> almostPowerSet(List<T> original, int length) {
        int[] a = new int[original.size()];
        for (int i = 0; i < original.size(); i++)
            a[i] = i;
        int combs = numOfComb(original.size(), length);
        int[][] combinations = new int[combs][length];
        for (int i = 0; i < combs; i++) {
            combinations[i] = nextSet(a, length);
        }
        Set<Set<T>> allGroups = new HashSet<>();
        for (int i = 0; i < combs; i++) {
            Set<T> innerSet = new HashSet<>();
            for (int j = 0; j < length; j++) {
                innerSet.add(original.get(combinations[i][j]));
            }
            allGroups.add(innerSet);
        }
        return allGroups;
    }

    public static <T> Set<Set<T>> powerSet(Set<T> original, int length) {
        Set<Set<T>> allGroups = new HashSet<>();
        List<T> originalSet = new LinkedList<>();
        originalSet.addAll(original);
        if (length > original.size())
            length = original.size();
        for (int i = 1; i <= length; i++) {
            allGroups.addAll(almostPowerSet(originalSet, i));
        }
        return allGroups;
    }

    public static void updateActiveMissions() {
        System.out.println(MissionManager.activeMissions);
        for (Mission mission : MissionManager.activeMissions.keySet()) {
            if (MissionManager.activeMissions.get(mission) == 0) {
                MissionManager.activeMissions.remove(mission);
                continue;
            }
            MissionManager.activeMissions.put(mission, MissionManager.activeMissions.get(mission) - 1);
        }
    }
}