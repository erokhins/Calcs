import org.hanuna.calcs.parser.Lexer;
import org.hanuna.calcs.parser.LexerToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;


public class Calc {
    public static void main(String[] args) {
        try{
            BufferedReader f1 = new BufferedReader(new FileReader(args[0]));
            StringReader f = new StringReader("235235 67 +_");
            Lexer l = new Lexer(f);

            LexerToken lt = l.next();

            while (lt.getT() >0) {
                System.out.println(lt.getT() + " " + lt.getS());
                lt = l.next();
            }
            System.out.println(lt.getT() + " " + lt.getS());

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

}
