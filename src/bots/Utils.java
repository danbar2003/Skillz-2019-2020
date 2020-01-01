package bots;

import bots.wrapper.*;
import penguin_game.*;

import java.util.*;

public class Utils {

    private Utils(){
    }

    public static void setAvaiablePenguins(MyGame game, List<MyIceberg> myIcebergs){
        for (MyIceberg iceberg : myIcebergs){
            if (iceberg.canDefendItself(game))
                iceberg.savePenguins(0);
            else{
                iceberg.getAttackingPenguinGroupsToIceberg(game);
            }
        }
    }
}
