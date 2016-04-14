import java.util.ArrayList;
import java.util.Iterator;

interface DiagramElement{
    public void accept(Checker c);
}

class StateDiagram implements DiagramElement{
    private ArrayList<DiagramElement> array = null;
    public void add(DiagramElement d){
        if(array == null){
            array=new ArrayList<DiagramElement>();
        }
        array.add(d);
    }
    public void accept(Checker c){
        c.check(this);
        Iterator iterator = array.iterator();
        while(iterator.hasNext()){
            ((DiagramElement)iterator.next()).accept(c);
        }
    }
}

class State implements DiagramElement{
    public void accept(Checker c){
        c.check(this);
    }
}

class Transition implements DiagramElement{
    public void accept(Checker c){
        c.check(this);
    }
}

interface Checker{
    public void check(State s);
    public void check(Transition t);
    public void check(StateDiagram sd);
}

class SyntaxCheck implements Checker{
    public void check(State s){
        System.out.println("SyntaxCheck :state"+s);
    }
    public void check(Transition t){
        System.out.println("SyntaxCheck :transition"+t);
    }
    public void check(StateDiagram sd){
        System.out.println("SyntaxCheck :statediagram"+sd);
    }
}

class RelationCheck implements Checker{
    public void check(State s){
        System.out.println("RelationCheck :state"+s);
    }
    public void check(Transition t){
        System.out.println("RelationCheck :transition"+t);
    }
    public void check(StateDiagram sd){
        System.out.println("RelationCheck :statediagram"+sd);
    }
}

class testQ22{
    public static void main(String[] args) {
        StateDiagram sd = new StateDiagram();
        State s1 = new State();
        State s2 = new State();
        Transition t = new Transition();
        sd.add(s1);
        sd.add(s2);
        sd.add(t);
        sd.accept(new SyntaxCheck());
        sd.accept(new RelationCheck());
    }
}
