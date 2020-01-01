package bots;

import bots.wrapper.MyGame;
import bots.wrapper.MyIceberg;

import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static List<MyIceberg> myThreatenedIcebergs(MyGame game) {
        List<MyIceberg> threatenedIcebergs = new LinkedList<>();
        for (MyIceberg iceberg : game.getMyIcebergs()) {
            if (iceberg.amountToDefend(game) <= 0)
                threatenedIcebergs.add(iceberg);
        }
        return threatenedIcebergs;
    }

    public static void setupIcebergPenguins(MyGame game){
        for (MyIceberg iceberg : game.getMyIcebergs()){
            iceberg.savePenguins(iceberg.amountToDefend(game));
        }
    }
}
