interface Delegate{
    public void push(Element e);
    public void push(Element e);
    public Element pop(int i);
    public Element top(int i);
    public void add(Element e);
    public void remove(int i);
}

class List implements Delegate{
    private ArrayList<String> arr = new ArrayList<String>();
    public void add(Element e ) {
        arr.add(e);
    }
    public void remove(int i){
        arr.remove(i);
    }
}
class Stack implements Delegate{
    StackStorage ss = new StackStorage();
    public void push(Element e){
        ss.push(e);
    }
    public Element pop(int i){
        ss.pop(i);
    }
    public Element top(int i){
        ss.top(i);
    }
}
class Mid{
    private Delegate d;
    public Mid(Delegate d){
        this.d=d;
    }
    public void push(Element e){
        d.push(e);
    }
    public Element pop(int i){}
    public Element top(int i){}
    public void add(Element e){}
    public void remove(int i){}
}

class Main{
    public static void main(String[] args) {
        Delegate list = new List();
        Delegate stack = new Stack();
        Mid m = new Mid(d);
        m.push(1);
        
    }
} 
