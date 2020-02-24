package bots;


import penguin_game.Iceberg;
import penguin_game.Player;

import java.util.Collection;
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
        this.savedPenguins = penguinAmount - this.iceberg.penguinsPerTurn;
    }

    public int getFreePenguins() {
        return (iceberg.penguinAmount - savedPenguins);
    }

    public String toString(){
        return iceberg.toString();
    }

    public boolean canUpgrade() {
        if (iceberg.canUpgrade())
            return getFreePenguins() >= iceberg.upgradeCost;
        return false;
    }

    public void sendPenguins(MyIceberg target, int penguins) {
        if (getFreePenguins() >= penguins) {
            iceberg.sendPenguins(target.iceberg, penguins);
        } else {
            System.out.println("sendPenguins: \n freePenguins: " + getFreePenguins() + "tried to send: " + penguins);
        }
    }

    public MyIceberg farthest(Collection<MyIceberg> arr) {
        MyIceberg farthest = this;
        for (MyIceberg iceberg : arr) {
            if (iceberg.iceberg.getTurnsTillArrival(this.iceberg) > farthest.iceberg.getTurnsTillArrival(this.iceberg)) {
                farthest = iceberg;
            }
        }
        return farthest;
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

    public List<MyPenguinGroup> getEnemyPenguinGroupsToIceberg() {
        List<MyPenguinGroup> friendlyPenguinGroups = new LinkedList<>();
        for (MyPenguinGroup penguinGroup : Constant.PenguinGroups.allPenguinGroup) {
            if (!penguinGroup.penguinGroup.owner.equals(this.gameObject.owner))
                friendlyPenguinGroups.add(penguinGroup);
        }
        return friendlyPenguinGroups;
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
     * @return - minimum penguin amount this iceberg should send in order to capture target iceberg
     */
    public int minPenguinAmountToWin(int turns) {
        int penguinAmount = this.iceberg.penguinAmount, turnsPast = 0, penguinGroupTurnsTillArrival, startTurns = turns, turnsSinceCapture = turns;
        Player owner = this.iceberg.owner;
        if (allComingPenguinGroups().isEmpty()) {
            if (owner.equals(Constant.Players.neutral)) {
                return this.iceberg.penguinAmount + 1;
            }
            if (owner.equals(Constant.Players.enemyPlayer)) {
                return this.iceberg.penguinAmount + this.iceberg.penguinsPerTurn * turns + 1;
            }
        }
        for (MyPenguinGroup penguinGroup : orderOfComingPenguinGroupsToIceberg()) {
            penguinGroupTurnsTillArrival = penguinGroup.penguinGroup.turnsTillArrival - turnsPast;
            turns = startTurns - turnsPast;
            if (penguinGroupTurnsTillArrival <= turns) {
                if (!owner.equals(Constant.Players.neutral))
                    penguinAmount += penguinGroupTurnsTillArrival * this.iceberg.penguinsPerTurn;
                if (owner.equals(penguinGroup.penguinGroup.owner)) {
                    penguinAmount += penguinGroup.penguinGroup.penguinAmount;
                } else {
                    penguinAmount -= penguinGroup.penguinGroup.penguinAmount;
                    if (penguinAmount < 0) {
                        penguinAmount = Math.abs(penguinAmount);
                        owner = penguinGroup.penguinGroup.owner;
                        turnsSinceCapture = turns - penguinGroup.penguinGroup.turnsTillArrival;
                    }
                }
            }
            turnsPast += penguinGroupTurnsTillArrival;
        }
        return penguinAmount + 1 + this.iceberg.penguinsPerTurn * turnsSinceCapture;
    }

    public List<MyPenguinGroup> orderOfComingPenguinGroupsToIceberg() {
        List<MyPenguinGroup> orderOfArrival = new LinkedList<>();
        List<MyPenguinGroup> comingPenguinGroups = allComingPenguinGroups();
        while (!comingPenguinGroups.isEmpty()) {
            MyPenguinGroup penguinGroup = closestTo(comingPenguinGroups);
            orderOfArrival.add(penguinGroup);
            comingPenguinGroups.remove(penguinGroup);
        }
        return orderOfArrival;
    }

    /**
     * @return how the iceberg state will look like after all penguin-groups will reach it.
     */
    public int futureState(boolean isUpgrading) {
        List<MyPenguinGroup> comingPenguinGroups = allComingPenguinGroups();
        int penguinsAmount = iceberg.penguinAmount;
        int penguinsPerTurn = iceberg.penguinsPerTurn;
        if (isUpgrading) {
            penguinsAmount -= iceberg.upgradeCost;
            penguinsPerTurn++;
        }
        Player icebergOwner = iceberg.owner;
        if (icebergOwner.equals(Constant.Players.enemyPlayer))
            penguinsAmount *= -1;

        int previousTurnsTillArrival = 0;
        while (!comingPenguinGroups.isEmpty()) {
            MyPenguinGroup penguinGroup = closestTo(comingPenguinGroups);

            if (icebergOwner.equals(Constant.Players.mySelf)) {
                penguinsAmount += (penguinGroup.penguinGroup.turnsTillArrival - previousTurnsTillArrival) * penguinsPerTurn;

                if (penguinGroup.penguinGroup.owner.equals(Constant.Players.mySelf)) {
                    penguinsAmount += penguinGroup.penguinGroup.penguinAmount;
                } else {
                    penguinsAmount -= penguinGroup.penguinGroup.penguinAmount;
                }
            }
            if (icebergOwner.equals(Constant.Players.enemyPlayer)) {
                penguinsAmount -= (penguinGroup.penguinGroup.turnsTillArrival - previousTurnsTillArrival) * penguinsPerTurn;

                if (penguinGroup.penguinGroup.owner.equals(Constant.Players.mySelf)) {
                    penguinsAmount += penguinGroup.penguinGroup.penguinAmount;
                } else {
                    penguinsAmount -= penguinGroup.penguinGroup.penguinAmount;
                }
            }
            if (icebergOwner.equals(Constant.Players.neutral)) {
                penguinsAmount -= penguinGroup.penguinGroup.penguinAmount;
                if (penguinsAmount < 0) {
                    if (penguinGroup.penguinGroup.owner.equals(Constant.Players.mySelf))
                        penguinsAmount = Math.abs(penguinsAmount);
                } else {
                    previousTurnsTillArrival = penguinGroup.penguinGroup.turnsTillArrival;
                    comingPenguinGroups.remove(penguinGroup);
                    break;
                }
            }

            if (penguinsAmount < 0)
                icebergOwner = Constant.Players.enemyPlayer;
            if (penguinsAmount > 0)
                icebergOwner = Constant.Players.mySelf;
            if (penguinsAmount == 0)
                icebergOwner = Constant.Players.neutral;

            previousTurnsTillArrival = penguinGroup.penguinGroup.turnsTillArrival;
            comingPenguinGroups.remove(penguinGroup);
        }
        //if penguinAmount == 0 - neutralized.
        //if penguinAmount > 0 - all good, iceberg is ours.
        //if penguinAmount < 0 - not good, iceberg is theirs.
        return penguinsAmount;
    }
}
