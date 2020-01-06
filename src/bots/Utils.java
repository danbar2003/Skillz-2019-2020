package bots;

import bots.wrapper.MyGame;
import bots.wrapper.MyIceberg;
import penguin_game.Iceberg;

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

    public static void setupIcebergPenguins(MyGame game) {
        for (MyIceberg iceberg : game.getMyIcebergs()) {
            iceberg.savePenguins(iceberg.amountToDefend(game));
        }

    }

    /**
     * attckers - friendly (ours)
     * target - enemy iceberg
     *
     * @param game      - game info
     * @param attackers - contributing icebergs to attack
     * @param target    - enemy iceberg
     * @return - map of icebergs who contribute to the attack as keys and
     * penguin amount that each iceberg is contributing as value
     */
    public static Map<MyIceberg, Integer> penguinsFromEachIceberg(MyGame game, List<MyIceberg> attackers, MyIceberg target) {
        //TODO create this function
        Map<MyIceberg, Integer> penguinsFromIcebergs = new HashMap<>();
    }

    /**
     * @param game - game info
     * @return - all options to attack each enemy iceberg
     * key - target (enemy iceberg)
     * value - list of options to attack the iceberg
     * value(Map):
     * key - attacking Iceberg
     * value - penguins amount
     */
    public static Map<MyIceberg, Set<Map<MyIceberg, Integer>>> optionsToAttack(MyGame game) {
        //TODO - create this function after you finished penguinsFromEachIceberg
    }
}