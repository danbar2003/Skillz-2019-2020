package bots;


import bots.missions.CaptureIceberg;
import bots.missions.Mission;
import bots.missions.SupportIceberg;
import bots.missions.UpgradeIceberg;
import bots.tasks.Attack;
import bots.tasks.TaskGroup;
import bots.tasks.Taskable;
import bots.tasks.Upgrade;
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
        waysToExec.removeIf(Objects::isNull);
        System.out.println("Mission: " + mission.getType());
        System.out.println(waysToExec);
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
        int neededPenguins = captureIceberg.getTarget().iceberg.penguinAmount + 1;
        if (captureIceberg.getTarget().iceberg.owner.equals(Constant.Players.enemyPlayer))
            neededPenguins = +captureIceberg.getTarget().iceberg.penguinsPerTurn *
                    captureIceberg.getTarget().farthest(attackers).iceberg.getTurnsTillArrival(captureIceberg.getTarget().iceberg);


        double availablePenguins = 0;
        for (MyIceberg iceberg : attackers) {
            if (iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(captureIceberg.getTarget()) <= 0)
                return tasks;
            availablePenguins += iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(captureIceberg.getTarget());
        }

        if (availablePenguins > neededPenguins) {
            for (MyIceberg iceberg : attackers) {
                int realFreePenguins = iceberg.getFreePenguins() - iceberg.getPenguinsComingFromIceberg(captureIceberg.getTarget());
                tasks.add(new Attack(iceberg, captureIceberg.getTarget(), (int) Math.round((realFreePenguins / availablePenguins) * neededPenguins)));
            }
        }
        return tasks;
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
                if (iceberg.iceberg.canUpgrade())
                    missions.add(new UpgradeIceberg(iceberg));
            }
        }
        return missions;
    }

    private static boolean isActive(Mission mission) {
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
        for (int layer = taskGroupMatrix.size() - 1; layer > 0; layer--) {
            if (!taskGroup.hasShared(taskGroupMatrix.get(layer).get(comb & a)))
                taskGroup.addAll(taskGroupMatrix.get(layer).get(comb & a));
            else
                return null;
            a <<= 1;
        }
        return taskGroup;
    }

    public static List<TaskGroup> allCombinations(List<List<TaskGroup>> matrix) {
        int combination = 0;

        int maxCombinations = matrix.get(0).size();
        for (int layer = 1; layer < matrix.size(); layer++)
            maxCombinations *= matrix.get(layer).size();

        List<TaskGroup> combinationList = new LinkedList<>();
        while (combination < maxCombinations) {
            combinationList.add(createCombination(matrix, combination));
            combination++;
        }
        combinationList.removeIf(Objects::isNull);
        return combinationList;
    }

    public static TaskGroup createCombination(List<List<TaskGroup>> matrix, int comb) {
        TaskGroup combination = new TaskGroup();
        int[] index = new int[matrix.size()];
        for (int layer = matrix.size() - 1; layer >= 0; layer--) {
            index[layer] = comb % matrix.get(layer).size();
            comb /= matrix.get(layer).size();
        }

        for (int layer = 0; layer < matrix.size(); layer++) {
            if (!combination.hasShared(matrix.get(layer).get(index[layer])))
                combination.addAll(matrix.get(layer).get(index[layer]));
            else
                return null;
        }
        return combination;
    }

    /**
     * this function decides how to execute each mission in a missionGroup.
     *
     * @param missions - missionGroup
     * @return - tasks for each mission (all tasks in the same list)
     */
    public static TaskGroup howToExecuteMissionGroup(Set<Mission> missions) {
        List<List<TaskGroup>> matrix = new LinkedList<>();
        //create matrix
        for (Mission mission : missions) {
            matrix.add(mission.getWaysToExecute());
        }

        List<TaskGroup> availableTaskGroups = allCombinations(matrix);
        if (availableTaskGroups.isEmpty())
            return new TaskGroup();
        TaskGroup holder = availableTaskGroups.get(0);
        for (TaskGroup taskGroup : availableTaskGroups)
            if (taskGroup.getTotalLoss() < holder.getTotalLoss())
                holder = taskGroup;
        return holder;
    }

    /**
     * This function decide which missionGroup to execute. (totalBenefit - totalLoss)
     *
     * @return set of tasks that will execute the chosen missionGroup.
     */
    public static Set<Taskable> createTasksForIcebergs() {
        List<Set<Mission>> allMissionGroups = new LinkedList<>(Constant.Groups.allMissionGroups);
        Set<Mission> holder = allMissionGroups.get(0);
        for (Set<Mission> missionGroup : allMissionGroups)
            if (totalBenefit(holder) - howToExecuteMissionGroup(holder).getTotalLoss() <
                    totalBenefit(missionGroup) - howToExecuteMissionGroup(missionGroup).getTotalLoss())
                holder = missionGroup;
        return howToExecuteMissionGroup(holder).getTasks();
    }
}