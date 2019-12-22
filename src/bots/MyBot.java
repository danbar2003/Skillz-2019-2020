package bots;

import bots.missions.Mission;
import bots.wrapper.MyIceberg;
import penguin_game.*;

import java.util.HashSet;
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
        /*
        Dictionary of iceberg and List of missions in the execute order/priority.
        (If one mission doesn't execute, it will choose the next Mission in the list)
        */
        Map<Iceberg, List<Mission>> icebergsMissions = MissionManager.createMissionsForIcebergs(game);

        for (Iceberg iceberg : icebergsMissions.keySet()){
            //iterating through every iceberg and executing his mission.
        }
    }
}

