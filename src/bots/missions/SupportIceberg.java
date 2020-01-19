package bots.missions;

import bots.wrapper.MyIceberg;

public class SupportIceberg implements Mission {

    private MyIceberg supportedIceberg;
    private State state;

    public SupportIceberg(MyIceberg supportedIceberg){
        this.supportedIceberg = supportedIceberg;
    }

    public int benefit(){
        return 0;
    }

    public MyIceberg getTarget(){
        return this.supportedIceberg;
    }

    public void setState(State state){
        this.state = state;
    }
}
