package bots.wrapper;

import java.util.*;

import bots.Utils;
import penguin_game.*;


public class MyIceberg {
    public Iceberg iceberg;

    public MyIceberg(Iceberg iceberg) {
        this.iceberg = iceberg;
    }

    public boolean canDefendItself(Game game) {
        List<PenguinGroup> penguinGroups = Arrays.asList(game.getEnemyPenguinGroups());
        int penguinAmount = iceberg.penguinAmount;
        PenguinGroup closestEnemyPenguinGroup = Utils.closestTo(iceberg, penguinGroups);
        for (int i = 0; i < penguinGroups.size(); i++) {
            if (closestEnemyPenguinGroup.penguinAmount - (
                    closestEnemyPenguinGroup.turnsTillArrival * iceberg.penguinsPerTurn + penguinAmount) < 0) {
                return false;
            }
            penguinGroups.remove(Utils.closestTo(iceberg, penguinGroups));
            penguinAmount = penguinAmount + closestEnemyPenguinGroup.turnsTillArrival * iceberg.penguinsPerTurn
                    - closestEnemyPenguinGroup.penguinAmount;
        }
        return true;
    }

    public <T extends GameObject> T closestTo(T[] arr) {
        if (arr.length > 0) {
            int min = this.iceberg.__distance(arr[0]);
            T obj = arr[0];
            for (T temp : arr) {
                if (this.iceberg.__distance(temp) < min) {
                    obj = temp;
                    min = this.iceberg.__distance(temp);
                }
            }
            return obj;
        }
        return null;
    }
}
