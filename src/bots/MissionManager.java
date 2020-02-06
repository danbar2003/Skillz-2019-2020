package bots;


import bots.missions.CaptureIceberg;
import bots.missions.Mission;
import bots.missions.SupportIceberg;
import bots.missions.UpgradeIceberg;
import bots.tasks.*;
import bots.wrapper.MyIceberg;

import java.util.*;

public class MissionManager {

    private static Set<Mission> activeMissions = new HashSet<>(); //All single missions that takes place atm.

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
        missions.removeIf(MissionManager::isActive);
        return missions;
    }

    private static boolean isActive(Mission mission){
        for (Mission activeMission : activeMissions)
            if (activeMission.getType().equals(mission.getType()))
                return true;
        return true;
    }

    /**
     * @param size - maxim size of groups
     * @return all mission groups with size <= param.size
     */
    public static Set<Set<Mission>> allMissionGroups(int size) {
        return Utils.powerSet(Constant.Groups.allMissions, size);
    }

    private static TaskGroup createTaskGroup(List<List<TaskGroup>> taskGroupMatrix, int comb) {
        TaskGroup taskGroup = new TaskGroup();
        int a = 1;
        for (int layer = taskGroupMatrix.size() - 1; layer > 0; layer--){
            if (!taskGroup.hasShared(taskGroupMatrix.get(layer).get(comb & a)))
                taskGroup.addAll(taskGroupMatrix.get(layer).get(comb & a));
            else
                return null;
            a<<=1;
        }
        return taskGroup;
    }

    private static Set<Mission> getHolder(List<Set<Mission>> missionGroups){
        for (Set<Mission> missionsGroup : missionGroups)
            if (howToExecuteMissionGroup(missionsGroup) != null)
                return missionsGroup;
        return null;
    }

    /**
     * this function decides how to execute each mission in a missionGroup.
     *
     * @param missions - missionGroup
     * @return - tasks for each mission (all tasks in the same list)
     */
    public static TaskGroup howToExecuteMissionGroup(Set<Mission> missions) {
        //Init taskGroupMatrix
        List<List<TaskGroup>> taskGroupMatrix = new LinkedList<>();
        for (Mission mission : missions) {
            List<TaskGroup> layer = new LinkedList<>(mission.getWaysToExecute());
            taskGroupMatrix.add(layer);
        }

        //Get all available taskGroup that can execute missionGroup.
        int combination = 0;
        List<TaskGroup> availableTaskGroup = new LinkedList<>();
        while (combination < Math.pow(2, missions.size())) {
            TaskGroup taskGroup = createTaskGroup(taskGroupMatrix, combination);
            combination++;

            if (taskGroup != null)
                availableTaskGroup.add(taskGroup);
        }

        if (availableTaskGroup.size() == 0)
            return null; //cant execute this mission group
        TaskGroup holder = availableTaskGroup.get(0);
        for (TaskGroup taskGroup : availableTaskGroup){
            if (taskGroup.getTotalLoss() < holder.getTotalLoss())
                holder = taskGroup;
        }
        return holder;
    }

    /**
     * This function decide which missionGroup to execute. (totalBenefit - totalLoss)
     *
     * @return set of tasks that will execute the chosen missionGroup.
     */
    public static Set<Taskable> createTasksForIcebergs() {
        List<Set<Mission>> missionGroups = new LinkedList<>(Constant.Groups.allMissionGroups);

        Set<Mission> holder = getHolder(missionGroups);
        if (holder == null)
            return new HashSet<Taskable>();
        for (Set<Mission> missionGroup : missionGroups){
            //possible mission group
            if (howToExecuteMissionGroup(missionGroup) != null)
                //if profit is higher
                if (totalBenefit(missionGroup) - howToExecuteMissionGroup(missionGroup).getTotalLoss() >
                        totalBenefit(holder) - howToExecuteMissionGroup(holder).getTotalLoss())
                    holder = missionGroup;
        }
        MissionManager.activeMissions.addAll(holder);
        for (Mission mission : holder)
            mission.setState(Mission.State.ACTIVE);
        return howToExecuteMissionGroup(holder).getTasks();
    }
}