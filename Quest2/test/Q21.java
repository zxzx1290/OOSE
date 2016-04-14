import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Iterator;

abstract class DiagramElement {
	//Component and Prototype
	public String name;
	public Point loc;

	public void draw(Graphics g){
		throw new UnsupportedOperationException();
	}
	public boolean intersect(Point p){
		throw new UnsupportedOperationException();
	}
	public void addElement(DiagramElement de){
		//stateDiagram
		throw new UnsupportedOperationException();
	}
	public DiagramElement getElement(DiagramElement de){
		throw new UnsupportedOperationException();
	}
	public void removeElement(DiagramElement de){
		//stateDiagram
		throw new UnsupportedOperationException();
	}
	public ArrayList<DiagramElement> getChild(){
		//stateDiagram
		throw new UnsupportedOperationException();
	}
	public String getName(){
		return this.name;
	}
}

class State extends DiagramElement {
	public int width;
	public int height;

	public State(){
		/*initialization the State*/
	}

	public void draw(Graphics g){
		/*draw a rectangle with rounded coner*/
	}

	public boolean intersect(Point p){
		/*set the intersect point*/
	}
}

abstract class Transition extends DiagramElement{
	public Point dest;

	public Transition(){
		/*initialization the Transition*/
	}

	public void draw(Graphics g){
		/*draw a arrow to state*/
	}

	public boolean intersect(Point p){
		/*set the intersect point*/
	}
}

class StateDiagram extends DiagramElement{
	protected ArrayList<DiagramElement> des = new ArrayList<DiagramElement>();

	public StateDiagram(){
		/*initialization the StateDiagram*/
	}

	public void addElement(DiagramElement de){
		System.out.println("----- add element into StateDiagram1 ----- ");
		des.add(de);
	}

	public DiagramElement getElement(int i){
		System.out.println("----- get element into StateDiagram1 ----- ");
		return des.get(i);
	}

	public void removeElement(DiagramElement de){
		System.out.println("StateDiagram1 remove element: " + de.getName());
		des.remove(de);
	}

	public ArrayList<DiagramElement> getChild(){
		return this.des;
	}

	public void draw(Graphics g){
		/*foreach DiagramElement draw*/
		System.out.println("----- draw StateDiagram1 ----- ");
		Iterator<DiagramElement> iterator = des.iterator();
		while(iterator.hasNext()){
			DiagramElement de = iterator.next();
			de.draw(g);
			System.out.println(de.getName());
		}
	}

	public boolean intersect(Point p){
		/*set the intersect point*/
	}
}
