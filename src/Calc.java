import org.hanuna.calcs.evaluator.CalcEvaluator;
import org.hanuna.calcs.evaluator.CalcEvaluatorException;
import org.hanuna.calcs.parser.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Calc {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("usage: java Calc filename");
                return;
            }
            BufferedReader f = new BufferedReader(new FileReader(args[0]));

            Lexer l = new Lexer(f);

            try {
                TableVars list = ParserTableVars.parserTableVars(l);
                ParserNode n = Parser.parseExpression(l);

                try {
                    Integer k = n.accept(new CalcEvaluator(list));
                    System.out.println(k);
                } catch (CalcEvaluatorException e) {
                    System.out.println(e.getMessage());
                }
            } catch (ParserException e) {
                System.out.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File '"+args[0]+"' not found");
        } catch (IOException e) {
            e.printStackTrace();  /////
        }
    }

}
