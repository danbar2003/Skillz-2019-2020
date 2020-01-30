package bots.missions;

import bots.Constant;
import bots.MissionManager;
import bots.tasks.TaskGroup;
import bots.tasks.Taskable;
import bots.wrapper.MyIceberg;
import haxe.root.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CaptureIceberg implements Mission{

    private MyIceberg target;
    private Set<TaskGroup> waysToExecute;
    private State state;

    public CaptureIceberg(MyIceberg target){
        this.target = target;
    }

    @Override
    public int benefit(){ return 0; }

    @Override
    public Set<TaskGroup> getWaysToExecute() {
        return waysToExecute;
    }

    @Override
    public String getType() {
        return "CaptureIceberg";
    }

    @Override
    public void calcWaysToExecute() {
        this.waysToExecute = MissionManager.waysToExecute(this);
    }

    @Override
    public MyIceberg getTarget(){
        return this.target;
    }

    @Override
    public void setState(State state){
        this.state = state;
    }
}
