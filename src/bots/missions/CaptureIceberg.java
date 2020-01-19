package bots.missions;

import bots.Constant;
import bots.wrapper.MyIceberg;

public class CaptureIceberg implements Mission{

    private MyIceberg target;
    private State state;

    public CaptureIceberg(MyIceberg target){
        this.target = target;
    }

    /**
     *
     * @return
     */
    public int benefit(){
        return 0;
    }

    public MyIceberg getTarget(){
        return this.target;
    }

    public void setState(State state){
        this.state = state;
    }
}
