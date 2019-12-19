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
//        array = Utils.removeNullFromArray(array);
        handleIcebergs(game);
    }

    private void handleIcebergs(Game game) {
        for (Iceberg myIceberg : game.getMyIcebergs()) {

            }
        }
    }

