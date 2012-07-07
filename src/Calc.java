import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: smevok
 * Date: 05.07.12
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
class Lexer{

    public static final int TT_PLUS = 1;  // +
    public static final int TT_MINUS = 2; // -
    public static final int TT_MULT = 3;  // *
    public static final int TT_BKT_O = 5; // (
    public static final int TT_BKT_C = 6; // )
    public static final int TT_EQ = 7;    // =
    public static final int TT_VAR = 12;   // asd
    public static final int TT_RUN = 10;   // >
    public static final int TT_INT = 11;   // 239
    public static final int TT_STOP = -1;  // error or end file
    public static final int TT_ER = -2;  // error input syntax

    private StreamTokenizer in;
    public int     ttype;
    public String  sval;
    public int     nval;

    Lexer(StreamTokenizer in){
        this.in = in;
    }

    int next(){
        try{
            in.nextToken();
        } catch (IOException e) {
            this.ttype = Lexer.TT_STOP;
            return this.ttype;
        }
        switch (in.ttype){
            case StreamTokenizer.TT_EOF: this.ttype = Lexer.TT_STOP; break;
            case StreamTokenizer.TT_NUMBER:
                this.ttype = Lexer.TT_INT;
                this.nval = (int) in.nval;
                break;
            case StreamTokenizer.TT_WORD:
                this.ttype = Lexer.TT_VAR;
                this.sval = in.sval;
                break;
            case 61: ttype = TT_EQ;
                break;
            case 62: ttype = TT_RUN;
                in.ordinaryChar('-');
                break;
            case 40: ttype = TT_BKT_O;
                break;
            case 41: ttype = TT_BKT_C;
                break;
            case 43: ttype = TT_PLUS;
                break;
            case 45: ttype = TT_MINUS;
                break;
            case 42: ttype = TT_MULT;
                break;

            default: ttype = TT_ER;
        }
        return this.ttype;
    }
}

public class Calc {
    public static void main(String[] args) {

        StreamTokenizer in;
        try{
            in = new StreamTokenizer(new FileReader("input"));
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        Lexer l = new Lexer(in);
        ListOfVars list = new ListOfVars(l);

        l.next();
        Sum s = new Sum(l);
        CalcVisitor c = new CalcVisitor(list);
        int k;
        k = s.accept(c);
        System.out.println(k);

    }

}

class ListOfVars{
    private HashMap vars = null;

    ListOfVars(){
        this.vars = new HashMap();
    }

    Integer get(String s){
        Object v = this.vars.get(s);
        if(v instanceof Integer){
            return (Integer) this.vars.get(s);
        }else{
            return null;
        }
    }



    ListOfVars(Lexer l){  // не выдаёт ошибок, а просто завершается
        this.vars = new HashMap();
        while (l.next() != Lexer.TT_STOP){
            boolean er = false;
            String s = "";
            int k = -1;
            if(l.ttype == Lexer.TT_VAR){
                s = l.sval;
                if(l.next() == Lexer.TT_EQ && l.next() == Lexer.TT_INT){
                    k = l.nval;
                }else{
                    er = true;
                }
            }else{
                er = true;
            }

            if(er){
                break;
            }else {
                this.vars.put(s, k);
            }
        }
    }
}

class CalcVisitor implements ExpressionVisitor<Integer> {
    ListOfVars l;

    CalcVisitor(ListOfVars l){
        this.l = l;
    }
    @Override
    public Integer visitSum(Sum s) {
        if(s.s == null){
            return s.m.accept(this);
        }else {
            if(s.type == Lexer.TT_PLUS)
                return s.m.accept(this) + s.s.accept(this);
            else
                return s.m.accept(this) - s.s.accept(this);
        }
    }

    @Override
    public Integer visitMult(Mult m) {
        if(m.m == null){
            return m.f.accept(this);
        }else {
            return m.f.accept(this) * m.m.accept(this);
        }
    }

    @Override
    public Integer visitFactor(Factor f) {
        if(f.f != null){
            if(f.type == Lexer.TT_PLUS)
                return f.f.accept(this);
            else
                return - f.f.accept(this);
        }
        if (f.i != null){
            return f.i.accept(this);
        }
        if (f.s != null){
            return f.s.accept(this);
        }
        if (f.v != null){
            return f.v.accept(this);
        }
        return 0;
    }

    @Override
    public Integer visitVar(Var v) {
        Integer k = this.l.get(v.s);
        if(k == null){
            /// ERROR
            return -1000;
        }else{
            return k;
        }
    }

    @Override
    public Integer visitInt(Int i) {
        return i.k;
    }
}

interface ExpressionVisitor<T> {
    T visitSum(Sum s);
    T visitMult(Mult m);
    T visitFactor(Factor f);
    T visitVar(Var v);
    T visitInt(Int i);

}

interface Expression {
    <T> T accept(ExpressionVisitor<T> visitor);
}
/*
* S => M + S
* M = F | F * M
* F = (S) | Var | Int | + F
* */

class Sum implements Expression{
    Mult m = null;
    Sum s = null;
    int type;
    Sum(Lexer l){
        this.m =new Mult(l);
        if(l.ttype != Lexer.TT_STOP){
            if(l.ttype == Lexer.TT_MINUS || l.ttype == Lexer.TT_PLUS){
                this.type = l.ttype;
                l.next();
                this.s = new  Sum(l);
            }else {
                /// ERROR
            }

        }
    }
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitSum(this);
    }
}

class Mult implements Expression{
    Factor f = null;
    Mult m = null;
    int type;
    Mult(Lexer l){
        this.f = new Factor(l);
        if(l.ttype != Lexer.TT_STOP){
            if(l.ttype == Lexer.TT_MULT){
                this.type = l.ttype;
                l.next();
                this.m = new Mult(l);
            }else{
                /// ERROR
            }
        }
    }
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitMult(this);
    }
}

class Factor implements Expression{
    Sum s = null;
    Var v = null;
    Int i = null;
    Factor f = null;
    int type;

    Factor(Lexer l){
        switch (l.ttype){
            case Lexer.TT_BKT_O:
                l.next();
                this.s = new Sum(l);
                if(l.ttype == Lexer.TT_BKT_C){
                    l.next();
                }else {
                    /// ERROR
                }
                break;
            case Lexer.TT_VAR:
                this.v = new Var(l.sval);
                l.next();
                break;
            case Lexer.TT_INT:
                this.i = new Int(l.nval);
                l.next();
                break;
            case Lexer.TT_MINUS:
                this.type = l.ttype;
                l.next();
                this.f = new Factor(l);
                break;
            case Lexer.TT_PLUS:
                this.type = l.ttype;
                l.next();
                this.f = new Factor(l);
                break;
            default:
                /// ERROR
        }

    }
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitFactor(this);
    }
}

class Var implements Expression{
    String s;
    Var(String s){
        this.s = s;
    }
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitVar(this);
    }
}

class Int implements Expression{
    int k;
    Int(int k){
        this.k = k;
    }
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitInt(this);
    }
}