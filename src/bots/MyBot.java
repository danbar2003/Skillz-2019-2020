package bots;

import bots.wrapper.*;
import penguin_game.*;
import java.util.*;


/**
 * This is an example for a bot.
 */
public class MyBot implements SkillzBot {


    static boolean debug = false;
    /**
     * Makes the bot run a single turn.
     *
     * @param game - the current game state.
     */
    @Override
    public void doTurn(Game game) {

        MyGame.updateGame(game);
        handleIcebergs();
    }
    
    private void handleIcebergs() {
        Utils.setupIcebergPenguins(); //update saved penguins for each iceberg
    }
}
