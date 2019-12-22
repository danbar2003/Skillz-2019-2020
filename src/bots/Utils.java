package bots;

import penguin_game.*;

import java.util.LinkedList;
import java.util.List;


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


    /**
     * @param iceberg can be an enemy iceberg or a neutral iceberg
     * @return the enemy's penguinGroups that their destination is "iceberg"
     */
    public static List<PenguinGroup> getHelpingPenguinGroupsToIceberg(Game game, Iceberg iceberg) {
        iasd
    }

    /**
     * @param attacker    - the attacker
     * @param target - the target
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

}