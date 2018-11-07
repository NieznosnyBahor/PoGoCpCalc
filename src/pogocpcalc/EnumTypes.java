/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogocpcalc;

/**
 *
 * @author admin
 */
public enum EnumTypes {
    Bug, Dark, Dragon, Electric, Fairy, Fight, Fire, Flying, Ghost,
    Grass, Ground, Ice, Normal, Poison, Psychic, Rock, Steel, Water;

    int getNumber(EnumTypes type) {
        int number = 0;
        switch (type) {
            case Bug: {
                number = 0;break;
            }
            case Dark: {
                number = 1;break;
            }
            case Dragon: {
                number = 2;break;
            }
            case Electric: {
                number = 3;break;
            }
            case Fairy: {
                number = 4;break;
            }
            case Fight: {
                number = 5;break;
            }
            case Fire: {
                number = 6;break;
            }
            case Flying: {
                number = 7;break;
            }
            case Ghost: {
                number = 8;break;
            }
            case Grass: {
                number = 9;break;
            }
            case Ground: {
                number = 10;break;
            }
            case Ice: {
                number = 11;break;
            }
            case Normal: {
                number = 12;break;
            }
            case Poison: {
                number = 13;break;
            }
            case Psychic: {
                number = 14;break;
            }
            case Rock: {
                number = 15;break;
            }
            case Steel: {
                number = 16;break;
            }
            case Water: {
                number = 17;break;
            }
            default: {
            }

        }

        return number;
    }
}
