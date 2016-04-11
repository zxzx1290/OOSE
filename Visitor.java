import java.util.*;

interface VisitorInterface{
    //Visitor
    public void visit(City city);
    public void visit(Museum museum);
    public void visit(Park park);
}

class FirstTimeVisitor implements VisitorInterface{
    public void visit(City city){
        //Visitor1
        System.out.println("I'm visiting the city!");
    }
    public void visit(Museum museum){
        //Visitor2
        System.out.println("I'm visiting the Museum!");
    }
    public void visit(Park park){
        //Visitor3
        System.out.println("I'm visiting the Park!");
    }
}

interface Element{
    //CompIF
    public void accept(VisitorInterface visitor);
}

class City implements Element{
    //Compoent
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
    //Compoent
    public void accept(VisitorInterface visitor){
        System.out.println("Museum is accepting visitor");
        visitor.visit(this);
    }
}

class Park implements Element{
    //Compoent
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
