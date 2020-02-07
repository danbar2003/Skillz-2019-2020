package bots.missions;


import bots.MissionManager;
import bots.Utils;
import bots.tasks.TaskGroup;
import bots.tasks.Taskable;
import bots.wrapper.MyIceberg;
import bots.tasks.TaskGroup;

import java.util.List;

public class CaptureIceberg implements Mission {

    private MyIceberg target;
    private List<TaskGroup> waysToExecute;
    private State state;

    public CaptureIceberg(MyIceberg target){
        this.target = target;
    }

    @Override
    public int benefit(){
        return Constant.Game.turnsLeft * target.iceberg.penguinsPerTurn;
    }

    @Override
    public List<TaskGroup> getWaysToExecute() {
        return waysToExecute;
    }

    @Override
    public String getType() {
        return "CaptureIceberg" + target.iceberg.toString();
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
