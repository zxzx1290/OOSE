import java.util.Map;
import java.util.Stack;
import java.util.HashMap;

interface Expression {
    public int interpret(Map<String,Expression> variables);
}

class Number implements Expression {
    private int number;
    public Number(int number)       { this.number = number; }
    public int interpret(Map<String,Expression> variables)  {
        System.out.println("Number in "+number);
        return number;
    }
}

class Plus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Plus(Expression left, Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public int interpret(Map<String,Expression> variables)  {
        System.out.println("Plus in "+variables);
        return leftOperand.interpret(variables) + rightOperand.interpret(variables);
    }
}

class Minus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Minus(Expression left, Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public int interpret(Map<String,Expression> variables)  {
        System.out.println("Minus in "+variables);
        return leftOperand.interpret(variables) - rightOperand.interpret(variables);
    }
}

class Variable implements Expression {
    private String name;
    public Variable(String name)       { this.name = name; }
    public int interpret(Map<String,Expression> variables)  {
        System.out.println("Variable in "+variables);
        if(null==variables.get(name)) return 0; //Either return new Number(0).
        return variables.get(name).interpret(variables);
    }
}


class Evaluator implements Expression {
    private Expression syntaxTree;

    public Evaluator(String expression) {
        Stack<Expression> expressionStack = new Stack<Expression>();
        for (String token : expression.split(" ")) {
            if  (token.equals("+")) {
                Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( subExpression );
                System.out.println(expressionStack+"--- "+token);
            }
            else if (token.equals("-")) {
                // it's necessary remove first the right operand from the stack
                Expression right = expressionStack.pop();
                // ..and after the left one
                Expression left = expressionStack.pop();
                Expression subExpression = new Minus(left, right);
                expressionStack.push( subExpression );
                System.out.println(expressionStack+"--- "+token);
            }
            else{
                expressionStack.push( new Variable(token) );
                System.out.println(expressionStack+"--- "+token);
            }
        }
        System.out.println("\n"+expressionStack);
        syntaxTree = expressionStack.pop();
        System.out.println(syntaxTree);
    }

    public int interpret(Map<String,Expression> context) {
        System.out.println(context+"\n");
        return syntaxTree.interpret(context);
    }
}


public class Interpreter {
    public static void main(String[] args) {
        String expression = "w x z + -";
        Evaluator sentence = new Evaluator(expression);
        Map<String,Expression> variables = new HashMap<String,Expression>();
        variables.put("w", new Number(5));
        variables.put("x", new Number(10));
        variables.put("z", new Number(42));
        System.out.println("\n"+ sentence.interpret(variables) );
    }
}
