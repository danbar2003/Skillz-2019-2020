package bots;

import penguin_game.*;

public class Utils {
    /**
     * @param object: Some object in the game.
     * @param arr:    Array of objects (doesn't have to be the same type)
     * @param <T>     :nothing.
     * @return : return the closest object from arr to object parameter, or null if arr length is below 1
     */
    public static <T extends GameObject> T closestTo(GameObject object, T[] arr) {
        if (arr.length > 0) {
            int distance = object.__distance(arr[0]);
            T closestObj = arr[0];
            for (T temp : arr) {
                if (temp.__distance(object) < distance) {
                    distance = temp.__distance(object);
                    closestObj = temp;
                }
            }
            return closestObj;
        }
        return null;
    }

    public static int howManyInRange(GameObject GameObject, GameObject[] objects, int range) {
        int counter = 0;
        for (GameObject temp : objects) {
            if (GameObject.__distance(temp) <= range)
                counter++;
        }
        return counter;
    }

    public static <T extends GameObject> boolean isInArray(T object, T[] array) {
        for (T arrayObject : array) {
            if (arrayObject == object)
                return true;
        }
        return false;
    }

    public static PenguinGroup[] helpsIceberg(Game game, Iceberg enemy_iceberg) {
        PenguinGroup[] helpingIceberg = new PenguinGroup[game.getEnemyPenguinGroups().length];
        for (int i = 0; i < game.getEnemyPenguinGroups().length; i++) {
            if (game.getEnemyPenguinGroups()[i].destination == enemy_iceberg) {
                for (int z = 0; z < helpingIceberg.length; z++) {
                    if (helpingIceberg[z] == null) {
                        helpingIceberg[z] = game.getEnemyPenguinGroups()[i];
                        z = helpingIceberg.length;
                    }
                }
            }
        }
        int c = 0;
        for (int a = 0; a < helpingIceberg.length; a++) {
            if (helpingIceberg[a] == null) {
                a = helpingIceberg.length;
            } else {
                c++;
            }
        }
        PenguinGroup[] helpingIceberg2 = new PenguinGroup[c];
        for (int a = 0; a < c; a++) {
            helpingIceberg2[a] = helpingIceberg[a];
        }
        return helpingIceberg2;
    }

    public static int amountOfPenguinsComing(Game game, Iceberg myIceberg) {
        int penguinAmount = 0;
        for (PenguinGroup temp : game.getEnemyPenguinGroups()) {
            if (temp.destination == myIceberg)
                penguinAmount += temp.penguinAmount;
        }
        return penguinAmount;
    }

    public static void defendIceberg(Game game, Iceberg iceberg) {
        int attackingPenguins = amountOfPenguinsComing(game, iceberg);
        if (iceberg.penguinAmount < attackingPenguins) {
            for (Iceberg temp : game.getMyIcebergs())
                if (temp.__distance(iceberg) < 1000 || closestTo(iceberg, game.getMyIcebergs()) == temp)
                    temp.sendPenguins(iceberg, temp.penguinAmount / 2);
        }
    }


    public static Iceberg[] getIcebergsUnderAttack(Game game) {
        Iceberg[] myIcebergs = game.getMyIcebergs();
        PenguinGroup[] enemyPenguinGroups = game.getEnemyPenguinGroups();
        Iceberg[] enemyDestinations = new Iceberg[enemyPenguinGroups.length];
        int counter = 0;
        for (PenguinGroup penguinGroup : enemyPenguinGroups) {
            enemyDestinations[counter] = penguinGroup.destination;
            counter++;
        }
        counter = 0;
        Iceberg[] icebergsUnderAttack = new Iceberg[enemyDestinations.length];
        for (Iceberg destination : enemyDestinations) {
            for (int j = 0; j < myIcebergs.length; j++) {
                if (destination == myIcebergs[j] && !isInArray(myIcebergs[j], icebergsUnderAttack)) {
                    icebergsUnderAttack[counter] = destination;
                    System.out.println("help me, turn:" + game.turn + " id:" + destination.id);
                    counter++;
                    break;
                }
            }
        }
        return icebergsUnderAttack;
    }

    public static PenguinGroup[] getHelpingPenguin(Game game, Iceberg enemy) {
        PenguinGroup[] enemyPenguinGroups = game.getEnemyPenguinGroups();
        int counter = 0;
        for (PenguinGroup enemyPenguinGroup : enemyPenguinGroups) {
            if (enemyPenguinGroup.destination == enemy) {
                counter++;
            }
        }
        if (counter == 0)
            return null;
        PenguinGroup[] helpingPenguinGroup = new PenguinGroup[counter];
        counter = 0;
        for (PenguinGroup enemyPenguinGroup : enemyPenguinGroups) {
            if (enemyPenguinGroup.destination == enemy && !isInArray(enemyPenguinGroup, helpingPenguinGroup)) {
                helpingPenguinGroup[counter] = enemyPenguinGroup;
                counter++;
            }
        }
        return helpingPenguinGroup;
    }

    public static int minimumPenguinAmountToWin(Game game, Iceberg myIceberg, Iceberg enemyIceberg) {
        int myTurnsTillArrival = myIceberg.getTurnsTillArrival(enemyIceberg);
        int numPenguins = enemyIceberg.penguinAmount;
        numPenguins += enemyIceberg.penguinsPerTurn * myTurnsTillArrival;
        if (helpsIceberg(game, enemyIceberg) != null) {
            PenguinGroup[] helpingIcebergs = helpsIceberg(game, enemyIceberg);
            for (int i = 0; i < helpingIcebergs.length; i++) {
                if (helpingIcebergs[i].turnsTillArrival <= myTurnsTillArrival) {
                    numPenguins += helpingIcebergs[i].penguinAmount;
                } else {
                    numPenguins += helpingIcebergs[i].penguinAmount - (helpingIcebergs[i].turnsTillArrival - myTurnsTillArrival);
                }
            }
            for (PenguinGroup enemy : game.getEnemyPenguinGroups()) {
                if (enemy.destination == myIceberg && enemy.source == enemyIceberg) {
                    numPenguins += enemy.penguinAmount;
                }
            }
        }
        System.out.println("the number of penguins: " + numPenguins);
        return numPenguins + 1;
    }

    public static int minimumPenguinAmountToWin1(Game game, Iceberg myIceberg, Iceberg enemyIceberg) {
        int numPenguins = myIceberg.getTurnsTillArrival(enemyIceberg) + enemyIceberg.penguinAmount +
                enemyIceberg.penguinsPerTurn * myIceberg.getTurnsTillArrival(enemyIceberg);
        if (helpsIceberg(game, enemyIceberg) != null) {
            PenguinGroup[] helpingIcebergs = helpsIceberg(game, enemyIceberg);
            for (int i = 0; i < helpingIcebergs.length; i++) {
                if (helpingIcebergs[i].turnsTillArrival <= myTurnsTillArrival) {
                    numPenguins += helpingIcebergs[i].penguinAmount;
                } else {
                    numPenguins += helpingIcebergs[i].penguinAmount - (helpingIcebergs[i].turnsTillArrival - myTurnsTillArrival);
                }
            }
            for (PenguinGroup enemy : game.getEnemyPenguinGroups()) {
                if (enemy.destination == myIceberg && enemy.source == enemyIceberg) {
                    numPenguins += enemy.penguinAmount;
                }
            }
        }
        System.out.println("the number of penguins: " + numPenguins);
        return numPenguins + 1;
    }

    public static Iceberg weakestIceBerg(Iceberg[] icebergs) {
        int penguins = icebergs[0].penguinAmount;
        int pos = 0;
        for (int i = 1; i < icebergs.length; i++) {
            if (icebergs[i].penguinAmount < penguins) {
                penguins = icebergs[i].penguinAmount;
                pos = i;
            }
        }
        return icebergs[pos];
    }
}

