package bots;

import bots.wrapper.MyGame;
import javafx.scene.control.IndexedCell;
import penguin_game.*;

public class Utils {
    /*TODO someone create the functions with MyGame obj (see example)
    * if the original function got a Game as a parameter, rewrite the func
    * using MyGame.
    *
    * Example:
    *
    * this original func gets a Game as a parameter, rewrite the function but using
    * func signature amountOfPenguinsComing(Iceberg myIceberg) (without receiving game)
    *
    * public static int amountOfPenguinsComing(Game game, Iceberg myIceberg) {
        int penguinAmount = 0;
        for (PenguinGroup temp : game.getEnemyPenguinGroups()) {
            if (temp.destination == myIceberg)
                penguinAmount += temp.penguinAmount;
        }
        return penguinAmount;
    }
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

    //shel Daniel
    public static int minPenguinsToWinTemp(Iceberg attacker, Iceberg target) {
        int comingPenguins = 0;
        for (PenguinGroup penguinGroup : MyGame.enemyPenguinGroups) {
            if (penguinGroup.destination == target && penguinGroup.turnsTillArrival > attacker.getTurnsTillArrival(target)) {
                comingPenguins += penguinGroup.penguinAmount;
            }
        }
        return target.penguinAmount + target.penguinsPerTurn * attacker.getTurnsTillArrival(target) + comingPenguins + 1;
    }

    //shel Roi, Yuval
    public static int minPenguinsToWin(Iceberg attacker, Iceberg target) {
        return target.penguinAmount + target.penguinsPerTurn * attacker.getTurnsTillArrival(target) +
                friendlyPenguinsComing(target) + 1;
    }

    public static int friendlyPenguinsComing(Iceberg iceberg) {
        int penguinsComing = 0;
        if (iceberg.owner.equals(MyGame.myIcebergs[0].owner)) {
            for (PenguinGroup penguinGroup : MyGame.myPenguinGroups) {
                if (penguinGroup.destination == iceberg)
                    penguinsComing += penguinGroup.penguinAmount;
            }
            return penguinsComing;
        }
        for (PenguinGroup penguinGroup : MyGame.enemyPenguinGroups) {
            if (penguinGroup.destination == iceberg)
                penguinsComing += penguinGroup.penguinAmount;
        }
        return penguinsComing;
    }


}