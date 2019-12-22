package bots;

import bots.wrapper.*;
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
        handleIcebergs(game);
    }

    private void handleIcebergs(Game game) {
        for (Iceberg myIceberg : game.getMyIcebergs()){
            Protocol protocol = MissionManager.createIcebergMission(myIceberg);
            protocol.act(game, myIceberg);
        }
    }
}

