/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogocpcalc;

/**
 *
 * @author NB
 */
public class AttackCharge {

    String name;
    
    int power;
    int bars;
    double time;
    
    double active;
    EnumTypes type;
    
//    double DPS;
//    double EPS;
    
    public AttackCharge(String name, int power, double time, double active, int bars, EnumTypes type)
    {
        this.name = name;
        this.power = power;
        this.bars = bars;
        this.time = time;
        this.active = active;
        this.type = type;
        
    }

    String getInfo() {
        String s;
        s = "name " + name + "\n" + "power: " + power + "\n" + "bars" + bars + "\n";
        return s;    
    }
}