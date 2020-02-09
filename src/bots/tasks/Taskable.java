package bots.tasks;


import bots.wrapper.MyIceberg;

public interface Taskable {

    MyIceberg getActor();

    MyIceberg getTarget();

    void act();

    int loss();
}
