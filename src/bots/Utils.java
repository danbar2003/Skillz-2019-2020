
package bots;

import bots.missions.AttackWeakestIceberg;
import bots.missions.Mission;
import bots.missions.UpgradeIceberg;
import haxe.root.Array;
import penguin_game.*;

import java.util.*;


public class Utils {

    /**
     * @param object is the object you want to find the
     * @param arr    is an array that you want to find the closest object to "object" from.
     * @return the closest object to "object" from the array
     */
    public static <T extends GameObject> T closestTo(GameObject object, T[] arr) {
        if (arr.length > 0) {
            T obj = arr[0];
            int minDistance = object.__distance(obj);
            for (T temp : arr) {
                if (object.__distance(temp) < minDistance) {
                    minDistance = object.__distance(temp);
                    obj = temp;
                }
            }
            return obj;
        }
        return null;
    }

    public static <T extends GameObject> T closestTo(GameObject object, List<T> arr) {
        if (arr.size() > 0) {
            T obj = arr.get(0);
            int minDistance = object.__distance(obj);
            for (T temp : arr) {
                if (object.__distance(temp) < minDistance) {
                    minDistance = object.__distance(temp);
                    obj = temp;
                }
            }
            return obj;
        }
        return null;
    }

    /**
     * @param iceberg can be an enemy iceberg or a neutral iceberg
     * @return the enemy's penguinGroups that their destination is "iceberg"
     */
    public static List<PenguinGroup> getHelpingPenguinGroupsToIceberg(Game game, Iceberg iceberg) {
        List<PenguinGroup> penguinGroups = new LinkedList<>();
        if (iceberg.owner == game.getMyIcebergs()[0].owner) {
            for (PenguinGroup penguinGroup : game.getMyPenguinGroups()) {
                if (penguinGroup.destination == iceberg)
                    penguinGroups.add(penguinGroup);
            }
            return penguinGroups;
        }
        for (PenguinGroup penguinGroup : game.getEnemyPenguinGroups()) {
            if (penguinGroup.destination == iceberg)
                penguinGroups.add(penguinGroup);
        }
        return penguinGroups;
    }

    //find better name ty.
    public static List<PenguinGroup> getAttackingPenguinGroupsToIceberg(Game game, Iceberg iceberg) {
        List<PenguinGroup> penguinGroups = new LinkedList<>();
        if (iceberg.owner == game.getMyIcebergs()[0].owner) {
            for (PenguinGroup penguinGroup : game.getEnemyPenguinGroups()) {
                if (penguinGroup.destination == iceberg)
                    penguinGroups.add(penguinGroup);
            }
            return penguinGroups;
        }
        for (PenguinGroup penguinGroup : game.getMyPenguinGroups()) {
            if (penguinGroup.destination == iceberg)
                penguinGroups.add(penguinGroup);
        }
        return penguinGroups;
    }

    /**
     * @param attacker - the attacker
     * @param target   - the target
     * @return the amount of penguins the attacker needs to send to the target.
     */
    public static int minPenguinAmountToWin(Game game, Iceberg attacker, Iceberg target) {
        int penguinAmount = target.penguinAmount +
                target.penguinsPerTurn * attacker.getTurnsTillArrival(target);
        List<PenguinGroup> helpers = getHelpingPenguinGroupsToIceberg(game, target);
        if (!helpers.isEmpty()) {
            for (PenguinGroup helper : helpers) {
                if (helper.turnsTillArrival < attacker.getTurnsTillArrival(target)) {
                    penguinAmount += helper.penguinAmount;
                }
            }
        }
        return penguinAmount;
    }
    //TODO create a function that returns Map of Group Missions (more than one Iceberg) as keys and which icebergs can execute them as values.
}