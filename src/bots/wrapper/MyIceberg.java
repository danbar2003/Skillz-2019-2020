package bots.wrapper;

import penguin_game.*;

public class MyIceberg{
    public Iceberg iceberg;
    public int savedPenguins;

    public MyIceberg(Iceberg iceberg){
        this.iceberg = iceberg;
        savedPenguins = 0;
    }

    public void savePenguins(int penguinsAmount){
        this.savedPenguins = penguinsAmount;
    }

    public void freePenguins(int penguinsAmount){
        this.savedPenguins = this.savedPenguins - penguinsAmount;
    }

    public void freeAllPenguins(){
        this.savedPenguins = 0;
    }
}
