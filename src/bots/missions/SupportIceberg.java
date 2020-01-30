package bots.missions;

import bots.MissionManager;
import bots.tasks.TaskGroup;
import bots.tasks.Taskable;
import bots.wrapper.MyIceberg;

import java.util.Set;

public class SupportIceberg implements Mission {

    private MyIceberg supportedIceberg;
    private Set<TaskGroup> waysToExecute;
    private State state;

    public SupportIceberg(MyIceberg supportedIceberg){
        this.supportedIceberg = supportedIceberg;
    }

    @Override
    public int benefit(){
        return 0;
    }

    @Override
    public MyIceberg getTarget(){
        return this.supportedIceberg;
    }

    @Override
    public Set<TaskGroup> getWaysToExecute() {
        return null;
    }

    @Override
    public void calcWaysToExecute() {
        this.waysToExecute = MissionManager.waysToExecute(this);
    }

    @Override
    public String getType() {
        return "SupportIceberg";
    }

    @Override
    public void setState(State state){
        this.state = state;
    }
}
