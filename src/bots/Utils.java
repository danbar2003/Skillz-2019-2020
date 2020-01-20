package bots;


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

    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<>());
            return sets;
        }
        List<T> list = new ArrayList<>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
}