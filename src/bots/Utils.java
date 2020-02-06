package bots;


import bots.missions.Mission;
import bots.tasks.AdvancedNode;
import bots.tasks.TaskGroup;
import bots.wrapper.*;
import penguin_game.*;

import java.util.*;

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

    public static List<MyIceberg> myThreatenedIcebergs() {
        List<MyIceberg> threatenedIcebergs = new LinkedList<>();
        for (MyIceberg iceberg : Constant.Icebergs.myIcebergs) {
            if (iceberg.amountToDefend() <= 0)
                threatenedIcebergs.add(iceberg);
        }
        return threatenedIcebergs;
    }

   public static List<MyIceberg> getNotMyIcebergs(){
        List<MyIceberg> notMyIcebergs = Constant.Icebergs.allIcebergs;
        notMyIcebergs.removeAll(Constant.Icebergs.myIcebergs);
        return notMyIcebergs;
   }

   public static List<MyIceberg> getMyAvailableIcebergs(){
        List<MyIceberg> myAvailableIcebergs = Constant.Icebergs.myIcebergs;
        myAvailableIcebergs.removeAll(myThreatenedIcebergs());
        return myAvailableIcebergs;
   }

   public static void setupIcebergPenguins() {
        for (MyIceberg iceberg : Constant.Icebergs.myIcebergs) {
            iceberg.savePenguins(iceberg.amountToDefend());
        }
    }

    public static void missionCalculation(){
        for (Mission mission : Constant.Groups.allMissions)
            mission.calcWaysToExecute();
    }

    static int[] nextSet(int[] a, int m) {
        int n = a.length;
        int[] currentSet = new int[m];
        for (int k = 0; k < m; k++)
            currentSet[k] = a[k];
        for (int i = m - 1; i >= 0; --i) {
            if (a[i] < n - m + i ) {
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

    public static <T> Set<Set<T>> almostPowerSet(List<T> original, int length){
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

    public static <T> Set<Set<T>> powerSet(Set<T> original, int length){
        Set<Set<T>> allGroups = new HashSet<>();
        List<T> originalSet = new LinkedList<>();
        originalSet.addAll(original);
        for (int i = 1; i <= length ; i++) {
            allGroups.addAll(almostPowerSet(originalSet, i));
        }
        return allGroups;
    }

}