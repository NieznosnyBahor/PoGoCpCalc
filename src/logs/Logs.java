/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NB
 */
public abstract class Logs {
    static boolean exist = false;
    static int number_of_logs;
    static String path;
    static ArrayList <LogRecord> logs; 
    
    static void addRecord(){}
    static void deleteRecordList(){}
    static void loadRecordSystem() throws FileNotFoundException
    {
        File file = new File("logs");
        Scanner in = new Scanner(file);
        System.out.println(in.nextLine());   
    }
    static void saveRecordSystem(){}
}
