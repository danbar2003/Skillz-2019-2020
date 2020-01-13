package bots.wrapper;

import bots.Constant;
import bots.Utils;
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

    public void sendPenguins(int penguins, MyIceberg target){
        if (getFreePenguins() >= penguins){
            iceberg.sendPenguins(target.iceberg, penguins);
        }
        else{
            System.out.println("sendPenguins: \n freePenguins: "+getFreePenguins() + "tried to send: "+penguins);
        }
    }

    /**
     * @param iceberg - iceberg sending the penguins
     * @return -penguin amount coming
     */
    public int getPenguinsComingFromIceberg(MyIceberg iceberg) {
        int penguinAmountFromIceberg = 0;
        for (PenguinGroup penguinGroup : iceberg.getFriendlyPenguinGroupsToIceberg()) {
            if (penguinGroup.source == iceberg.iceberg && penguinGroup.destination == this.iceberg) {
                penguinAmountFromIceberg += penguinGroup.penguinAmount;
            }
        }
        return penguinAmountFromIceberg;
    }

    /**
     * @return list of friendly penguin groups to icebergs
     */
    private List<PenguinGroup> getFriendlyPenguinGroupsToIceberg() {
        List<PenguinGroup> friendlyPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : Constant.PenguinGroups.allPenguinGroup) {
            if (penguinGroup.owner.equals(this.gameObject.owner))
                friendlyPenguinGroups.add(penguinGroup);
        }
        return friendlyPenguinGroups;
    }

    /**
     * @return - list of enemy penguin groups to icebergs
     */
    private List<PenguinGroup> getEnemyPenguinGroupsToIceberg() {
        List<PenguinGroup> enemyPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : Constant.PenguinGroups.allPenguinGroup) {
            if (penguinGroup.owner != this.gameObject.owner)
                enemyPenguinGroups.add(penguinGroup);
        }
        return enemyPenguinGroups;
    }

    /**
     * @return - list of coming penguin groups
     */
    public List<PenguinGroup> allComingPenguinGroups() {
        List<PenguinGroup> comingPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : Constant.PenguinGroups.allPenguinGroup)
            if (penguinGroup.destination == this.iceberg)
                comingPenguinGroups.add(penguinGroup);
        return comingPenguinGroups;
    }

    /**
     * @return - list of helping penguin groups to icebergs
     */
    public List<PenguinGroup> getHelpingPenguinGroupsToIceberg() {
        List<PenguinGroup> helpingPenguinGroups = getFriendlyPenguinGroupsToIceberg();
        for (PenguinGroup penguinGroup : helpingPenguinGroups) {
            if (penguinGroup.destination.equals(this.iceberg))
                helpingPenguinGroups.add(penguinGroup);
        }
        return helpingPenguinGroups;
    }

    /**
     * @return - list of enemy penguin groups coming to iceberg
     */
    public List<PenguinGroup> getAttackingPenguinGroupsToIceberg() {
        List<PenguinGroup> enemyPenguinGroups = getEnemyPenguinGroupsToIceberg();
        for (PenguinGroup penguinGroup : enemyPenguinGroups) {
            if (penguinGroup.destination.equals(this.iceberg))
                enemyPenguinGroups.add(penguinGroup);
        }
        return enemyPenguinGroups;
    }

    /**
     * @param target attacked Iceberg
     * @return - minimum penguin amount this iceberg should send in order to capture target iceberg
     */
    public int minPenguinAmountToWin( MyIceberg target) {
        int penguinAmount = target.iceberg.penguinAmount +
                target.iceberg.penguinsPerTurn * iceberg.getTurnsTillArrival(target.iceberg);
        List<PenguinGroup> helpers = target.getHelpingPenguinGroupsToIceberg();
        for (PenguinGroup helper : helpers) {
            if (helper.turnsTillArrival < iceberg.getTurnsTillArrival(target.iceberg)) {
                penguinAmount += helper.penguinAmount;
            }
        }
        return penguinAmount + this.getPenguinsComingFromIceberg(target) + 1;
    }

    /**
     * @return - penguin amount needs to be saved in Iceberg
     */
    public int amountToDefend() {
        List<PenguinGroup> comingPenguinGroups = allComingPenguinGroups();
        int penguinAmount = iceberg.penguinAmount;
        int previousTurnsTillArrival = 0;
        Player icebergOwner = iceberg.owner;
        while (!comingPenguinGroups.isEmpty()) {
            PenguinGroup closestPenguinGroup = closestTo(comingPenguinGroups);
            if (icebergOwner.equals(Constant.Players.mySelf)) {
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
            if (icebergOwner.equals(Constant.Players.enemyPlayer)) {
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
            if (icebergOwner.equals(Constant.Players.neutral)) {
                if (closestPenguinGroup.owner.equals(Constant.Players.mySelf)) {
                    penguinAmount = closestPenguinGroup.penguinAmount;
                } else {
                    penguinAmount = -closestPenguinGroup.penguinAmount;
                }
            }
            if (penguinAmount < 0)
                icebergOwner = Constant.Players.enemyPlayer;
            if (penguinAmount == 0)
                icebergOwner = Constant.Players.enemyPlayer;
            if (penguinAmount > 0)
                icebergOwner = Constant.Players.mySelf;
            previousTurnsTillArrival = closestPenguinGroup.turnsTillArrival;
            comingPenguinGroups.remove(closestPenguinGroup);
        }
        if (penguinAmount <= 0)
            return 0;
        return iceberg.penguinAmount - penguinAmount + 1;
    }
}
