package bots.wrapper;

import bots.*;
import penguin_game.Game;

import java.util.LinkedList;

public class MyGame {

    public static Game game;

    public static void updateGame(Game game) {
        MyGame.game = game;

        Constant.Icebergs.myIcebergs = new LinkedList<>(Utils.convertToMyIcebergType(game.getMyIcebergs()));
        Constant.Icebergs.enemyIcebergs = new LinkedList<>(Utils.convertToMyIcebergType(game.getEnemyIcebergs()));
        Constant.Icebergs.neutralIcebergs = new LinkedList<>(Utils.convertToMyIcebergType(game.getNeutralIcebergs()));
        Constant.Icebergs.allIcebergs = new LinkedList<>(Utils.convertToMyIcebergType(game.getAllIcebergs()));
        Constant.Icebergs.notMyIcebergs = Utils.getNotMyIcebergs();
        Constant.Icebergs.myAvailableIcebergs = Utils.getMyAvailableIcebergs();

        Constant.Game.maxTurns = game.maxTurns;
        Constant.Game.turn = game.turn;
        Constant.Game.turnsLeft = Constant.Game.maxTurns - Constant.Game.turn;

        Constant.PenguinGroups.allPenguinGroup = new LinkedList<>(Utils.convertToMyPenguinGroupType(game.getAllPenguinGroups()));
        Constant.PenguinGroups.myPenguinGroups = new LinkedList<>(Utils.convertToMyPenguinGroupType(game.getMyPenguinGroups()));
        Constant.PenguinGroups.enemyPenguinGroups = new LinkedList<>(Utils.convertToMyPenguinGroupType(game.getEnemyPenguinGroups()));

        Constant.Players.mySelf = game.getMyself();
        Constant.Players.enemyPlayer = game.getEnemy();
        Constant.Players.neutral = game.getNeutral();
        Constant.Players.allPlayers = game.getAllPlayers();

        Constant.Groups.allMyIcebergGroups = Utils.powerSet(Constant.Icebergs.myIcebergs, 4);
        Constant.Groups.allMissions = MissionManager.allMissions();
        Constant.Groups.allMissionGroups = MissionManager.allMissionGroups(2);
    }
}
