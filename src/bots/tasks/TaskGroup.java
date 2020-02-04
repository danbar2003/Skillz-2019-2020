package bots.tasks;

import bots.wrapper.MyIceberg;

import java.util.*;

public class TaskGroup {

    private Set<Taskable> tasks;

    public TaskGroup(Taskable... tasks){
        this.tasks.addAll(Arrays.asList(tasks));
    }

    public TaskGroup(){
        this.tasks = new HashSet<>();
    }

    public TaskGroup(Set<Taskable> tasks){
        this.tasks = tasks;
    }

    public int getTotalLoss(){
        int loss = 0;
        for (Taskable task : tasks)
            loss += task.loss();
        return loss;
    }

    public Set<Taskable> getTasks(){
        return tasks;
    }



    public void add(Taskable task){
        tasks.add(task);
    }

    public boolean hasShared(TaskGroup taskGroup){
        for (Taskable task : taskGroup.getTasks())
            if (this.tasks.contains(task))
                return true;
        return false;
    }

    public List<MyIceberg> usedIcebergs(){
        List<MyIceberg> usedIcebergs = new LinkedList<>();
        for (Taskable task : tasks){
            usedIcebergs.add(task.getActor());
        }
        return usedIcebergs;
    }
}
