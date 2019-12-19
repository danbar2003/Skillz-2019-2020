package bots;

import penguin_game.*;

public class Utils {

    public static <T extends MapObject> T closestTo(MapObject object, T[] arr) {
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
}