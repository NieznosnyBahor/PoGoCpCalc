package pogocpcalc;

/**
 *
 * @author nieznosnybahor
 */
public class Pokemon {

    int id;
    private int number;
    private String name;
    
    private int stat_att;
    private int stat_def;
    private int stat_sta;
    
    private EnumTypes type1;
    private EnumTypes type2;
    
    private AttackFast fa[];
    private AttackCharge ca[];
    
    // Konstruktor
    public Pokemon(int id)
    {
        this.id = id;
    }
    
    public void showPokemonInfo(){
        System.out.println("id: "+ id);
        System.out.println("number: " + getNumber());
        System.out.println("name: " + getName());
        System.out.println("stats: " + getStat_att() + "/" + getStat_def() + "/" + getStat_sta());
        System.out.println("type1: " + getType1());
        System.out.println("type2: " + getType2());
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the stat_att
     */
    public int getStat_att() {
        return stat_att;
    }

    /**
     * @param stat_att the stat_att to set
     */
    public void setStat_att(int stat_att) {
        this.stat_att = stat_att;
    }

    /**
     * @return the stat_def
     */
    public int getStat_def() {
        return stat_def;
    }

    /**
     * @param stat_def the stat_def to set
     */
    public void setStat_def(int stat_def) {
        this.stat_def = stat_def;
    }

    /**
     * @return the stat_sta
     */
    public int getStat_sta() {
        return stat_sta;
    }

    /**
     * @param stat_sta the stat_sta to set
     */
    public void setStat_sta(int stat_sta) {
        this.stat_sta = stat_sta;
    }

    /**
     * @return the type1
     */
    public EnumTypes getType1() {
        return type1;
    }

    /**
     * @param type1 the type1 to set
     */
    public void setType1(EnumTypes type1) {
        this.type1 = type1;
    }

    /**
     * @return the type2
     */
    public EnumTypes getType2() {
        return type2;
    }

    /**
     * @param type2 the type2 to set
     */
    public void setType2(EnumTypes type2) {
        this.type2 = type2;
    }

    /**
     * @return the fa
     */
    public AttackFast[] getFa() {
        return fa;
    }

    /**
     * @param fa the fa to set
     */
    public void setFa(AttackFast[] fa) {
        this.fa = fa;
    }

    /**
     * @return the ca
     */
    public AttackCharge[] getCa() {
        return ca;
    }

    /**
     * @param ca the ca to set
     */
    public void setCa(AttackCharge[] ca) {
        this.ca = ca;
    }
    
    public String[] getFaNames()
    {
        String s[] = new String[fa.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = "(" + fa[i].type + ") " + fa[i].name;
        }
        return s;
    }
    public String[] getCaNames()
    {
        String s[] = new String[ca.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = "(" + ca[i].type + ") " + ca[i].name;
        }
        return s;
    }
}
