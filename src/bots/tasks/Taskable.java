package bots.tasks;

import bots.wrapper.MyIceberg;

public interface Taskable {

    MyIceberg getActor();

    void act();

    int loss();

}
