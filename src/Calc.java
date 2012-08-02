import org.hanuna.calcs.RunCalcs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;



public class Calc {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("usage: java Calcs filename");
                return;
            }
            BufferedReader f = new BufferedReader(new FileReader(args[0]));

            System.out.println(RunCalcs.calcsRun(f));

        } catch (FileNotFoundException e) {
            System.err.println("File '" + args[0] + "' not found");
        }
    }

}
