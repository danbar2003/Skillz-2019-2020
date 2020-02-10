package bots.missions;


import bots.Constant;
import bots.MissionManager;
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
        return target.iceberg.penguinsPerTurn * Constant.Game.turn;
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

    @Override
    public String toString(){
        return "Capture| Attacked: " + target.iceberg ;
    }
}
