import java.util.*;

interface VisitorInterface{
    public void visit(City city);
    public void visit(Museum museum);
    public void visit(Park park);
}

class FirstTimeVisitor implements VisitorInterface{
    public void visit(City city){
        System.out.println("I'm visiting the city!");
    }
    public void visit(Museum museum){
        System.out.println("I'm visiting the Museum!");
    }
    public void visit(Park park){
        System.out.println("I'm visiting the Park!");
    }
}

interface Element{
    public void accept(VisitorInterface visitor);
}

class City implements Element{
    ArrayList<Element> places = new ArrayList<Element>();

    public City(){
        places.add(new Museum());
        places.add(new Park());
    }
    public void accept(VisitorInterface visitor){
        System.out.println("City is accepting visitor");
        visitor.visit(this);
        for(Element e : places){
            e.accept(visitor);
        }
    }
}

class Museum implements Element{
    public void accept(VisitorInterface visitor){
        System.out.println("Museum is accepting visitor");
        visitor.visit(this);
    }
}

class Park implements Element{
    public void accept(VisitorInterface visitor){
        System.out.println("Park is accepting visitor");
        visitor.visit(this);
    }
}

class Visitor{
    public static void main(String[] args){
        FirstTimeVisitor visitor = new FirstTimeVisitor();
        City city = new City();
        city.accept(visitor);
    }
}
