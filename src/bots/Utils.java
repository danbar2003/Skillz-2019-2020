package bots;

import penguin_game.Game;
import penguin_game.MapObject;

public class Utils {

    public static Game game;

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

    public static int howManyInRange(MapObject mapObject, MapObject[] objects, int range) {
        int counter = 0;
        for (MapObject temp : objects) {
            if (mapObject.__distance(temp) <= range)
                counter++;
        }
        return counter;
    }

}