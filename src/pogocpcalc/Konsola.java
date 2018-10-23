package pogocpcalc;

import javax.swing.JOptionPane;

/**
 *
 * @author nieznosnybahor
 */
public abstract class Konsola {
    static void print(String s)
    {
        System.out.println(s);
    }
    static void printError(String s)
    {
        System.out.println(s);
    }
    static void message(String s)
    {
        JOptionPane.showMessageDialog(null, s);
    }
    static void exception(String s)
    {
        JOptionPane.showMessageDialog(null, "Exception: " + s);
    }
            
}
