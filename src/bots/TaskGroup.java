package bots;


import java.util.*;

public class TaskGroup {

    private Set<Taskable> tasks;

    public TaskGroup(Taskable... tasks) {
        this.tasks = new HashSet<>();
        this.tasks.addAll(Arrays.asList(tasks));
    }

    public TaskGroup() {
        this.tasks = new HashSet<>();
    }

    public TaskGroup(Set<Taskable> tasks) {
        this.tasks = tasks;
    }

    public int getTotalLoss() {
        int loss = 0;
        for (Taskable task : tasks) {
            loss += task.loss();
        }
        return loss;
    }

    public Set<Taskable> getTasks() {
        return tasks;
    }

    public void add(Taskable task) {
        tasks.add(task);
    }

    public void addAll(TaskGroup taskGroup) {
        tasks.addAll(taskGroup.tasks);
    }

    public boolean hasShared(TaskGroup taskGroup) {
        for (Taskable task : taskGroup.getTasks())
            if (this.tasks.contains(task))
                return true;
        return false;
    }

    public Set<MyIceberg> usedIcebergs() {
        Set<MyIceberg> usedIcebergs = new HashSet<>();
        for (Taskable task : tasks) {
            usedIcebergs.add(task.getActor());
        }
        return usedIcebergs;
    }

    public boolean canCompleteTaskGroup() {
        int usedPenguins = 0, available = 0;
        List<MyIceberg> upgrading = new LinkedList<>();
        for (Taskable task : this.tasks) {
            usedPenguins += task.penguins();
            if(task instanceof Upgrade)
                upgrading.add(task.getActor());
        }

        for(Taskable task: this.tasks)
            if(upgrading.contains(task.getActor()) && !(task instanceof Upgrade))
                return false;

        for (MyIceberg iceberg : this.usedIcebergs()) {
            available += iceberg.getFreePenguins();
        }

        return available >= usedPenguins;
    }

    @Override
    public String toString() {
        return "TaskGroup{" +
                "tasks=" + tasks +
                '}';
    }
}
