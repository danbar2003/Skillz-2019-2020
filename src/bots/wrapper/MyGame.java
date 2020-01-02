package bots.wrapper;

import bots.Utils;
import penguin_game.Game;
import penguin_game.Iceberg;
import penguin_game.PenguinGroup;

import java.util.LinkedList;
import java.util.List;

public class MyGame {
    public final Game game;

    public MyGame(Game game) {
        this.game = game;
    }

    //MyIcebergs
    public List<MyIceberg> getMyIcebergs() {
        return Utils.convertToMyIcebergType(game.getMyIcebergs());
    }

    public List<MyIceberg> getEnemyIcebergs() {
        return Utils.convertToMyIcebergType(game.getEnemyIcebergs());
    }

    public List<MyIceberg> getNeutralIcebergs(){
        return Utils.convertToMyIcebergType(game.getNeutralIcebergs());
    }

    public List<MyIceberg> getAllIcebergs() {
        return Utils.convertToMyIcebergType(game.getAllIcebergs());
    }

}
