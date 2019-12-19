package bots;

import penguin_game.Game;
import penguin_game.Iceberg;
import penguin_game.SkillzBot;


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
        handleIcebergs(game);
    }

    private void handleIcebergs(Game game) {
        for (Iceberg myIceberg : game.getMyIcebergs()) {
            /*
             if the minimum amount of penguins to win the fight with the weakest enemy iceberg is lower than
             the amount of penguins we have, we will use fire power nd destroy the shit out of them.
            */
            //attack
            if (Utils.minimumPenguinAmountToWin(game, myIceberg, Utils.weakestIceBerg(game.getEnemyIcebergs())) <= myIceberg.penguinAmount) {
                Modes.offensiveMode(game, myIceberg);
            }

        }
    }
}

