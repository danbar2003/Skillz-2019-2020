package bots.wrapper;

import penguin_game.*;

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
        this.savedPenguins = penguinAmount;
    }

    public int getSavedPenguins() {
        return this.savedPenguins;
    }

    public int getFreePenguins(){
        return (iceberg.penguinAmount - getSavedPenguins());
    }

    public int getPenguinsComingFromIceberg(Game game, MyIceberg iceberg){
        int penguinAmountFromIceberg = 0;
        for (PenguinGroup penguinGroup : iceberg.getFriendlyPenguinGroupsToIceberg(game)){
            if (penguinGroup.source == iceberg.iceberg && penguinGroup.destination == this.iceberg){
                penguinAmountFromIceberg += penguinGroup.penguinAmount;
            }
        }
        return penguinAmountFromIceberg;
    }

    private List<PenguinGroup> getFriendlyPenguinGroupsToIceberg(Game game) {
        List<PenguinGroup> friendlyPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : game.getAllPenguinGroups()) {
            if (penguinGroup.owner == this.gameObject.owner)
                friendlyPenguinGroups.add(penguinGroup);
        }
        return friendlyPenguinGroups;
    }

    private List<PenguinGroup> getEnemyPenguinGroupsToIceberg(Game game) {
        List<PenguinGroup> enemyPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : game.getAllPenguinGroups()) {
            if (penguinGroup.owner != this.gameObject.owner)
                enemyPenguinGroups.add(penguinGroup);
        }
        return enemyPenguinGroups;
    }

    public List<PenguinGroup> allComingPenguinGroups(Game game) {
        List<PenguinGroup> comingPenguinGroups = new LinkedList<>();
        for (PenguinGroup penguinGroup : game.getAllPenguinGroups())
            if (penguinGroup.destination == this.iceberg)
                comingPenguinGroups.add(penguinGroup);
        return comingPenguinGroups;
    }

    public List<PenguinGroup> getHelpingPenguinGroupsToIceberg(Game game) {
        List<PenguinGroup> friendlyPenguinGroups = getFriendlyPenguinGroupsToIceberg(game);
        for (PenguinGroup penguinGroup : friendlyPenguinGroups) {
            if (penguinGroup.destination == this.iceberg)
                friendlyPenguinGroups.add(penguinGroup);
        }
        return friendlyPenguinGroups;
    }

    public List<PenguinGroup> getAttackingPenguinGroupsToIceberg(Game game) {
        List<PenguinGroup> enemyPenguinGroups = getEnemyPenguinGroupsToIceberg(game);
        for (PenguinGroup penguinGroup : enemyPenguinGroups) {
            if (penguinGroup.destination == this.iceberg)
                enemyPenguinGroups.add(penguinGroup);
        }
        return enemyPenguinGroups;
    }

    public int minPenguinAmountToWin(Game game, MyIceberg target) {
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

    public boolean canDefendItself(Game game) {
        List<PenguinGroup> comingPenguinGroups = allComingPenguinGroups(game);
        int penguinAmount = iceberg.penguinAmount;
        for (int i = 0; i < comingPenguinGroups.size(); i++) {
            PenguinGroup closestPenguinGroup = closestTo(comingPenguinGroups);
            if (closestPenguinGroup.owner == iceberg.owner)
                penguinAmount += iceberg.penguinsPerTurn * closestPenguinGroup.turnsTillArrival
                        + closestPenguinGroup.penguinAmount;
            else {
                penguinAmount += iceberg.penguinsPerTurn * closestPenguinGroup.turnsTillArrival
                        - closestPenguinGroup.penguinAmount;
            }
        }
        return penguinAmount >= 0;
    }
}
