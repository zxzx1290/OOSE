import java.util.*;
interface Expression {
    public int interpret(Map<String,Expression> var);
}

class Number implements Expression {
    private int num;
    public Number(int i){
        this.num=i;
    }
    public int interpret(Map<String,Expression> var){
        return this.num;
    }
}

class Plus implements Expression {
    private Expression l,r;
    public Plus(Expression l,Expression r){
        this.l=l;
        this.r=r;
    }
    public int interpret(Map<String,Expression> var){
        return l.interpret(var)+r.interpret(var);
    }
}

class Minus implements Expression {
    private Expression l,r;
    public Minus(Expression l,Expression r){
        this.l=l;
        this.r=r;
    }
    public int interpret(Map<String,Expression> var){
        return l.interpret(var)-r.interpret(var);
    }
}

class Variable implements Expression {
    private String name;
    public Variable(String name){
        this.name=name;
    }
    public int interpret(Map<String,Expression> var){
        return var.get(name).interpret(var);
    }
}

class Evaluator {
    private Expression syntaxTree;
    public Evaluator(String ss){
        Stack<Expression> st = new Stack<Expression>();
        for(String s : ss.split(" ")){
            if(s.equals("+")){
                st.push(new Plus(st.pop(),st.pop()));
            }else if(s.equals("-")){
                Expression r = st.pop();
                Expression l = st.pop();
                st.push(new Minus(l,r));
            }else{
                st.push(new Variable(s));
            }
        }
        syntaxTree=st.pop();
    }
    public int interpret(Map<String,Expression> var){
        return syntaxTree.interpret(var);
    }
}

public class testInterpreter {
    public static void main(String[] args) {
        Evaluator e = new Evaluator("w x z + -");
        Map<String,Expression> m = new HashMap<String,Expression>();
        m.put("w", new Number(5));
        m.put("x", new Number(10));
        m.put("z", new Number(42));
        System.out.println("\n"+ e.interpret(m) );
    }
}
