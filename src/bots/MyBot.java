package bots;

import penguin_game.Game;
import penguin_game.SkillzBot;


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

        long start = System.currentTimeMillis();
        MyGame.updateGame(game);
        Utils.updateActiveMissions();
        Utils.missionCalculation();
        handleIcebergs();
        System.out.println("turn time: " + (System.currentTimeMillis() - start) );
    }
    
    private void handleIcebergs() {
        Utils.setupIcebergPenguins(); //update saved penguins for each iceberg
        for(MyIceberg iceberg: Constant.Icebergs.myIcebergs)
            System.out.println(  iceberg.iceberg + " free penguins"+ iceberg.getFreePenguins());
        for (Taskable task : MissionManager.createTasksForIcebergs())
            task.act();
    }
}
