package bots;
import penguin_game.*;

public class Modes {

    public static void defensiveMode(Game game, Iceberg needsHelp){

    }

    public static void offensiveMode(Game game, Iceberg myIceberg){
        if (Utils.minimumPenguinAmountToWin(game, myIceberg, Utils.weakestIceBerg(game.getEnemyIcebergs())) < myIceberg.penguinAmount)
            Utils.attackWeakest(game, myIceberg);
    }

    public static void upgradeMode(Game game, Iceberg myIceberg){


    }
}


