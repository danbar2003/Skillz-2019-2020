package bots;

import bots.missions.Mission;
import penguin_game.Game;
import penguin_game.Iceberg;
import penguin_game.SkillzBot;

import java.util.List;
import java.util.Map;

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
        /*
        Dictionary of iceberg and List of missions in the execute order/priority.
        (If one mission doesn't execute, it will choose the next Mission in the list)
        */
        Map<Iceberg, List<Mission>> icebergsMissions = MissionManager.createMissionsForIcebergs(game);

        //iterating through every iceberg and executing his mission.
        for (Iceberg iceberg : icebergsMissions.keySet()) {
            for (int i = 0; i < icebergsMissions.get(iceberg).size(); i ++){
                if (icebergsMissions.get(iceberg).get(i).act(game, iceberg)){
                    break;
                }
            }
        }
    }
}

