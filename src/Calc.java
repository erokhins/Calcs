import org.hanuna.calcs.evaluator.CalcEvaluator;
import org.hanuna.calcs.parser.*;

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

            Lexer l = new Lexer(f, true);

            try{
                ListOfVars list = Parser.parseListOfVars(l);
                ParserNode n = Parser.parseExpression(l);

                try{
                    Integer k = n.accept(new CalcEvaluator(list));
                    System.out.println(k);
                } catch (ExpressionVisitorError e) {
                    System.out.println(e.getMessage());
                }
            } catch (ParserError e) {
                System.out.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File '"+args[0]+"' not found");
        }
    }

}
