/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogocpcalc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JTextField;
import static pogocpcalc.Database.levelMultiplier;

/**
 *
 * @author nieznosnybahor
 */
public abstract class Database {

    final static int NUMBER_OF_POKEMON = 28;

    static Map<Double, Double> levelMultiplier = new HashMap<Double, Double>();
    //static String pokemonNamesComboBox[] = new String[NUMBER_OF_POKEMON];
    static String pokemonNames[] = new String[NUMBER_OF_POKEMON];
    static int pokemonStats[][] = new int[NUMBER_OF_POKEMON][4]; // [id/sta/att/def]

    static void load() throws IOException {
        loadLevelMultipliers();
        loadStats();
        loadNames();

    }

    private static void loadLevelMultipliers() throws IOException {
        InputStream stream = Database.class.getResourceAsStream("/database/LevelMultiplier");
        Scanner sc = new Scanner(stream);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("\t");
            levelMultiplier.put(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        }
        stream.close();
    }

    private static void loadStats() throws IOException {
        InputStream stream = Database.class.getResourceAsStream("/database/Pokemon");
        Scanner sc = new Scanner(stream);
        int i = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("\t");
            pokemonStats[i][0] = Integer.parseInt(parts[0]);
            pokemonStats[i][1] = Integer.parseInt(parts[1]);
            pokemonStats[i][2] = Integer.parseInt(parts[2]);
            pokemonStats[i][3] = Integer.parseInt(parts[3]);
            i++;
        }

        stream.close();

    }

    private static void loadNames() throws IOException {
        InputStream stream = Database.class.getResourceAsStream("/database/Pokemon");
        Scanner sc = new Scanner(stream);
        int i = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("\t");
            //for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
            pokemonNames[i] = parts[4];
            //pokemonNamesComboBox[i] = i + ". " + parts[4];
            i++;
            //}

        }
        stream.close();
    }

    static boolean checkData(String[] data) {

        try {
            if (Integer.parseInt(data[0]) > 0 && Integer.parseInt(data[0]) <= 510
                    && Integer.parseInt(data[1]) > 0 && Integer.parseInt(data[1]) <= 510
                    && Integer.parseInt(data[2]) > 0 && Integer.parseInt(data[2]) <= 510
                    && Integer.parseInt(data[3]) >= 0 && Integer.parseInt(data[3]) <= 15
                    && Integer.parseInt(data[4]) >= 0 && Integer.parseInt(data[4]) <= 15
                    && Integer.parseInt(data[5]) >= 0 && Integer.parseInt(data[5]) <= 15
                    && Double.parseDouble(data[6]) >= 1.0 && Double.parseDouble(data[6]) <= 40.0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Konsola.print("Wrong data");
            return false;
        }
    }

    static String catchData(String[] data) {

        double coe_att = Integer.parseInt(data[0]) + Integer.parseInt(data[3]);
        double coe_def = Math.sqrt(Integer.parseInt(data[1]) + Integer.parseInt(data[4]));
        double coe_sta = Math.sqrt(Integer.parseInt(data[2]) + Integer.parseInt(data[5]));
        double coe_lvl = Math.pow(levelMultiplier.get(Double.parseDouble(data[6])), 2);
        double wynik = ((coe_att * coe_def * coe_sta) / 10) * coe_lvl;
        int wynikInt = (int) wynik;

        return Integer.toString(wynikInt);
    }

    static String[] convertStats(int hp, int att, int spatt, int def, int spdef, int speed) {
        double speedCoeff = 1.0 + ((speed - 75) * 0.002);
        double go_sta;
        go_sta = hp * 2;
        //
        double go_att;
        int up, down;
        if (att >= spatt) {
            up = att;
            down = spatt;
        } else {
            up = spatt;
            down = att;
        }
        go_att = ((up * 0.875) + (down * 0.125)) * 2;
        go_att = Math.round(go_att);
        System.out.println(go_att);
        go_att *= speedCoeff;
        go_att = Math.round(go_att);

        //
        double go_def;
        up = 0;
        down = 0;
        if (def >= spdef) {
            up = def;
            down = spdef;
        } else {
            up = spdef;
            down = def;
        }
        go_def = ((up * 0.875) + (down * 0.125)) * 2;
        go_def = Math.round(go_def);
        go_def *= speedCoeff;
        go_def = Math.round(go_def);

//        System.out.println("go_att: " + go_att);
//        System.out.println("go_def: " + go_def);
//        System.out.println("go_sta: " + go_sta);
        String answer[] = {Double.toString(go_att), Double.toString(go_def), Double.toString(go_sta)};

        return answer;
    }

    static int calcNoPokemons(String Candies, String Cost, boolean transfer) {
        int cost = Integer.parseInt(Cost);
        int candies = Integer.parseInt(Candies);
        if(transfer)
        {
            return (int)(candies / (cost - 2));
        }
        else
        {
            return (int)(candies / (cost - 1));
        }
        }

    static int calcNoCandies(String Pokemon, String Cost, boolean transfer) {
        int pokemon = Integer.parseInt(Pokemon);
        int cost = Integer.parseInt(Cost);
        if(transfer)
        {
            return (int)((pokemon * (cost - 2)) + 2);
        }
        else
        {
            return (int)((pokemon * (cost - 1)) + 1);
        }
    }


}
