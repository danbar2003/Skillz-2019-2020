package bots;

import bots.missions.Mission;
import penguin_game.Game;
import penguin_game.Iceberg;
import penguin_game.SkillzBot;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

       //Dictionary of missions(keys) and sets of icebergs(values)
        Map<Mission, Set<Iceberg>> icebergsMissions;
        //iterating through every mission and executing the mission with every iceberg in the set.

    }
}

