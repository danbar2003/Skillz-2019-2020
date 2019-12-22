package bots.wrapper;

import penguin_game.*;

public class MyIceberg{
    public Iceberg iceberg;
    public int savedPenguins;

    public MyIceberg(Iceberg iceberg){
        this.iceberg = iceberg;
        savedPenguins = 0;
    }

    public <T extends GameObject> T closestTo(T[] arr){
        if (arr.length > 0) {
            int min = this.iceberg.__distance(arr[0]);
            T obj = arr[0];
            for (T temp : arr) {
                if (this.iceberg.__distance(temp) < min){
                    obj = temp;
                    min = this.iceberg.__distance(temp);
                }
            }
            return obj;
        }
        return null;
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
