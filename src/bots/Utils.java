package bots;

import bots.wrapper.*;
import penguin_game.*;

import java.util.*;

public class Utils {

    private Utils(){
    }

    private static List<Integer> sortIcebergsByStrength(MyGame game , List<MyIceberg> myIcebergs){
        List<Integer> strengthOfIcebergs = new LinkedList<>();
        for (int i = 0; i < myIcebergs.size() ; i++) {
            strengthOfIcebergs.add(myIcebergs.get(i).strength(game));
        }
            strengthOfIcebergs.sort(Integer::compareTo);
        return strengthOfIcebergs;
    }

    public static List<MyIceberg> icebergsStrengthList(MyGame game){
        List<MyIceberg> myIcebergsByStrength = new LinkedList<>();
        myIcebergsByStrength.addAll(game.getMyIcebergs());
        myIcebergsByStrength.sort();
        return myIcebergsByStrength;
    }
}










