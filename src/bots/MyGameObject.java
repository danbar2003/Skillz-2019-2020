package bots;

import penguin_game.GameObject;
import penguin_game.MapObject;

import java.util.List;

public abstract class MyGameObject extends MyMapObject {

    public final GameObject gameObject;

    public MyGameObject(GameObject gameObject) {
        super(gameObject);
        this.gameObject = gameObject;
    }

    public <T extends MapObject> double avgDistance(List<T> arr){
        double distance = 0;
        for (T obj : arr){
            distance += this.gameObject.__distance(obj);
        }
        return (distance / arr.size());
    }
}
