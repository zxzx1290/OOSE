import java.util.ArrayList;
import java.util.Iterator;

interface DiagramElement {
	//Component
	public void accept(Checker c);
}

class StateDiagram implements DiagramElement {
	//Composite
	private ArrayList<DiagramElement> els = new ArrayList<DiagramElement>();

	public void add(DiagramElement e) {
		els.add(e);
	}

	public void accept(Checker c) {
		c.check(this);
		Iterator itr = els.iterator();

		while (itr.hasNext()) {
			((DiagramElement)itr.next()).accept(c);
		}
	}
}

class State implements DiagramElement {
	public void accept(Checker c) {
		c.check(this);
	}
}

class Transition implements DiagramElement {
	public void accept(Checker c) {
		c.check(this);
	}
}


interface Checker {
	public void check(State s);
	public void check(Transition t);
	public void check(StateDiagram sd);
}

class SyntaxCheck implements Checker {
	public void check(State s) {
		System.out.println("SyntaxCheck: State " + s);
	}

	public void check(Transition t) {
		System.out.println("SyntaxCheck: Transition " + t);
	}

	public void check(StateDiagram sd) {
		System.out.println("SyntaxCheck: StateDiagram " + sd);
	}
}

class RelationCheck implements Checker {
	public void check(State s) {
		System.out.println("RelationCheck: State " + s);
	}

	public void check(Transition t) {
		System.out.println("RelationCheck: Transition " + t);
	}

	public void check(StateDiagram sd) {
		System.out.println("RelationCheck: StateDiagram " + sd);
	}
}

class Q22 {
	public static void main (String[] args) {
		StateDiagram sd = new StateDiagram();

		sd.add(new State());
		sd.add(new State());
		sd.add(new Transition());

		sd.accept(new SyntaxCheck());
		sd.accept(new RelationCheck());
	}
}
