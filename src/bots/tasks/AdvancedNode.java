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

    public AdvancedNode(){
        this.value = null;
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

    public void setNextNodes(List<AdvancedNode<T>> nextNodes) {
        this.nextNodes = nextNodes;
    }
}
