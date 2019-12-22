package bots;

import penguin_game.Game;
import penguin_game.Iceberg;
import penguin_game.SkillzBot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


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
//        array = Utils.removeNullFromArray(array);
        handleIcebergs(game);
    }

    private void handleIcebergs(Game game) {
        List<Iceberg> notOurs = new LinkedList<Iceberg>(Arrays.asList(game.getEnemyIcebergs()));
        notOurs.addAll(Arrays.asList(game.getNeutralIcebergs()));



        for (Iceberg myIceberg : game.getMyIcebergs()) {

            //defence
            /*
             if the minimum amount of penguins to win the fight with the weakest enemy iceberg is lower than
             the amount of penguins we have, we will use fire power nd destroy the shit out of them.
            */
            //attack

            if (Utils.minimumPenguinAmountToWin(game, myIceberg, Utils.weakestIceBerg(notOurs.toArray(new Iceberg[]{}))) < myIceberg.penguinAmount) {
                System.out.println("hi");
                Modes.offensiveMode(game, myIceberg);
            }
        }
    }
}

