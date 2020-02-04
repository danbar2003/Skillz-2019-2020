package bots.tasks;

import java.util.LinkedList;
import java.util.List;

public class AdvancedNode<T> {

    private T value;
    private List<AdvancedNode<T>> nextNodes;

    public AdvancedNode(T value){
        this.value = value;
        this.nextNodes = new LinkedList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<AdvancedNode<T>> getNextNodes() {
        return nextNodes;
    }

    public void add(AdvancedNode<T> advancedNode){
        this.nextNodes.add(advancedNode);
    }
}
