package pogocpcalc;

/**
 *
 * @author nieznosnybahor
 */
public class Pokemon {

    private int id;
    private int number;
    private String name;

    private int stat_att;
    private int stat_def;
    private int stat_sta;

    private EnumTypes type1;
    private EnumTypes type2;

    private AttackFast fa[];
    protected char faX[];
    
    private AttackCharge ca[];
    protected char caX[];
    
    
    // Konstruktor
    public Pokemon(int id) {
        this.id = id;
    }

    public void showPokemonInfo() {
        System.out.println("*** START ***");
        System.out.println("id: " + getId());
        System.out.println("number: " + getNumber());
        System.out.println("name: " + getName());
        System.out.println("stats: " + getStat_att() + "/" + getStat_def() + "/" + getStat_sta());
        System.out.println("type1: " + getType1());
        System.out.println("type2: " + getType2());
        System.out.println("Fast Attacks:");
        for (int i = 0; i < fa.length; i++) {
            System.out.print(fa[i].name + " ");
        }
        System.out.println("Charge Attacks:");
        for (int i = 0; i < ca.length; i++) {
            System.out.print(ca[i].name + " ");
        }
        System.out.println("*** ENDE ***");
    }
    
    
    public int getNumber()              {
        return number;
    }
    public void setNumber(int number)   {
        this.number = number;
    }
    public String getName()             {
        return name;
    }
    public void setName(String name)    {
        this.name = name;
    }
    public int getStat_att()            {
        return stat_att;
    }
    public void setStat_att(int stat_att) {
        this.stat_att = stat_att;
    }
    public int getStat_def()            {
        return stat_def;
    }
    public void setStat_def(int stat_def) {
        this.stat_def = stat_def;
    }
    public int getStat_sta()            {
        return stat_sta;
    }
    public void setStat_sta(int stat_sta) {
        this.stat_sta = stat_sta;
    }
    public EnumTypes getType1()         {
        return type1;
    }
    public void setType1(EnumTypes type1) {
        this.type1 = type1;
    }
    public EnumTypes getType2()         {
        return type2;
    }
    public void setType2(EnumTypes type2) {
        this.type2 = type2;
    }
    public AttackFast[] getFa()         {
        return fa;
    }
    public void setFa(AttackFast[] fa)  {
        this.fa = fa;
    }
    public AttackCharge[] getCa()       {
        return ca;
    }
    public void setCa(AttackCharge[] ca) {
        this.ca = ca;
    }
    public String[] getFaNames()        {
        String s[] = new String[fa.length];
        for (int i = 0; i < s.length; i++) {
            switch (faX[i]) {
                case '*':
                    s[i] = "(" + fa[i].type + ") * " + fa[i].name;
                    break;
                case '$':
                    s[i] = "(" + fa[i].type + ") $ " + fa[i].name;
                    break;
                default:
                    s[i] = "(" + fa[i].type + ") " + fa[i].name;
                    break;
            }
        }
        return s;
    }
    public int getId()                  {
        return id;
    }
    public void setId(int id)           {
        this.id = id;
    }
    public String[] getCaNames()        {
        String s[] = new String[ca.length];
        for (int i = 0; i < s.length; i++) {
            switch (caX[i]) {
                case '*':
                    s[i] = "(" + ca[i].type + ") * " + ca[i].name;
                    break;
                case '$':
                    s[i] = "(" + ca[i].type + ") $ " + ca[i].name;
                    break;
                default:
                    s[i] = "(" + ca[i].type + ") " + ca[i].name;
                    break;
            }
        }
        return s;
    }
    
    public char[] getFaX()              {
        return faX;
    }
    public void setFaX(char[] faX)      {
        this.faX = faX;
    }
    public char[] getCaX()              {
        return caX;
    }
    public void setCaX(char[] caX)      {
        this.caX = caX;
    }
    

}
