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
public class LogRecord {
    int number;
    char type;
    String message;
    String date;
    
    public LogRecord(int number, char type, String message, String date)
    {
        this.number = number; 
        this.type = type;
        this.message = message;
        this.date = date;
    }
        
}
//
//  e - error
//  i - information / confirmation
//  x - excepion
//  f - fatality  

