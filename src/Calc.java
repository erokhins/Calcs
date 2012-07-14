import org.hanuna.calcs.parser.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Calc {
    public static void main(String[] args) {
        try{
            BufferedReader f = new BufferedReader(new FileReader(args[0]));

            Lexer l = new Lexer(f);
            l.next();

            try{
                ListOfVars list = Parser.parseListOfVars(l);
                ParserNode n = Parser.parseExpression(l);
            } catch (ParserError e) {
                System.out.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

}
