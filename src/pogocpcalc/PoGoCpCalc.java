/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogocpcalc;

import java.io.IOException;

/**
 *
 * @author admin
 */
public class PoGoCpCalc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Database.load();
        Frame frame = new Frame();
        frame.pack();
        frame.setVisible(true);
    }
    
}
