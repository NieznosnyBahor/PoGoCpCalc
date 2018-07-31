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
    
    // FastAttack fa[];
    // ChargeAttac ca[];
    
    // Konstruktor
    public Pokemon(int id, int number, String name, int stat_att, int stat_def, int stat_sta, EnumTypes type1, EnumTypes type2)
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
    
}
