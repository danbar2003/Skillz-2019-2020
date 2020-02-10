package bots.tasks;


import bots.wrapper.MyIceberg;

public interface Taskable {

    MyIceberg getActor();

    MyIceberg getTarget();

    int penguins();

    void act();

    int loss();
}
