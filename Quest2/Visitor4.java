//大話版
import java.util.*;

abstract class VisitorAbstract{
    //Visitor
    public abstract void visit(Museum museum);
    public abstract void visit(Park park);
}

class FirstTimeVisitMuseum extends VisitorAbstract{
    //Visitor2
    public void visit(Museum museum){
        System.out.println("I'm visiting the Museum!");
    }
    public void visit(Park park){};
}
class FirstTimeVisitPark extends VisitorAbstract{
    //Visitor3
    public void visit(Museum museum){};
    public void visit(Park park){
        System.out.println("I'm visiting the Park!");
    }
}

abstract class Element{
    //CompIF
    public abstract void accept(VisitorAbstract visitor);
}

class City {
    //ObjectStruct
    ArrayList<Element> places = new ArrayList<Element>();
    public void add(Element e){
        places.add(e);
    }
    public void accept(VisitorAbstract visitor){
        for(Element e : places){
            e.accept(visitor);
        }
    }
}
class Museum extends Element{
    //Element1
    public void accept(VisitorAbstract visitor){
        visitor.visit(this);
    }
}

class Park extends Element{
    //Element2
    public void accept(VisitorAbstract visitor){
        visitor.visit(this);
    }
}

class Visitor4{
    public static void main(String[] args){
        VisitorAbstract visitorMuseum = new FirstTimeVisitMuseum();
        VisitorAbstract visitorPark = new FirstTimeVisitPark();
        City city = new City();
        System.out.println("---new Museum()---");
        city.add(new Museum());
        System.out.println("---new Park()---");
        city.add(new Park());
        System.out.println("---accept(visitorMuseum)---");
        city.accept(visitorMuseum);
        System.out.println("---accept(visitorPark)---");
        city.accept(visitorPark);
    }
}
