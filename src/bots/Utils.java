package bots;

import penguin_game.*;

public class Utils {
    public static Game game;
    /**
     *
     * @param object: Some object in the game.
     * @param arr: Array of objects (doesn't have to be the same type)
     * @param <T> :nothing.
     * @return : return the closest object from arr to object parameter, or null if arr length is below 1
     */
    public static <T extends GameObject> T closestTo(GameObject object, T[] arr) {
        if (arr.length > 0) {
            int distance = object.__distance(arr[0]);
            T closestObj = arr[0];
            for (T temp : arr) {
                if (temp.__distance(object) < distance) {
                    distance = temp.__distance(object);
                    closestObj = temp;
                }
            }
            return closestObj;
        }
        return null;
    }

    public static int howManyInRange(GameObject GameObject, GameObject[] objects,int range){
        int counter = 0;
        for (GameObject temp: objects) {
            if (GameObject.__distance(temp) <= range)
                counter++;
        }
        return counter;
    }

    public static void attack(Iceberg iceberg){
       Iceberg closestBerg = closestTo(iceberg, game.getEnemyIcebergs());

        }
    }
}