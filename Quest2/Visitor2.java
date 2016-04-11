//英文課本版
import java.util.*;

abstract class VisitorAbstract{
    //Visitor
    public abstract void visit(City city);
    public abstract void visit(Museum museum);
    public abstract void visit(Park park);
}

class FirstTimeVisitCity extends VisitorAbstract{
    //Visitor1
    public void visit(City city){
        System.out.println("I'm visiting the city!");
    }
    public void visit(Museum museum){};
    public void visit(Park park){};
}
class FirstTimeVisitMuseum extends VisitorAbstract{
    //Visitor2
    public void visit(City city){};
    public void visit(Museum museum){
        System.out.println("I'm visiting the Museum!");
    }
    public void visit(Park park){};
}
class FirstTimeVisitPark extends VisitorAbstract{
    //Visitor3
    public void visit(City city){};
    public void visit(Museum museum){};
    public void visit(Park park){
        System.out.println("I'm visiting the Park!");
    }
}

interface Element{
    //CompIF
    public void accept(VisitorAbstract visitor);
}

class City implements Element{
    //假設 City 和 Museum 和 Park 都是同一個等級
    //Element1
    public void accept(VisitorAbstract visitor){
        System.out.println("City is accepting visitor");
    }
}

class Museum implements Element{
    //Element2
    public void accept(VisitorAbstract visitor){
        System.out.println("Museum is accepting visitor");
        visitor.visit(this);
    }
}

class Park implements Element{
    //Element3
    public void accept(VisitorAbstract visitor){
        System.out.println("Park is accepting visitor");
        visitor.visit(this);
    }
}

class Visitor2{
    public static void main(String[] args){
        FirstTimeVisitMuseum visitor = new FirstTimeVisitMuseum();
        Museum museum = new Museum();
        museum.accept(visitor);
    }
}
