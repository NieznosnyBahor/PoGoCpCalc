   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogocpcalc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    final static int NUMBER_OF_FASTATTACKS = loadNumberOfFastAttacks();
    final static int NUMBER_OF_CHARGEATTACKS = loadNumberOfChargeAttacks();
    static Map<Double, Double> levelMultiplier = new HashMap<Double, Double>();

    // Dane pokemonów
    static ArrayList<Pokemon> PokemonList = new ArrayList<Pokemon>();

    // Dane atakow fast i charge
//    static Map<String, Double[]> attacksFast = new HashMap<String, Double[]>();
//    static Map<String, Double[]> attacksCharge = new HashMap<String, Double[]>();
    //static Map<String, Types> attacksTypes = new HashMap<String, Types>();
    static ArrayList<AttackCharge> AttackChargeList = new ArrayList<AttackCharge>();
    static ArrayList<AttackFast> AttackFastList = new ArrayList<AttackFast>();

    // Nazwy przystosowane do Comboboxow
    static String pokemonNamesComboBox[] = new String[NUMBER_OF_POKEMON];
    static String attackFastComboBox[] = new String[NUMBER_OF_FASTATTACKS];
    static String attackChargeComboBox[] = new String[NUMBER_OF_CHARGEATTACKS];

    //
    //
    // METODY:
    //
    //
    static void load() throws IOException {

        loadLevelMultipliers();     // +
        loadPokemonList();          // +
//          loadPokemonNames();
//          loadPokemonStats();
//          loadPokemonTypes();
//          loadPokemonAttacks();
        loadPokemonNamesComboBox(); // +
        loadAttacksFast();          // +
        loadAttacksCharge();        // +
        loadAttacksCombobox();      // +
        // show666();               // +
    }

    private static int loadNumberOfPokemon() {
        int nope = 0;
        InputStream stream = Database.class.getResourceAsStream("/database/Pokemon_Stats");
        Scanner sc = new Scanner(stream);
        while (sc.hasNextLine()) {
            nope++;
            sc.nextLine();
        }
        sc.close();
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
        sc.close();
    }

    private static void loadPokemonNamesComboBox() {
        for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
            pokemonNamesComboBox[i] = PokemonList.get(i).number + ". " + PokemonList.get(i).name;
        }
    }
    // <editor-fold defaultstate="collapsed" desc=" ${Wykomentowane} ">
//    private static void loadRawPokemonSpec() {
//        InputStream stream = Database.class.getResourceAsStream("/database/Pokemon_Stats");
//        Scanner sc = new Scanner(stream);
//        int i = 0;
//        while (sc.hasNext()) {
//            String line = sc.nextLine();
//            String parts[] = line.split("\t");
//            rawPokemonInput[i][0] = parts[0];   // id
//            rawPokemonInput[i][1] = parts[1];   // sta
//            rawPokemonInput[i][2] = parts[2];   // att
//            rawPokemonInput[i][3] = parts[3];   // def
//            rawPokemonInput[i][4] = parts[4];   // name
//            rawPokemonInput[i][5] = parts[5];   // type1
//            rawPokemonInput[i][6] = parts[6];   // type2
//            i++;
//        }
//    }
//    private static void extractStats() {
//        for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
//            for (int j = 0; j < 4; j++) {
//                pokemonStats[i][j] = Integer.parseInt(rawPokemonInput[i][j]);
//            }
//        }
//    }
//    private static void extractNames() {
//        for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
//            pokemonNames[i] = rawPokemonInput[i][4];
//        }
//    }

//    private static void extractTypes() {
//            for (int i = 0; i < NUMBER_OF_POKEMON; i++) {
//                for (int j = 0; j < 2; j++) {
//                    pokemonTypes[i][j] = rawPokemonInput[i][j+5];
//            }
//        }
//    }
// </editor-fold>
    // Panel CP Calc
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
        if (wynikInt < 10) {
            wynikInt = 10;
        }
        return Integer.toString(wynikInt);
    }

    // Panel Konwersji statystyk
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
    static int calcNoPokemons(String Candies, String Cost, boolean transfer) {
        int cost = Integer.parseInt(Cost);
        int candies = Integer.parseInt(Candies);
        if (transfer) {
            return (int) Math.ceil((candies-2) / (cost - 2));
        } else {
            return (int) Math.ceil((candies-1) / (cost - 1));
        }
    }

    static int calcNoCandies(String Pokemon, String Cost, boolean transfer) {
        int pokemon = Integer.parseInt(Pokemon);
        int cost = Integer.parseInt(Cost);
        if (transfer) {
            return (int) ((pokemon * (cost - 2)) + 2);
        } else {
            return (int) ((pokemon * (cost - 1)) + 1);
        }
    }

    static String[] getPokemonInfo(int index) {
        String data[] = new String[5];     // sta/att/def / type1 / type2
        data[0] = Integer.toString(PokemonList.get(index).stat_sta);
        data[1] = Integer.toString(PokemonList.get(index).stat_att);
        data[2] = Integer.toString(PokemonList.get(index).stat_def);

        try {
            data[3] = PokemonList.get(index).type1.toString();
        } catch (Exception e) {
            data[3] = "null";
        }

        try {
            data[4] = PokemonList.get(index).type2.toString();
        } catch (Exception e) {
            data[4] = "null";
        }

        return data;
    }

    static String[] getAttackInfo() {
        return null;
    }

    private static void loadPokemonList() {

        InputStream stream = Database.class.getResourceAsStream("/database/Pokemon_Stats");
        Scanner sc = new Scanner(stream);
        int i = 0;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("\t");

            int id = i;
            int number = Integer.parseInt(parts[0]);
            String name = parts[4];
            int stat_att = Integer.parseInt(parts[2]);
            int stat_def = Integer.parseInt(parts[3]);
            int stat_sta = Integer.parseInt(parts[1]);
            EnumTypes type1 = chooseType(parts[5]);
            EnumTypes type2 = chooseType(parts[6]);
            PokemonList.add(new Pokemon(id, number, name, stat_att, stat_def, stat_sta, type1, type2));
            i++;
        }

    }

    private static EnumTypes chooseType(String part) {
        EnumTypes type = null;
        if (part.equals(EnumTypes.Bug.toString())) {
            type = EnumTypes.Bug;
        } else if (part.equals(EnumTypes.Dark.toString())) {
            type = EnumTypes.Dark;
        } else if (part.equals(EnumTypes.Dragon.toString())) {
            type = EnumTypes.Dragon;
        } else if (part.equals(EnumTypes.Electric.toString())) {
            type = EnumTypes.Electric;
        } else if (part.equals(EnumTypes.Fairy.toString())) {
            type = EnumTypes.Fairy;
        } else if (part.equals(EnumTypes.Fight.toString())) {
            type = EnumTypes.Fight;
        } else if (part.equals(EnumTypes.Flying.toString())) {
            type = EnumTypes.Flying;
        } else if (part.equals(EnumTypes.Fire.toString())) {
            type = EnumTypes.Fire;
        } else if (part.equals(EnumTypes.Ghost.toString())) {
            type = EnumTypes.Ghost;
        } else if (part.equals(EnumTypes.Grass.toString())) {
            type = EnumTypes.Grass;
        } else if (part.equals(EnumTypes.Ground.toString())) {
            type = EnumTypes.Ground;
        } else if (part.equals(EnumTypes.Ice.toString())) {
            type = EnumTypes.Ice;
        } else if (part.equals(EnumTypes.Normal.toString())) {
            type = EnumTypes.Normal;
        } else if (part.equals(EnumTypes.Poison.toString())) {
            type = EnumTypes.Poison;
        } else if (part.equals(EnumTypes.Psychic.toString())) {
            type = EnumTypes.Psychic;
        } else if (part.equals(EnumTypes.Rock.toString())) {
            type = EnumTypes.Rock;
        } else if (part.equals(EnumTypes.Steel.toString())) {
            type = EnumTypes.Steel;
        } else if (part.equals(EnumTypes.Water.toString())) {
            type = EnumTypes.Water;
        } else {
            type = null;
        }
        return type;

    }

    private static void loadAttacksFast() throws IOException {
        InputStream stream = Database.class.getResourceAsStream("/database/AttacksFast");
        Scanner sc = new Scanner(stream);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("\t");

            String name = parts[0];
            int damage = Integer.parseInt(parts[1]);
            int energy = Integer.parseInt(parts[2]);
            double time = Double.parseDouble(parts[3]);
            EnumTypes type = chooseType(parts[4]);

            AttackFastList.add(new AttackFast(name, damage, time, energy, type));

        }

        stream.close();
        sc.close();
    }

    private static void loadAttacksCharge() throws IOException {
        InputStream stream = Database.class.getResourceAsStream("/database/AttacksCharge");
        Scanner sc = new Scanner(stream);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("\t");

            String name = parts[0];
            int bars = Integer.parseInt(parts[1]);
            double time = Double.parseDouble(parts[2]);
            int damage = Integer.parseInt(parts[3]);
            Double active = Double.parseDouble(parts[4]);

            EnumTypes type = chooseType(parts[5]);

            AttackChargeList.add(new AttackCharge(name, damage, time, active, bars, type));

        }
        stream.close();
        sc.close();

    }

    private static void loadAttacksCombobox() {
        for (int i = 0; i < AttackFastList.size(); i++) {
            attackFastComboBox[i] = "(" + AttackFastList.get(i).type + ") " + AttackFastList.get(i).name;
        }
        for (int i = 0; i < AttackChargeList.size(); i++) {
            attackChargeComboBox[i] = "(" + AttackChargeList.get(i).type + ") " + AttackChargeList.get(i).name;
        }
    }

    private static int loadNumberOfFastAttacks() {
        int nope = 0;
        InputStream stream = Database.class.getResourceAsStream("/database/AttacksFast");
        Scanner sc = new Scanner(stream);
        while (sc.hasNextLine()) {
            nope++;
            sc.nextLine();
        }
        sc.close();
        return nope;
    }

    private static int loadNumberOfChargeAttacks() {
        int nope = 0;
        InputStream stream = Database.class.getResourceAsStream("/database/AttacksCharge");
        Scanner sc = new Scanner(stream);
        while (sc.hasNextLine()) {
            nope++;
            sc.nextLine();
        }
        sc.close();
        return nope;
    }

    private static void show666() {

        for (int i = 0; i < 152; i++) {          // pokemon
            for (int j = 6; j < 7; j++) {      // ivatt
                for (int k = 6; k < 7; k++) {  // ivdef
                    for (int l = 6; l < 7; l++) { // ivsta
                        for (double m = 1; m < 40.5; m += 0.5) {

                            // att, obr, sta ivatt, ivobr, ivsta, lvl
                            String[] data = {
                                Integer.toString(PokemonList.get(i).stat_att),
                                Integer.toString(PokemonList.get(i).stat_def),
                                Integer.toString(PokemonList.get(i).stat_sta),
                                Integer.toString(j),
                                Integer.toString(k),
                                Integer.toString(l),
                                Double.toString(m)};

                            if ("666".equals(catchData(data))) {
                                System.out.println(PokemonList.get(i).number + " " + PokemonList.get(i).name + " " + j + "/" + k + "/" + l + "/L" + m);
                            }
                        }
                    }
                }
            }

        }
    }

    static double[] calcDamagePerSecond(int indexFast, int indexCharge) {
        int fast_dmg = AttackFastList.get(indexFast).power;
        int fast_nrg = AttackFastList.get(indexFast).energy;
        double fast_time = AttackFastList.get(indexFast).time;

        int charge_dmg = AttackChargeList.get(indexCharge).power;
        int charge_nrg = AttackChargeList.get(indexCharge).bars;
        double charge_time = AttackChargeList.get(indexCharge).time;
        if (fast_nrg == 0) {
            double output[] = {0.0, 0.0, 0.0};
            return output;
        }
        double time_to_get_full_energy;
        double dmg_dealed_with_FA;
        try {

            time_to_get_full_energy = Math.ceil(100 / fast_nrg) * fast_time;
            dmg_dealed_with_FA = Math.ceil(100 / fast_nrg) * fast_dmg;

        } catch (Exception e) {
            time_to_get_full_energy = 0.0;
            dmg_dealed_with_FA = 0.0;
        }

        double moveset_full_time = time_to_get_full_energy + charge_nrg * charge_time;
        double moveset_full_damage = dmg_dealed_with_FA + charge_nrg * charge_dmg;

        double dps = moveset_full_damage / moveset_full_time;

        double dpsFA = fast_dmg / fast_time;

        double dpsCA = dps - dpsFA;

        dps = Math.round(dps * 100.0) / 100.0;
        dpsFA = Math.round(dpsFA * 100.0) / 100.0;
        dpsCA = Math.round(dpsCA * 100.0) / 100.0;
        double[] output = {dps, dpsFA, dpsCA};
        return output;
    }

    private static void loadPokemonAttacks(){
        
        InputStream stream = Database.class.getResourceAsStream("/database/Pokemon_Attacks");
        Scanner sc = new Scanner(stream);
        while (sc.hasNextLine()) {
            String parts[] = sc.nextLine().split("\t"); // 0-number 1-fa[] 2-ca[]
        }
    } 

    private static void loadPokemonTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void loadPokemonStats() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
