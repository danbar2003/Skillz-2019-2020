package bots;

import bots.missions.*;
import bots.wrapper.*;
import penguin_game.*;

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
        MyGame.updateGame(game);
        handleIcebergs();
    }
    
    private void handleIcebergs() {
        Utils.setupIcebergPenguins(); //update saved penguins for each iceberg
        /*
        Dictionary of iceberg and List of missions in the execute order/priority.
        (If one mission doesn't execute, it will choose the next Mission in the list)
        */
        Map<MyIceberg, List<Mission>> icebergsMissions = MissionManager.createMissionsForIcebergs();
        //iterating through every iceberg and executing his mission.
        for (MyIceberg iceberg : icebergsMissions.keySet())
            for (int i = 0; i < icebergsMissions.get(iceberg).size(); i ++)
                if (icebergsMissions.get(iceberg).get(i).act(iceberg)) // if acted, do not execute the next mission
                    break;
    }

}
