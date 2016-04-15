interface Methods{
    public void add(Element e);
    public void remove(int i);
}
class List implements Methods{
    ArrayList<Element> list = new ArrayList<Element>();
    public void add(Element e){
        list.add(e)
    }
    public void remove(int i){
        list.remove(i);
    }
}
class Stack{
    List list;
    StackStorage ss = new StackStorage();
    public void setList(List l){
        list=l;
    }
    public Element pop(int i){
        ss.pop(i);
    }
    public Element top(int i){
        ss.pop(i);
    }
    public void push(Element e){
        ss.push(e);
    }
    public void add(Element e){
        list.add(e);
    }
    public void remove(int i){
        list.remove(i);
    }
}
class Main{
    public static void main(String[] args){
        List list = new List();
        Stack stack = new Stack();
        stack.setList(list);
        stack.add(new Element());
        stack.remove(1);
    }
}
