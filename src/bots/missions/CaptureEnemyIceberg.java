package bots.missions;

import bots.Constant;
import bots.wrapper.MyIceberg;

public class CaptureEnemyIceberg implements Mission{

    private MyIceberg target;
    private State state;

    public CaptureEnemyIceberg(MyIceberg target){
        this.target = target;
    }

    /**
     *
     * @return
     */
    public int benefit(){
        return 0;
    }
}
