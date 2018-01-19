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
import static pogocpcalc.Database.levelMultiplier;

/**
 *
 * @author nieznosnybahor
 */
public abstract class Database {

    final static int NUMBER_OF_POKEMON = 22;

    static Map<Double, Double> levelMultiplier = new HashMap<Double, Double>();
    static String pokemonNamesComboBox[] = new String[NUMBER_OF_POKEMON];
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
            if (       Integer.parseInt(data[0]) > 0 && Integer.parseInt(data[0]) <= 510
                    && Integer.parseInt(data[1]) > 0 && Integer.parseInt(data[1]) <= 510
                    && Integer.parseInt(data[2]) > 0 && Integer.parseInt(data[2]) <= 510
                    && Integer.parseInt(data[3]) >= 0 && Integer.parseInt(data[3]) <= 15
                    && Integer.parseInt(data[4]) >= 0 && Integer.parseInt(data[4]) <= 15
                    && Integer.parseInt(data[5]) >= 0 && Integer.parseInt(data[5]) <= 15
                    && Double.parseDouble(data[6]) >= 1.0 && Double.parseDouble(data[6]) <= 40.0) 
            {    
                return true;
            } 
            else
            {
                return false;
            }
        } 
        catch (Exception e) {
            System.out.println("Wrong data");
            return false;
        }
    }
    static String catchData(String[] data) {
        
        double coe_att = Integer.parseInt(data[0]) + Integer.parseInt(data[3]);
        double coe_def = Math.sqrt(Integer.parseInt(data[1]) + Integer.parseInt(data[4]));
        double coe_sta = Math.sqrt(Integer.parseInt(data[2]) + Integer.parseInt(data[5]));
        double coe_lvl = Math.pow(levelMultiplier.get(Double.parseDouble(data[6])), 2);
        double wynik   = ((coe_att * coe_def * coe_sta) / 10) * coe_lvl;
        int wynikInt = (int)wynik;
        
        
        return Integer.toString(wynikInt);
    }

}
