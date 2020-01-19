package bots;


import bots.missions.CaptureIceberg;
import bots.missions.Mission;
import bots.missions.SupportIceberg;
import bots.missions.UpgradeIceberg;
import bots.tasks.Upgrade;
import bots.wrapper.MyIceberg;

import java.util.*;

public class MissionManager {

    public static Set<Set<MyIceberg>> allMyIcebergGroups() {
        Set<MyIceberg> availableIcebergs = new HashSet<>(Constant.Icebergs.myIcebergs);
        availableIcebergs.removeAll(Utils.myThreatenedIcebergs());
        return Utils.powerSet(availableIcebergs);
    }

    /**
     * attackers - friendly (ours)
     * target - enemy iceberg
     *
     * @param attackers - contributing icebergs to attack
     * @param target    - enemy iceberg
     * @return - map of icebergs who contribute to the attack as keys and
     * penguin amount that each iceberg is contributing as value
     */
    public static Map<MyIceberg, Integer> penguinsFromEachIceberg(List<MyIceberg> attackers, MyIceberg target) {
        Map<MyIceberg, Integer> penguinsFromIcebergs = new HashMap<>();
        int neededPenguins = target.farthest(attackers).iceberg.getTurnsTillArrival(target.iceberg)
                * target.iceberg.penguinsPerTurn + target.iceberg.penguinAmount + 1;

        double availablePenguins = 0;
        for (MyIceberg iceberg : attackers) {
            if (iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(target) <= 0)
                return null;
            availablePenguins += iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(target);
        }

        if (availablePenguins > neededPenguins) {
            for (MyIceberg iceberg : attackers) {
                int realFreePenguins = iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(target);
                penguinsFromIcebergs.put(iceberg, (int) Math.round((realFreePenguins / availablePenguins) * neededPenguins));
            }
            return penguinsFromIcebergs;
        }
        return null;
    }

    /**
     * @return - all options to attack each enemy iceberg
     * key - target (enemy iceberg)
     * value - list of options to attack the iceberg
     * value(Map):
     * key - attacking Iceberg
     * value - penguins amount
     */
    public static Map<MyIceberg, Set<Map<MyIceberg, Integer>>> optionsToAttack() {
        Map<MyIceberg, Set<Map<MyIceberg, Integer>>> optionToAttackEnemy = new HashMap<>();
        for (MyIceberg enemyIceberg : Constant.Icebergs.enemyIcebergs) {
            Set<Map<MyIceberg, Integer>> waysToAttack = new HashSet<>();
            for (Set<MyIceberg> group : Constant.IcebergGroups.allMyIcebergGroups) {
                Map<MyIceberg, Integer> option = penguinsFromEachIceberg(new LinkedList<>(group), enemyIceberg);
                if (option != null)
                    waysToAttack.add(option);
            }
            optionToAttackEnemy.put(enemyIceberg, waysToAttack);
        }
        return optionToAttackEnemy;
    }

    public static Set<Map<Set<Mission>, Integer>> benefitOfMissions(Set<Set<Mission>> missionGroups ){
        Set<Map<Set<Mission>, Integer>> benefitOfMission = new HashSet<>();
        



        return benefitOfMission;
    }

    public static Set<Set<Mission>> allMissions() {
        Set<Set<Mission>> missionsGroups = new HashSet<>();
        Set<Mission> missions = new HashSet<>();

        for (MyIceberg iceberg : Constant.Icebergs.allIcebergs) {
            if (!iceberg.iceberg.owner.equals(Constant.Players.mySelf))
                missions.add(new CaptureIceberg(iceberg));
            else {
                missions.add(new SupportIceberg(iceberg));
                missions.add(new UpgradeIceberg(iceberg));
            }
        }
        missionsGroups = Utils.powerSet(missions);

        return missionsGroups;
    }
}

