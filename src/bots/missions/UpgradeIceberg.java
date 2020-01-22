package bots.missions;

import bots.Constant;
import bots.MissionManager;
import bots.tasks.Taskable;
import bots.wrapper.MyIceberg;

import java.util.Set;

public class UpgradeIceberg implements Mission {

    private MyIceberg iceberg; // upgrading iceberg
    private Set<Set<Taskable>> waysToExecute;
    private State state;

    public UpgradeIceberg(MyIceberg iceberg){
        this.iceberg = iceberg;
    }

    @Override
    public int benefit(){
        return 0;
    }

    @Override
    public MyIceberg getTarget(){
        return this.iceberg;
    }

    @Override
    public Set<Set<Taskable>> getWaysToExecute() {
        return waysToExecute;
    }

    @Override
    public void calcWaysToExecute() {
        this.waysToExecute = MissionManager.waysToExecute(this);
    }

    @Override
    public String getType() {
        return "UpgradeIceberg";
    }

    @Override
    public void setState(State state){
        this.state = state;
    }
}