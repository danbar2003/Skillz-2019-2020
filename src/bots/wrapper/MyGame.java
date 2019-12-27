package bots.wrapper;

import penguin_game.Game;
import penguin_game.Iceberg;
import penguin_game.PenguinGroup;

import java.util.LinkedList;
import java.util.List;

public class MyGame {
    private final Game game;

    public MyGame(Game game) {
        this.game = game;
    }

    //MyIcebergs
    public List<MyIceberg> getMyIcebergs() {
        return convertToMyIcebergType(game.getMyIcebergs());
    }

    public List<MyIceberg> getEnemyIcebergs() {
        return convertToMyIcebergType(game.getEnemyIcebergs());
    }

    public List<MyIceberg> getNeutralIcebergs(){
        return convertToMyIcebergType(game.getNeutralIcebergs());
    }

    public List<MyIceberg> getAllIcebergs() {
        return convertToMyIcebergType(game.getAllIcebergs());
    }

    private List<MyIceberg> convertToMyIcebergType(Iceberg[] arr) {
        LinkedList<MyIceberg> myIcebergs = new LinkedList<>();
        for (Iceberg iceberg : arr) {
            myIcebergs.add(new MyIceberg(iceberg));
        }
        return myIcebergs;
    }
}
