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

    private static Set<Mission> activeMissions;

    /**
     *
     * @param mission - missions
     * @return - options to execute the mission (each option is by different iceberg group).
     */
    public static Set<Set<Taskable>> waysToExecute(Mission mission){
        Set<Set<Taskable>> waysToExec = new HashSet<>();

        if (mission instanceof CaptureIceberg)
            for (Set<MyIceberg> icebergs : Constant.Groups.allMyIcebergGroups)
                waysToExec.add(howToCapture(new LinkedList<>(icebergs), (CaptureIceberg) mission));
        if (mission instanceof SupportIceberg)
            for (Set<MyIceberg> icebergs : Constant.Groups.allMyIcebergGroups)
                waysToExec.add(howToSupport(new LinkedList<>(icebergs), (SupportIceberg) mission));
        if (mission instanceof UpgradeIceberg) {
            Set<Taskable> upgradeTask = new HashSet<>();
            upgradeTask.add(new Upgrade(mission.getTarget()));
            waysToExec.add(upgradeTask);
        }

        return waysToExec;
    }

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
     * @param supporters - contributing icebergs to support
     * @param supportIceberg - mission
     * @return Set of tasks (task for each supporter)
     */
    private static Set<Taskable> howToSupport(List<MyIceberg> supporters, SupportIceberg supportIceberg) {
        Set<Taskable> tasks = new HashSet<>();
        //TODO - how to support... how much each iceberg should send.
        return tasks;
    }

    /**
     * attackers - friendly (ours)
     * target - enemy iceberg
     *
     * @param attackers - contributing icebergs to attack
     * @param captureIceberg - mission
     * @return - Set of tasks
     */
    private static Set<Taskable> howToCapture(List<MyIceberg> attackers, CaptureIceberg captureIceberg) {
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

    /**
     *
     * @return all single missions.
     */
    public static Set<Mission> allMissions(){
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

    public static Set<Set<Mission>> allMissionGroups() {
        //TODO we need to add a filter
        return Utils.powerSet(Constant.Groups.allMissions);
    }

    /**
     * In this function we which missionGroup will be executed.
     * @return map of mission as key and tasks as value for each mission.
     */
    public static Map<Mission, Set<Taskable>> howToExecute(){
        Map<Mission, Set<Taskable>> howToExecute = new HashMap<>();
        //TODO - create this function.
        return howToExecute;
    }
}