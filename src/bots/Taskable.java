package bots;


import bots.MyIceberg;

public interface Taskable {

    MyIceberg getActor();

    MyIceberg getTarget();

    int penguins();

    void act();

    int loss();
}
