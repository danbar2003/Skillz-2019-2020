package bots;


import penguin_game.Iceberg;
import penguin_game.Player;

import java.util.LinkedList;
import java.util.List;

public class MyIceberg extends MyGameObject {

    public Iceberg iceberg;
    private int savedPenguins;
    private boolean hasTask;

    public MyIceberg(Iceberg iceberg) {
        super(iceberg);
        this.iceberg = iceberg;
        this.savedPenguins = 0;

    }

    public boolean hasTask() {
        return hasTask;
    }

    public void setHasTask(boolean hasTask) {
        this.hasTask = hasTask;
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
     * @param iceberg - iceberg sending the penguins
     * @return -penguin amount coming
     */
    public int getPenguinsComingFromIceberg(MyIceberg iceberg) {
        int penguinAmountFromIceberg = 0;
        for (MyPenguinGroup penguinGroup : iceberg.getFriendlyPenguinGroupsToIceberg()) {
            if (penguinGroup.penguinGroup.source.equals(iceberg.iceberg) && penguinGroup.penguinGroup.destination.equals(this.iceberg)) {
                penguinAmountFromIceberg += penguinGroup.penguinGroup.penguinAmount;
            }
        }
        return penguinAmountFromIceberg;
    }

    /**
     * @return list of friendly penguin groups to icebergs
     */
    private List<MyPenguinGroup> getFriendlyPenguinGroupsToIceberg() {
        List<MyPenguinGroup> friendlyPenguinGroups = new LinkedList<>();
        for (MyPenguinGroup penguinGroup : Constant.PenguinGroups.allPenguinGroup) {
            if (penguinGroup.penguinGroup.owner.equals(this.gameObject.owner))
                friendlyPenguinGroups.add(penguinGroup);
        }
        return friendlyPenguinGroups;
    }

    /**
     * @return - list of enemy penguin groups to icebergs
     */
    private List<MyPenguinGroup> getEnemyPenguinGroupsToIceberg() {
        List<MyPenguinGroup> enemyPenguinGroups = new LinkedList<>();
        for (MyPenguinGroup penguinGroup : Constant.PenguinGroups.allPenguinGroup) {
            if (!penguinGroup.penguinGroup.owner.equals(this.gameObject.owner))
                enemyPenguinGroups.add(penguinGroup);
        }
        return enemyPenguinGroups;
    }

    /**
     * @return - list of coming penguin groups
     */
    public List<MyPenguinGroup> allComingPenguinGroups() {
        List<MyPenguinGroup> comingPenguinGroups = new LinkedList<>();
        for (MyPenguinGroup penguinGroup : Constant.PenguinGroups.allPenguinGroup)
            if (penguinGroup.penguinGroup.destination.equals(this.iceberg))
                comingPenguinGroups.add(penguinGroup);
        return comingPenguinGroups;
    }

    /**
     * @return - list of helping penguin groups to icebergs
     */
    public List<MyPenguinGroup> getHelpingPenguinGroupsToIceberg() {
        List<MyPenguinGroup> helpingPenguinGroups = new LinkedList<>();
        for (MyPenguinGroup penguinGroup : getFriendlyPenguinGroupsToIceberg()) {
            if (penguinGroup.penguinGroup.destination.equals(this.iceberg))
                helpingPenguinGroups.add(penguinGroup);
        }
        return helpingPenguinGroups;
    }

    /**
     * @return - list of enemy penguin groups coming to iceberg
     */
    public List<MyPenguinGroup> getAttackingPenguinGroupsToIceberg() {
        List<MyPenguinGroup> enemyPenguinGroups = getEnemyPenguinGroupsToIceberg();
        for (MyPenguinGroup penguinGroup : enemyPenguinGroups) {
            if (penguinGroup.penguinGroup.destination.equals(this.iceberg))
                enemyPenguinGroups.add(penguinGroup);
        }
        return enemyPenguinGroups;
    }

    /**
     * @param target attacked Iceberg
     * @return - minimum penguin amount this iceberg should send in order to capture target iceberg
     */
    public int minPenguinAmountToWin(MyIceberg target) {
        int penguinAmount = target.iceberg.penguinAmount;
        if (target.iceberg.owner.equals(Constant.Players.enemyPlayer))
            penguinAmount += target.iceberg.penguinsPerTurn * iceberg.getTurnsTillArrival(target.iceberg);
        List<MyPenguinGroup> helpers = target.getHelpingPenguinGroupsToIceberg();
        for (MyPenguinGroup helper : helpers) {
            if (helper.penguinGroup.turnsTillArrival < iceberg.getTurnsTillArrival(target.iceberg)) {
                penguinAmount += helper.penguinGroup.penguinAmount;
            }
        }
        return penguinAmount + this.getPenguinsComingFromIceberg(target) + 1;
    }

    /**
     * @return - penguin amount needs to be saved in Iceberg
     */
    public int amountToDefend() {
        List<MyPenguinGroup> comingPenguinGroups = allComingPenguinGroups();
        int penguinAmount = iceberg.penguinAmount;
        int previousTurnsTillArrival = 0;
        Player icebergOwner = iceberg.owner;
        while (!comingPenguinGroups.isEmpty()) {
            MyPenguinGroup closestPenguinGroup = closestTo(comingPenguinGroups);
            if (icebergOwner.equals(Constant.Players.mySelf)) {
                if (closestPenguinGroup.penguinGroup.owner.equals(icebergOwner)) {
                    penguinAmount += closestPenguinGroup.penguinGroup.penguinAmount +
                            (closestPenguinGroup.penguinGroup.turnsTillArrival - previousTurnsTillArrival) *
                                    iceberg.penguinsPerTurn;
                } else {
                    penguinAmount += -closestPenguinGroup.penguinGroup.penguinAmount +
                            (closestPenguinGroup.penguinGroup.turnsTillArrival - previousTurnsTillArrival) *
                                    iceberg.penguinsPerTurn;
                }
            }
            if (icebergOwner.equals(Constant.Players.enemyPlayer)) {
                if (closestPenguinGroup.penguinGroup.owner.equals(icebergOwner)) {
                    penguinAmount += -closestPenguinGroup.penguinGroup.penguinAmount +
                            -(closestPenguinGroup.penguinGroup.turnsTillArrival - previousTurnsTillArrival) *
                                    iceberg.penguinsPerTurn;
                } else {
                    penguinAmount += closestPenguinGroup.penguinGroup.penguinAmount +
                            -(closestPenguinGroup.penguinGroup.turnsTillArrival - previousTurnsTillArrival) *
                                    iceberg.penguinsPerTurn;
                }
            }
            if (icebergOwner.equals(Constant.Players.neutral)) {
                if (closestPenguinGroup.penguinGroup.owner.equals(Constant.Players.mySelf)) {
                    penguinAmount = closestPenguinGroup.penguinGroup.penguinAmount;
                } else {
                    penguinAmount = -closestPenguinGroup.penguinGroup.penguinAmount;
                }
            }
            if (penguinAmount < 0)
                icebergOwner = Constant.Players.enemyPlayer;
            if (penguinAmount == 0)
                icebergOwner = Constant.Players.enemyPlayer;
            if (penguinAmount > 0)
                icebergOwner = Constant.Players.mySelf;
            previousTurnsTillArrival = closestPenguinGroup.penguinGroup.turnsTillArrival;
            comingPenguinGroups.remove(closestPenguinGroup);
        }
        if (penguinAmount <= 0)
            return 0;
        return iceberg.penguinAmount - penguinAmount + 1;
    }
}
