//混合版
import java.util.*;

interface VisitorInterface{
    //Visitor
    public void visit(Museum museum);
    public void visit(Park park);
}

class FirstTimeVisitor implements VisitorInterface{
    public void visit(Museum museum){
        //Visitor1
        System.out.println("I'm visiting the Museum!");
    }
    public void visit(Park park){
        //Visitor2
        System.out.println("I'm visiting the Park!");
    }
}

interface Element{
    //CompIF
    public void accept(VisitorInterface visitor);
}

class City {
    //ObjectStruct
    ArrayList<Element> places = new ArrayList<Element>();

    public City(){
        places.add(new Museum());
        places.add(new Park());
    }
    public void accept(VisitorInterface visitor){
        System.out.println("I'm in City");
        for(Element e : places){
            e.accept(visitor);
        }
    }
}

class Museum implements Element{
    //Element1
    public void accept(VisitorInterface visitor){
        System.out.println("Museum is accepting visitor");
        visitor.visit(this);
    }
}

class Park implements Element{
    //Element2
    public void accept(VisitorInterface visitor){
        System.out.println("Park is accepting visitor");
        visitor.visit(this);
    }
}

class Visitor3{
    public static void main(String[] args){
        FirstTimeVisitor visitor = new FirstTimeVisitor();
        City city = new City();
        city.accept(visitor);
    }
}
