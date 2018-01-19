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

    final static int NUMBER_OF_POKEMON = 19;

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
        InputStream stream = Database.class.getResourceAsStream("LevelMultiplier");
        Scanner sc = new Scanner(stream);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("\t");
            levelMultiplier.put(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        }
        stream.close();
    }

    private static void loadStats() throws IOException {
        InputStream stream = Database.class.getResourceAsStream("Pokemon");
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
        InputStream stream = Database.class.getResourceAsStream("Pokemon");
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

}
