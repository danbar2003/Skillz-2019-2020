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
        if (penguinAmount >= 0)
            this.savedPenguins = penguinAmount;
    }

    public int getSavedPenguins() {
        return this.savedPenguins;
    }

    public int getFreePenguins() {
        return (iceberg.penguinAmount - savedPenguins);
    }

    public void sendPenguins(MyIceberg target, int penguins){
        if (getFreePenguins() >= penguins){
            iceberg.sendPenguins(target.iceberg, penguins);
        }
        else{
            System.out.println("sendPenguins: \n freePenguins: "+getFreePenguins() + "tried to send: "+penguins);
        }
    }

    /**
     * @param game - game info
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
     * @param game - game info
     * @return list of friendly penguin groups to icebergs
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
     * @param game - game info
     * @return -
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
     * @param game - game info
     * @return -
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
     * @param game - game info
     * @return - list of enemy penguin groups coming to iceberg
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
     * @param game game info
     * @param target attacked Iceberg
     * @return -
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
        return penguinAmount + this.getPenguinsComingFromIceberg(game, target) + 1;
    }

    /**
     * @param game - game info
     * @return - penguin amount needs to be saved in Iceberg
     */
    public int amountToDefend(MyGame game) {
        List<PenguinGroup> comingPenguinGroups = allComingPenguinGroups(game);
        int penguinAmount = iceberg.penguinAmount;
        int previousTurnsTillArrival = 0;
        Player icebergOwner = iceberg.owner;
        while (!comingPenguinGroups.isEmpty()) {
            PenguinGroup closestPenguinGroup = closestTo(comingPenguinGroups);
            if (icebergOwner.equals(game.game.getMyself())) {
                if (closestPenguinGroup.owner.equals(icebergOwner)) {
                    penguinAmount += closestPenguinGroup.penguinAmount +
                            (closestPenguinGroup.turnsTillArrival - previousTurnsTillArrival) *
                                    iceberg.penguinsPerTurn;
                } else {
                    penguinAmount += -closestPenguinGroup.penguinAmount +
                            (closestPenguinGroup.turnsTillArrival - previousTurnsTillArrival) *
                                    iceberg.penguinsPerTurn;
                }
            }
            if (icebergOwner.equals(game.game.getEnemy())) {
                if (closestPenguinGroup.owner.equals(icebergOwner)) {
                    penguinAmount += -closestPenguinGroup.penguinAmount +
                            -(closestPenguinGroup.turnsTillArrival - previousTurnsTillArrival) *
                                    iceberg.penguinsPerTurn;
                } else {
                    penguinAmount += closestPenguinGroup.penguinAmount +
                            -(closestPenguinGroup.turnsTillArrival - previousTurnsTillArrival) *
                                    iceberg.penguinsPerTurn;
                }
            }
            if (icebergOwner.equals(game.game.getNeutral())) {
                if (closestPenguinGroup.owner.equals(game.game.getMyself())) {
                    penguinAmount = closestPenguinGroup.penguinAmount;
                } else {
                    penguinAmount = -closestPenguinGroup.penguinAmount;
                }
            }
            if (penguinAmount < 0)
                icebergOwner = game.game.getEnemy();
            else if (penguinAmount == 0)
                icebergOwner = game.game.getNeutral();
            else //(penguinAmount > 0)
                icebergOwner = game.game.getMyself();
            previousTurnsTillArrival = closestPenguinGroup.turnsTillArrival;
            comingPenguinGroups.remove(closestPenguinGroup);
        }
        if (penguinAmount <= 0)
            return 0;
        return iceberg.penguinAmount - penguinAmount + 1;
    }
}
