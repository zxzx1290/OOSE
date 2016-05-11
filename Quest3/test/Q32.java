import java.util.ArrayList;

interface DiagramElement {
    public void draw();
}

class StateDiagram implements DiagramElement {
    private ArrayList<DiagramElement> arr = new ArrayList<DiagramElement>();
    public void addElement(DiagramElement d){
        arr.add(d);
    }
    public void draw(){
        System.out.print("StateDiagram draw");
        String s="";
        for(DiagramElement d : arr){
            d.draw();
        }
    }
}

class State implements DiagramElement {
    public void draw(){
        System.out.print("\nState draw ");
    }
}

class Transition implements DiagramElement {
    public void draw(){
        System.out.print("\nTransition draw ");
    }
}

abstract class Decorator implements DiagramElement {
    protected DiagramElement de;
    public Decorator(DiagramElement d){
        this.de=d;
    }
    public void draw(){
        this.de.draw();
    }
}

class Scroller extends Decorator {
    public Scroller(DiagramElement g){
        super(g);
    }
    public void draw(){
        super.draw();
        drawScroller();
    }
    public void drawScroller(){
        System.out.print("with Scroller");
    }
}

class Border extends Decorator {
    public Border(DiagramElement g){
        super(g);
    }
    public void draw(){
        super.draw();
        drawScroller();
    }
    public void drawScroller(){
        System.out.print("with Border");
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
        sd.draw();
    }
}
