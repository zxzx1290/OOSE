//大話版
import java.util.*;

abstract class VisitorAbstract{
    //Visitor
    public abstract void visitMuseum(Museum museum);
    public abstract void visitPark(Park park);
}

class FirstTimeVisitCity extends VisitorAbstract{
    //Visitor1
    public void visitMuseum(Museum museum){
        System.out.println("I'm visiting the Museum!");
    }
    public void visitPark(Park park){
        System.out.println("I'm visiting the Park!");
    }
}
class SecondTimeVisitCity extends VisitorAbstract{
    //Visitor2
    public void visitMuseum(Museum museum){
        System.out.println("I'm visiting the Museum second!");
    }
    public void visitPark(Park park){
        System.out.println("I'm visiting the Park second!");
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
        visitor.visitMuseum(this);
    }
}

class Park extends Element{
    //Element2
    public void accept(VisitorAbstract visitor){
        visitor.visitPark(this);
    }
}

class Visitor4{
    public static void main(String[] args){
        VisitorAbstract firstTimeVisit = new FirstTimeVisitCity();
        VisitorAbstract secondTimeVisit = new SecondTimeVisitCity();
        City city = new City();
        System.out.println("---new Museum()---");
        city.add(new Museum());
        System.out.println("---new Park()---");
        city.add(new Park());
        System.out.println("---accept(firstTimeVisit)---");
        city.accept(firstTimeVisit);
        System.out.println("---accept(secondTimeVisit)---");
        city.accept(secondTimeVisit);
    }
}
