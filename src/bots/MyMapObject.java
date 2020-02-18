package bots;

import penguin_game.MapObject;

import java.util.List;


public class MyMapObject {

    public final MapObject mapObject;

    public MyMapObject(MapObject mapObject){
        this.mapObject = mapObject;
    }

    public <T extends MyMapObject> T closestTo(List<T> arr) {
        if (arr.size() > 0) {
            T obj = arr.get(0);
            int minDistance = mapObject.__distance(obj.mapObject);
            for (T temp : arr) {
                if (mapObject.__distance(temp.mapObject) < minDistance) {
                    minDistance = mapObject.__distance(temp.mapObject);
                    obj = temp;
                }
            }
            return obj;
        }
        return null;
    }
}
