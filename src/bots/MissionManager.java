package bots;


import bots.missions.CaptureIceberg;
import bots.missions.Mission;
import bots.missions.SupportIceberg;
import bots.missions.UpgradeIceberg;
import bots.tasks.*;
import bots.wrapper.MyIceberg;

import java.util.*;

public class MissionManager {

    private static Set<Mission> activeMissions; //All single missions that takes place atm.

    /**
     * @param mission - missions
     * @return - options to execute the mission (each option is by different iceberg group).
     */
    public static List<TaskGroup> waysToExecute(Mission mission) {
        List<TaskGroup> waysToExec = new LinkedList<>();
        if (mission instanceof CaptureIceberg)
            for (Set<MyIceberg> icebergs : Constant.Groups.allMyIcebergGroups)
                waysToExec.add(howToCapture(new LinkedList<>(icebergs), (CaptureIceberg) mission));
        if (mission instanceof SupportIceberg)
            for (Set<MyIceberg> icebergs : Constant.Groups.allMyIcebergGroups)
                waysToExec.add(howToSupport(new LinkedList<>(icebergs), (SupportIceberg) mission));
        if (mission instanceof UpgradeIceberg) {
            waysToExec.add(new TaskGroup(new Upgrade(mission.getTarget())));
        }
        return waysToExec;
    }

    public static int totalBenefit(Set<Mission> missionGroup) {
        int benefit = 0;
        for (Mission mission : missionGroup) {
            benefit += mission.benefit();
        }
        return benefit;
    }

    /**
     * @param supporters     - contributing icebergs to support
     * @param supportIceberg - mission
     * @return Set of tasks (task for each supporter)
     */
    private static TaskGroup howToSupport(List<MyIceberg> supporters, SupportIceberg supportIceberg) {
        TaskGroup tasks = new TaskGroup();
        //TODO - how to support... how much each iceberg should send.
        return tasks;
    }

    /**
     * attackers - friendly (ours)
     * target - enemy iceberg
     *
     * @param attackers      - contributing icebergs to attack
     * @param captureIceberg - mission
     * @return - Set of tasks
     */
    private static TaskGroup howToCapture(List<MyIceberg> attackers, CaptureIceberg captureIceberg) {
        TaskGroup tasks = new TaskGroup();
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
                tasks.add(new Attack(iceberg, captureIceberg.getTarget(), (int) Math.round((realFreePenguins / availablePenguins) * neededPenguins)));
            }
            return tasks;
        }
        return null;
    }

    /**
     * @return all single missions.
     */
    public static Set<Mission> allMissions() {
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

    /**
     * @param size - maxim size of groups
     * @return all mission groups with size <= param.size
     */
    public static Set<Set<Mission>> allMissionGroups(int size) {
        return Utils.powerSet(Constant.Groups.allMissions, size);
    }

    /**
     * this function decides how to execute each mission in a missionGroup.
     *
     * @param missions - missionGroup
     * @return - tasks for each mission (all tasks in the same list)
     */
    public static TaskGroup howToExecuteMissionGroup(List<Mission> missions) {
        return null;
    }

    /**
     * This function decide which missionGroup to execute. (totalBenefit - totalLoss)
     *
     * @return set of tasks that will execute the chosen missionGroup.
     */
    public static Set<Taskable> createTasksForIcebergs() {
        return null;
    }
}