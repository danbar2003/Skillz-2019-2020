package bots.wrapper;

import bots.*;
import penguin_game.Game;

import java.util.Arrays;
import java.util.LinkedList;

public class MyGame {

    public static Game game;

    public static void updateGame(Game game) {
        MyGame.game = game;
        Constant.Icebergs.myIcebergs = new LinkedList<>(Utils.convertToMyIcebergType(game.getMyIcebergs()));
        Constant.Icebergs.enemyIcebergs = new LinkedList<>(Utils.convertToMyIcebergType(game.getEnemyIcebergs()));
        Constant.Icebergs.neutralIcebergs = new LinkedList<>(Utils.convertToMyIcebergType(game.getNeutralIcebergs()));
        Constant.Icebergs.allIcebergs = new LinkedList<>(Utils.convertToMyIcebergType(game.getAllIcebergs()));

        Constant.Game.maxTurns = game.maxTurns;
        Constant.Game.turn = game.turn;

        Constant.PenguinGroups.allPenguinGroup = new LinkedList<>(Utils.convertToMyPenguinGroupType(game.getAllPenguinGroups()));
        Constant.PenguinGroups.myPenguinGroups = new LinkedList<>(Utils.convertToMyPenguinGroupType(game.getMyPenguinGroups()));
        Constant.PenguinGroups.enemyPenguinGroups = new LinkedList<>(Utils.convertToMyPenguinGroupType(game.getEnemyPenguinGroups()));

        Constant.Players.mySelf = game.getMyself();
        Constant.Players.enemyPlayer = game.getEnemy();
        Constant.Players.neutral = game.getNeutral();
        Constant.Players.allPlayers = game.getAllPlayers();
    }
}
