package bots;

import bots.wrapper.MyGame;
import bots.wrapper.MyIceberg;
import penguin_game.Game;
import penguin_game.Iceberg;
import penguin_game.MapObject;

import java.util.*;

public class Utils {

    public static List<MyIceberg> convertToMyIcebergType(Iceberg[] arr) {
        LinkedList<MyIceberg> myIcebergs = new LinkedList<>();
        for (Iceberg iceberg : arr) {
            myIcebergs.add(new MyIceberg(iceberg));
        }
        return myIcebergs;
    }

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

    /**
     * attckers - friendly (ours)
     * target - enemy iceberg
     *
     * @param game - game info
     * @param attackers - contributing icebergs to attack
     * @param target - enemy iceberg
     * @return - map of icebergs who contribute to the attack as keys and
     * penguin amount that each iceberg is contributing as value
     */
    public static Map<MyIceberg, Integer> penguinsFromEachIceberg(MyGame game, List<MyIceberg> attackers, MyIceberg target){
        //TODO - create this function
    }
    /**
     *
     * @param game - game info
     * @return - all opstions to attack each enemy iceberg
     *           key - target (enemy iceberg)
     *           value - list of opstions to attack the iceberg
     *                  value(Map):
     *                      key - attacking Iceberg
     *                      value - penguins amount
     */
    public static Map<MyIceberg, List<Map<MyIceberg, Integer>>> optionsToAttack(MyGame game){
        //TODO - create this function after you finished
    }
}
