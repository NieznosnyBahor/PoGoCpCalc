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
public class AttackFast {

    String name;
    
    int power;
    int energy;
    double time;
    
    EnumTypes type;
    
//    double DPS;
//    double EPS;

    public AttackFast(String name, int power, double time, int energy, EnumTypes type) {
        this.name = name;
        this.power = power;
        this.energy = energy;
        this.time = time;
        this.type = type;

//        this.DPS = this.power / this.time;
//        this.EPS = this.energy / this.time;
    }
}
