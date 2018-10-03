package pogocpcalc;

/**
 *
 * @author nieznosnybahor
 */
public class Pokemon {

    int id;
    int number;
    String name;
    
    int stat_att;
    int stat_def;
    int stat_sta;
    
    EnumTypes type1;
    EnumTypes type2;
    
    AttackFast fa[];
    AttackCharge ca[];
    
    // Konstruktor
    public Pokemon(int id, int number, String name, 
                   int stat_att, int stat_def, int stat_sta, 
                   EnumTypes type1, EnumTypes type2)
    {
        this.id = id;
        this.number = number;
        this.name = name;
        this.stat_att = stat_att;
        this.stat_def = stat_def;
        this.stat_sta = stat_sta;
        this.type1 = type1;
        this.type2 = type2;
    }
    
    public void showPokemonInfo()
    {
        System.out.println("id: "+ id);
        System.out.println("number: " + number);
        System.out.println("name: " + name);
        System.out.println("stats: " + stat_att + "/" + stat_def + "/" + stat_sta);
        System.out.println("type1: " + type1);
        System.out.println("type2: " + type2);
    }
    
    
}
