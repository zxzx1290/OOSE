import java.util.ArrayList;

interface DiagramElement {
    public String draw(DiagramElement g);
}

class StateDiagram implements DiagramElement {
    private ArrayList<DiagramElement> arr = new ArrayList<DiagramElement>();
    public void addElement(DiagramElement d){
        arr.add(d);
    }
    public String draw(DiagramElement g){
        /*System.out.println("StateDiagram draw");*/
        String s="";
        for(DiagramElement d : arr){
            s+=d.draw(g);
        }
        return s;
    }
}

class State implements DiagramElement {
    public String draw(DiagramElement g){
        return "\nState draw";
    }
}

class Transition implements DiagramElement {
    public String draw(DiagramElement g){
        return "\nTransition draw";
    }
}

abstract class Decorator implements DiagramElement {
    protected DiagramElement de;
    public Decorator(DiagramElement d){
        this.de=d;
    }
    public String draw(DiagramElement g){ return ""; }
}

class Scroller extends Decorator {
    public Scroller(DiagramElement g){
        super(g);
    }
    public String draw(DiagramElement g){
        return de.draw(g)+drawScroller();
    }
    public String drawScroller(){
        return " with Scroller";
    }
}

class Border extends Decorator {
    public Border(DiagramElement g){
        super(g);
        /*System.out.println(g.draw(g));*/
    }
    public String draw(DiagramElement g){
        return de.draw(g)+drawBorder();
    }
    public String drawBorder(){
        return " with Border";
    }
}


public class Q32 {
    public static void main(String[] args) {
        StateDiagram sd = new StateDiagram();
        DiagramElement s1 = new State();
        s1 = new Border(s1);
        s1 = new Scroller(s1);
        sd.addElement(s1);
        DiagramElement t1 = new Transition();
        t1 = new Border(t1);
        t1 = new Scroller(t1);
        sd.addElement(t1);
        System.out.println(sd.draw(new State()));
    }
}
