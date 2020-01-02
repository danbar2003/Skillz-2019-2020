package bots.wrapper;

import penguin_game.Iceberg;
import penguin_game.PenguinGroup;
import penguin_game.Player;

import java.util.LinkedList;
import java.util.List;

public class MyIceberg extends MyGameObject {

    public Iceberg iceberg;
    private int savedPenguins;

    public MyIceberg(Iceberg iceberg) {
        super(iceberg);
        this.iceberg = iceberg;
        this.savedPenguins = 0;

    }

    public void savePenguins(int penguinAmount) {
        if (penguinAmount <= 0)
            this.savedPenguins = penguinAmount;
    }

    public int getSavedPenguins() {
        return this.savedPenguins;
    }

    public int getFreePenguins() {
        return (iceberg.penguinAmount - getSavedPenguins());
    }

    /**
     * @param game
     * @param iceberg - iceberg sending the penguins
     * @return -penguin amount coming
     */
    public int getPenguinsComingFromIceberg(MyGame game, MyIceberg iceberg) {
        int penguinAmountFromIceberg = 0;
        for (PenguinGroup penguinGroup : iceberg.getFriendlyPenguinGroupsToIceberg(game)) {
            if (penguinGroup.source == iceberg.iceberg && penguinGroup.destination == this.iceberg) {
                penguinAmountFromIceberg += penguinGroup.penguinAmount;
            }
        }
        return penguinAmountFromIceberg;
    }

    /**
     * @param game
     * @return
     */
    private List<PenguinGroup> getFriendlyPenguinGroupsToIceberg(MyGame game) {
        List<PenguinGroup> friendlyPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : game.game.getAllPenguinGroups()) {
            if (penguinGroup.owner == this.gameObject.owner)
                friendlyPenguinGroups.add(penguinGroup);
        }
        return friendlyPenguinGroups;
    }

    /**
     * @param game
     * @return
     */
    private List<PenguinGroup> getEnemyPenguinGroupsToIceberg(MyGame game) {
        List<PenguinGroup> enemyPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : game.game.getAllPenguinGroups()) {
            if (penguinGroup.owner != this.gameObject.owner)
                enemyPenguinGroups.add(penguinGroup);
        }
        return enemyPenguinGroups;
    }

    /**
     * @param game - game info
     * @return - list of coming penguin groups
     */
    public List<PenguinGroup> allComingPenguinGroups(MyGame game) {
        List<PenguinGroup> comingPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : game.game.getAllPenguinGroups())
            if (penguinGroup.destination == this.iceberg)
                comingPenguinGroups.add(penguinGroup);
        return comingPenguinGroups;
    }

    /**
     * @param game
     * @return
     */
    public List<PenguinGroup> getHelpingPenguinGroupsToIceberg(MyGame game) {
        List<PenguinGroup> friendlyPenguinGroups = getFriendlyPenguinGroupsToIceberg(game);
        for (PenguinGroup penguinGroup : friendlyPenguinGroups) {
            if (penguinGroup.destination == this.iceberg)
                friendlyPenguinGroups.add(penguinGroup);
        }
        return friendlyPenguinGroups;
    }

    /**
     * @param game
     * @return
     */
    public List<PenguinGroup> getAttackingPenguinGroupsToIceberg(MyGame game) {
        List<PenguinGroup> enemyPenguinGroups = getEnemyPenguinGroupsToIceberg(game);
        for (PenguinGroup penguinGroup : enemyPenguinGroups) {
            if (penguinGroup.destination == this.iceberg)
                enemyPenguinGroups.add(penguinGroup);
        }
        return enemyPenguinGroups;
    }

    /**
     * @param game
     * @param target
     * @return
     */
    public int minPenguinAmountToWin(MyGame game, MyIceberg target) {
        int penguinAmount = target.iceberg.penguinAmount +
                target.iceberg.penguinsPerTurn * iceberg.getTurnsTillArrival(target.iceberg);
        List<PenguinGroup> helpers = target.getHelpingPenguinGroupsToIceberg(game);
        for (PenguinGroup helper : helpers) {
            if (helper.turnsTillArrival < iceberg.getTurnsTillArrival(target.iceberg)) {
                penguinAmount += helper.penguinAmount;
            }
        }
        return penguinAmount + getPenguinsComingFromIceberg(game, target) + 1;
    }

    /**
     * @param game;
     * @return - penguin amount
     */
    public int amountToDefend(MyGame game) {
        List<PenguinGroup> comingPenguinGroups = allComingPenguinGroups(game);
        int penguinAmount = iceberg.penguinAmount;
        int previousTurnsTillArrival = 0;
        while (!comingPenguinGroups.isEmpty()) {
            PenguinGroup closestPenguinGroup = closestTo(comingPenguinGroups);
            if (closestPenguinGroup.owner.equals(iceberg.owner)) {
                penguinAmount += closestPenguinGroup.penguinAmount +
                        (closestPenguinGroup.turnsTillArrival - previousTurnsTillArrival) * iceberg.penguinsPerTurn;
            } else {
                penguinAmount += -closestPenguinGroup.penguinAmount +
                        (closestPenguinGroup.turnsTillArrival - previousTurnsTillArrival) * iceberg.penguinsPerTurn;
            }
        }
        return 0;
    }
}
