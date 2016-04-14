class Stack{
	Delegate d = new Delegate();
	public void push(){
	}
	public void pop(){
	}
	public void top(){
	}

	public void listAdd(){
		d.add();
	}

	public void listRemove(){
		d.remove();
	}
}

class List{
	public void add(Element e){
		System.out.println("aaa");
	}
	public void remove(){
	}
}

class Delegate{
	public void add(){
		list.add();
	}
	public void remove(){
		list.remove();
	}
}

public class StackList {
	public static void main(String args[]){
		Stack s = new Stack();
		s.push();
		s.listAdd();
		s.remove();
	}
}
