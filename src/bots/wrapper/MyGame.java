package bots.wrapper;

import penguin_game.*;

public class MyGame {
    public static Game game;
    public static int turn;

    //icebergs
    public static Iceberg[] myIcebergs;
    public static Iceberg[] enemyIcebergs;
    public static Iceberg[] allIcebergs;
    public static Iceberg[] neutralIcebergs;
    //TODO create an notFriendlyIcebergs Iceberg array

    //penguin_groups
    public static PenguinGroup[] myPenguinGroups;
    public static PenguinGroup[] enemyPenguinGroups;
    public static PenguinGroup[] allPenguinGroups;

    public static void init(Game game){
        MyGame.game = game;
    }

    public static void update(Game game){
        MyGame.turn = game.turn;

        MyGame.myIcebergs = game.getMyIcebergs();
        MyGame.enemyIcebergs = game.getEnemyIcebergs();
        MyGame.allIcebergs = game.getAllIcebergs();
        MyGame.neutralIcebergs = game.getNeutralIcebergs();

        MyGame.myPenguinGroups = game.getMyPenguinGroups();
        MyGame.enemyPenguinGroups = game.getEnemyPenguinGroups();
        MyGame.allPenguinGroups = game.getAllPenguinGroups();
    }
}
