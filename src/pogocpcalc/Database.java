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
    
    //
    // POLA:
    //
    
    final static int NUMBER_OF_POKEMON = loadNumberOfPokemon();
    static Map<Double, Double> levelMultiplier = new HashMap<Double, Double>();
    
    
    // Dane pokemonów - najpierw String, pozniej obrobka do konkretnych typow danych
    static String[][] rawPokemonInput = new String[NUMBER_OF_POKEMON][7];
    static int    pokemonStats[][] = new int[NUMBER_OF_POKEMON][4]; // [id/sta/att/def]
    static String pokemonNames[]   = new String[NUMBER_OF_POKEMON];  
    static String pokemonTypes[][] = new String[NUMBER_OF_POKEMON][2];
    
    // Dane atakow fast i charge
    static Map<String, Double[]> attacksFast   = new HashMap<String, Double[]>();
    static Map<String, Double[]> attacksCharge = new HashMap<String, Double[]>();
    //static Map<String, Types> attacksTypes = new HashMap<String, Types>();
    
    // Nazwy pokemonow przystosowane do Comboboxow
    static String pokemonNamesComboBox[] = new String[NUMBER_OF_POKEMON];
    
    //
    // METODY:
    //

    static void load() throws IOException {
        
        loadLevelMultipliers();     // +
        loadRawPokemonSpec();       // +
            extractStats();         // +
            extractNames();         // +
            extractNamesComboBox(); // +
            extractTypes();         // +
    }
    private static int loadNumberOfPokemon() {
        int nope = 0;
        InputStream stream = Database.class.getResourceAsStream("/database/Pokemon_Stats");
        Scanner sc = new Scanner(stream);
        while(sc.hasNextLine())
        {
            nope++;
            sc.nextLine();
        }
        
        return nope;
    }
    
    // Funkcje ładowania i ekstrakcji danych wykonywanych przez konstruktor
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
    private static void loadRawPokemonSpec() {
        InputStream stream = Database.class.getResourceAsStream("/database/Pokemon_Stats");
        Scanner sc = new Scanner(stream);
        int i = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("\t");
            rawPokemonInput[i][0] = parts[0];   // id
            rawPokemonInput[i][1] = parts[1];   // sta
            rawPokemonInput[i][2] = parts[2];   // att
            rawPokemonInput[i][3] = parts[3];   // def
            rawPokemonInput[i][4] = parts[4];   // name
            rawPokemonInput[i][5] = parts[5];   // type1
            rawPokemonInput[i][6] = parts[6];   // type2
            i++;
        }
    }
    private static void extractStats() {
        for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
            for (int j = 0; j < 4; j++) {
                pokemonStats[i][j] = Integer.parseInt(rawPokemonInput[i][j]);
            }
        }
    }
    private static void extractNames() {
        for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
            pokemonNames[i] = rawPokemonInput[i][4];
        }
    }
    private static void extractNamesComboBox(){
        for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
            pokemonNamesComboBox[i] = pokemonStats[i][0] +". "+ pokemonNames[i];
        }
    }
    private static void extractTypes() {
            for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
                for (int j = 0; j < 2; j++) {
                    pokemonTypes[i][j] = rawPokemonInput[i][j+5];
            }
        }
    }
    
    // Panel CP Calc
    static boolean  checkData(String[] data) {

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
    static String   catchData(String[] data) {

        double coe_att = Integer.parseInt(data[0]) + Integer.parseInt(data[3]);
        double coe_def = Math.sqrt(Integer.parseInt(data[1]) + Integer.parseInt(data[4]));
        double coe_sta = Math.sqrt(Integer.parseInt(data[2]) + Integer.parseInt(data[5]));
        double coe_lvl = Math.pow(levelMultiplier.get(Double.parseDouble(data[6])), 2);
        double wynik = ((coe_att * coe_def * coe_sta) / 10) * coe_lvl;
        int wynikInt = (int) wynik;
        if(wynikInt < 10)
        {
            wynikInt = 10;
        }
        return Integer.toString(wynikInt);
    }
    
    // Panel Konwersji statystyk
    static String[] convertStats(int hp, int att, int spatt, int def, int spdef, int speed)     {
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
        //System.out.println(go_att);
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
    
    // Panel Konwersji cukierkow
    static int      calcNoPokemons(String Candies, String Cost, boolean transfer)               {
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
    static int      calcNoCandies(String Pokemon, String Cost, boolean transfer)                {
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
    


    
    static String[] getPokemonInfo(int index) {
        String data[] = new String[5];     // sta/att/def / type1 / type2
        data[0] = Integer.toString(pokemonStats[index][1]);
        data[1] = Integer.toString(pokemonStats[index][2]);
        data[2] = Integer.toString(pokemonStats[index][3]);
        data[3] = pokemonTypes[index][0];
        data[4] = pokemonTypes[index][1];
        
        
        
        return data;
    }
    
    static String[] getAttackInfo()    {
        return null;
    }
}
