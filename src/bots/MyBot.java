package bots;

import penguin_game.*;


/**
 * This is an example for a bot.
 */
public class MyBot implements SkillzBot {
    /**
     * Makes the bot run a single turn.
     *
     * @param game - the current game state.
     */
    @Override
    public void doTurn(Game game) {
//
        for (Iceberg myIceberg : game.getMyIcebergs()) {

            // The amount of penguins in my iceberg.
            int myPenguinAmount = myIceberg.penguinAmount;

            // Initializing the iceberg we want to send penguins to.
            Iceberg destination;

            // If there are any neutral icebergs.
            if (game.getNeutralIcebergs().length > 0) {
                // Target a neutral iceberg.
                destination = game.getNeutralIcebergs()[0];
            } else {
                // Target an enemy iceberg.
                destination = game.getEnemyIcebergs()[0];
            }

            // The amount of penguins the target has.
            int destinationPenguinAmount = destination.penguinAmount;
            // If my iceberg has more penguins than the target iceberg.
            if (myPenguinAmount > destinationPenguinAmount) {
                // Send penguins to the target.
                System.out.println(myIceberg + " sends " + (destinationPenguinAmount + 1) + " penguins to " + destination.id);
                myIceberg.sendPenguins(destination, destinationPenguinAmount + 1);
            }
        }
    }
}
