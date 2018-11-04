/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogocpcalc;

import java.io.IOException;
import javax.swing.UIManager;

/**
 *
 * @author admin
 */
public class PoGoCpCalc {

    static Frame frame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Database.load();
        try {
            
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            frame = new Frame();
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    static void restart(String str) {
        frame.dispose();
        try {
            UIManager.setLookAndFeel(str);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        frame = new Frame();
//       Frame frame = new Frame();
        if ("HiFi".equals(UIManager.getLookAndFeel().getName())) {
            frame.setAlternativeColor();
        }
        System.out.println();
        frame.pack();
        frame.setVisible(true);

    }

}
