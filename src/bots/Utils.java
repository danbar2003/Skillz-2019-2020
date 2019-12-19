package bots;

import bots.wrapper.MyGame;
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

    public static <T extends GameObject> T closestTo(MapObject object, T[] arr){
        if (arr.length > 0){
            T obj = arr[0];
            int minDistance = object.__distance(obj);
            for (T temp : arr) {
                if (object.__distance(temp) < minDistance){
                    minDistance = object.__distance(temp);
                    obj = temp;
                }
            }
            return obj;
        }
        return null;
    }
}