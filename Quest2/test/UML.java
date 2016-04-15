import java.awt.*;
import java.util.*;

interface AbstractFactory{
	public State createState(Point p);
	public Transition createTransition(Point p);
	public StateDiagram createDiagram(Point p);
}

class UML1Factory implements AbstractFactory{
	public State createState(Point p){
		return new UML1State(p);
	}
	public Transition createTransition(Point p){
		return new UML1Transition(p);
	}
	public StateDiagram createDiagram(Point p){
		return new UML1StateDiagram(p);
	}
}

class UML2Factory implements AbstractFactory{
	public State createState(Point p){
		return new UML2State(p);
	}
	public Transition createTransition(Point p){
		return new UML2Transition(p);
	}
	public StateDiagram createDiagram(Point p){
		return new UML2StateDiagram(p);
	}
}

interface DiagramElement{
	public void draw(Graphics g);
	public void add(DiagramElement e);
}
interface State implements DiagramElement{
	public void draw(Graphics g);
	public void add(DiagramElement e);
}
interface Transition implements DiagramElement{
	/*public void draw(Graphics g);
	public void add(DiagramElement e);*/
}
interface StateDiagram implements DiagramElement{
	/*public void draw(Graphics g);
	public void add(DiagramElement e);*/
}

class UML1State implements State{
	public void add(DiagramElement e){
		throw new UnsupportedOperationException();
	}
	public void draw(Graphics g){
		/*draw*/
		System.out.println("UML1StateDraw");
	}
}
class UML2State implements State{
	public void add(DiagramElement e){
		throw new UnsupportedOperationException();
	}
	public void draw(Graphics g){
		/*draw*/
		System.out.println("UML2StateDraw");
	}
}
class UML1Transition implements Transition{
	public void add(DiagramElement e){
		throw new UnsupportedOperationException();
	}
	public void draw(Graphics g){
		/*draw*/
		System.out.println("UM1TransitionDraw");
	}
}
class UML2Transition implements Transition{
	public void ad(DiagramElement e){
		throw new UnsupportedOperationException();
	}
	public void draw(Graphics g){
		/*draw*/
		System.out.println("UML2TransitionDraw");
	}
}
class UML1StateDiagram implements StateDiagram{
	ArrayList<DiagramElement> um1a = new ArrayList<DiagramElement>();
	public void add(DiagramElement e){
		umla.add(e);
	}
	public void draw(Graphics g){
		/*draw*/
		System.out.println("UML1StateDiagramDraw");
		Iterator iterator = um1a.iterator();
		while(iterator.hasNext()){
			((DiagramElement)iterator.next()).draw(g);
		}
	}
}
class UML2StateDiagram implements StateDiagram{
	ArrayList<DiagramElement> um2a = new ArrayList<DiagramElement>();
	public void add(DiagramElement e){
		um2a.add(e);
	}
	public void draw(Graphics g){
		/*draw*/
		System.out.println("UML2StateDiagramDraw");
		Iterator iterator = um2a.iterator();
		while(iterator.hasNext()){
			((DiagramElement)itrator.next()).draw(g);
		}
	}
}
class EditDiagramController{
	AbstractFactory factory;
	DiagramElement de;
	public setFactory(AbstractFactory f){
		this.factory=f;
	}
	public addState(Point p){
		State s = factory.createState(p);
		if(de==null){
			de = factory.createDiagram(p);
		}
		de.add(s);
	}
}

public class UML{
	public static void main(String[] args) {
		EditDiagramController edc = new EditDiagramController();
		AbstractFactory ab = new UML1Factory();
		edc.setFactory(ab);
		edc.addState(new Point());
		edc.draw(new Graphics());
	}
}
