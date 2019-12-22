package bots.wrapper;

import penguin_game.Game;
import penguin_game.Iceberg;
import penguin_game.PenguinGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyGame {
    public static Game game;
    public static int turn;

    //icebergs
    public static Iceberg[] myIcebergs;
    public static Iceberg[] enemyIcebergs;
    public static Iceberg[] allIcebergs;
    public static Iceberg[] neutralIcebergs;
    public static List<Iceberg> notMyIcebergs;


    //penguin_groups
    public static PenguinGroup[] myPenguinGroups;
    public static PenguinGroup[] enemyPenguinGroups;
    public static PenguinGroup[] allPenguinGroups;

    public static void init(Game game) {
        MyGame.game = game;
    }

    public static void update(Game game) {
        MyGame.turn = game.turn;

        MyGame.myIcebergs = game.getMyIcebergs();
        MyGame.enemyIcebergs = game.getEnemyIcebergs();
        MyGame.allIcebergs = game.getAllIcebergs();
        MyGame.neutralIcebergs = game.getNeutralIcebergs();
        MyGame.notMyIcebergs = Arrays.asList(enemyIcebergs);
        notMyIcebergs.addAll(Arrays.asList(neutralIcebergs));

        MyGame.myPenguinGroups = game.getMyPenguinGroups();
        MyGame.enemyPenguinGroups = game.getEnemyPenguinGroups();
        MyGame.allPenguinGroups = game.getAllPenguinGroups();
    }
}
