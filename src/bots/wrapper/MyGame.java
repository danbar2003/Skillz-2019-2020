package bots.wrapper;

import bots.Constant;
import bots.Utils;
import penguin_game.Game;

import java.util.Arrays;
import java.util.LinkedList;

public class MyGame {

    public static Game game;

    public static void updateGame(Game game) {
        MyGame.game = game;
        Constant.Icebergs.myIcebergs = new LinkedList<MyIceberg>(Utils.convertToMyIcebergType(game.getMyIcebergs()));
        Constant.Icebergs.enemyIcebergs = new LinkedList<MyIceberg>(Utils.convertToMyIcebergType(game.getEnemyIcebergs()));
        Constant.Icebergs.neutralIcebergs = new LinkedList<MyIceberg>(Utils.convertToMyIcebergType(game.getNeutralIcebergs()));
        Constant.Icebergs.allIcebergs = new LinkedList<MyIceberg>(Utils.convertToMyIcebergType(game.getAllIcebergs()));

        Constant.Game.maxTurns = game.maxTurns;
        Constant.Game.turn = game.turn;

        Constant.PenguinGroups.allPenguinGroup = new LinkedList<>(Arrays.asList(game.getAllPenguinGroups()));
        Constant.PenguinGroups.myPenguinGroups = new LinkedList<>(Arrays.asList(game.getMyPenguinGroups()));
        Constant.PenguinGroups.enemyPenguinGroups = new LinkedList<>(Arrays.asList(game.getEnemyPenguinGroups()));
    }
}
