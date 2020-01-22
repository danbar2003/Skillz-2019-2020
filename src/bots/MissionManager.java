package bots;


import bots.missions.CaptureIceberg;
import bots.missions.Mission;
import bots.missions.SupportIceberg;
import bots.missions.UpgradeIceberg;
import bots.tasks.Attack;
import bots.tasks.Support;
import bots.tasks.Taskable;
import bots.tasks.Upgrade;
import bots.wrapper.MyIceberg;

import java.util.*;

public class MissionManager {
    private static int totalBenefit(Set<Mission> missionGroup){
        int benefit = 0;
        for (Mission mission : missionGroup){
            benefit += mission.benefit();
        }
        return benefit;
    }

    private static int totalLoss(Set<Taskable> taskGroup){
        int loss = 0;
        for (Taskable task : taskGroup){
            loss += task.loss();
        }
        return loss;
    }

    /**
     *
     * @param attackers - contributing icebergs to support
     * @param supportIceberg - mission
     * @return map of icebergs who contribute to the attack as keys and
     * penguin amount that each iceberg is contributing value
     */
    private static Set<Taskable> penguinsFromEachSupporter(List<MyIceberg> attackers, SupportIceberg supportIceberg) {
        Set<Taskable> tasks = new HashSet<>();
        int neededPenguins = supportIceberg.getTarget().farthest(attackers).iceberg.getTurnsTillArrival(supportIceberg.getTarget().iceberg)
                * supportIceberg.getTarget().iceberg.penguinsPerTurn + supportIceberg.getTarget().iceberg.penguinAmount + 1;

        double availablePenguins = 0;
        for (MyIceberg iceberg : attackers) {
            if (iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(supportIceberg.getTarget()) <= 0)
                return null;
            availablePenguins += iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(supportIceberg.getTarget());
        }

        if (availablePenguins > neededPenguins) {
            for (MyIceberg iceberg : attackers) {
                int realFreePenguins = iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(supportIceberg.getTarget());
                tasks.add(new Attack(iceberg, supportIceberg.getTarget(), (int) Math.round((realFreePenguins / availablePenguins) * neededPenguins) ));
            }
            return tasks;
        }
        return null;
    }

    /**
     * attackers - friendly (ours)
     * target - enemy iceberg
     *
     * @param attackers - contributing icebergs to attack
     * @param captureIceberg - mission
     * @return - map of icebergs who contribute to the attack as keys and
     * penguin amount that each iceberg is contributing as value
     */
    private static Set<Taskable> penguinsFromEachAttacker(List<MyIceberg> attackers, CaptureIceberg captureIceberg) {
        Set<Taskable> tasks = new HashSet<>();
        int neededPenguins = captureIceberg.getTarget().farthest(attackers).iceberg.getTurnsTillArrival(captureIceberg.getTarget().iceberg)
                * captureIceberg.getTarget().iceberg.penguinsPerTurn + captureIceberg.getTarget().iceberg.penguinAmount + 1;

        double availablePenguins = 0;
        for (MyIceberg iceberg : attackers) {
            if (iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(captureIceberg.getTarget()) <= 0)
                return null;
            availablePenguins += iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(captureIceberg.getTarget());
        }

        if (availablePenguins > neededPenguins) {
            for (MyIceberg iceberg : attackers) {
                int realFreePenguins = iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(captureIceberg.getTarget());
                tasks.add(new Attack(iceberg, captureIceberg.getTarget(), (int) Math.round((realFreePenguins / availablePenguins) * neededPenguins) ));
            }
            return tasks;
        }
        return null;
    }

    private static Set<Mission> allMissions(){
        Set<Mission> missions = new HashSet<>();

        for (MyIceberg iceberg : Constant.Icebergs.allIcebergs) {
            if (!iceberg.iceberg.owner.equals(Constant.Players.mySelf))
                missions.add(new CaptureIceberg(iceberg));
            else {
                missions.add(new SupportIceberg(iceberg));
                missions.add(new UpgradeIceberg(iceberg));
            }
        }
        return missions;
    }

    private static Set<Set<Mission>> allMissionGroups() {
        return Utils.powerSet(allMissions());
    }
}