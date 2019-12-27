package bots.wrapper;

import penguin_game.GameObject;
import penguin_game.MapObject;

import java.util.List;

public class MyGameObject {
    public final GameObject gameObject;

    public MyGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public <T extends MapObject> T closestTo(T[] arr) {
        if (arr.length > 0) {
            T obj = arr[0];
            int minDistance = this.gameObject.__distance(obj);
            for (T temp : arr) {
                if (this.gameObject.__distance(temp) < minDistance) {
                    minDistance = this.gameObject.__distance(temp);
                    obj = temp;
                }
            }
            return obj;
        }
        return null;
    }

    public <T extends MapObject> T closestTo(List<T> arr) {
        if (arr.size() > 0) {
            T obj = arr.get(0);
            int minDistance = this.gameObject.__distance(obj);
            for (T temp : arr) {
                if (this.gameObject.__distance(temp) < minDistance) {
                    minDistance = this.gameObject.__distance(temp);
                    obj = temp;
                }
            }
            return obj;
        }
        return null;
    }
}
