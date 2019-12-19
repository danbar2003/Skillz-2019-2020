package bots;

import penguin_game.*;

public class Modes {

    public static void defensiveMode(Game game, Iceberg needsHelp) {

    }

    public static void offensiveMode(Game game, Iceberg myIceberg) {

        // The amount of penguins in my iceberg.
        int myPenguinAmount = myIceberg.penguinAmount;

        // Initializing the iceberg we want to send penguins to.
        Iceberg destination = null;

        // If there are any neutral icebergs.
        if (game.getNeutralIcebergs().length > 0) {
            // Target a neutral iceberg.
            destination = Utils.closestTo(myIceberg, game.getNeutralIcebergs());
        } else {
            // Target an enemy iceberg.
            destination = game.getEnemyIcebergs()[0];
        }

        // The amount of penguins the target has.
        int destinationPenguinAmount = destination.penguinAmount;
        // If my iceberg has more penguins than the target iceberg.
        if (myPenguinAmount > destinationPenguinAmount) {
            // Send penguins to the target.
            System.out.println(myIceberg + " sends " + (destinationPenguinAmount + 1) + " penguins to " + destination);
            myIceberg.sendPenguins(destination, destinationPenguinAmount + 1);
        }
    }
}


