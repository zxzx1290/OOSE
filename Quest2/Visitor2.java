import java.util.*;

interface VisitorInterface{
    public void visit(Meseum meseum);
    public void visit(Park park);
}

class FirstTimeVisitCity implements VisitorInterface{
    //Visitor1
    public void visit(Meseum meseum){
        System.out.println("I'm visiting the Museum!");
    }
    public void visit(Park park){
        System.out.println("I'm visiting the Park!");
    }
}

class SecondTimeVisitCity implements VisitorInterface{
    //Visitor2
    public void visit(Meseum meseum){
        System.out.println("I'm visiting the Museum second!");
    }
    public void visit(Park park){
        System.out.println("I'm visiting the Park second!");
    }
}

interface Element{
    public void accept(VisitorInterface visitor);
}

class Meseum implements Element{
    //Element1
    public void accept(VisitorInterface visitor){
        visitor.visit(this);
    }
}

class Park implements Element{
    //Element2
    public void accept(VisitorInterface visitor){
        visitor.visit(this);
    }
}

class City{
    ArrayList<Element> places = new ArrayList<Element>();
    public void addPlace(Element e){
        places.add(e);
    }
    public void accept(VisitorInterface visitor){
        for(Element e : places){
            e.accept(visitor);
        }
    }
}

class Visitor2{
    public static void main(String[] args){
        City city = new City();

        city.addPlace(new Meseum());
        city.addPlace(new Park());

        VisitorInterface firstTimeVisitCity = new FirstTimeVisitCity();
        VisitorInterface secondTimeVisitCity =new  SecondTimeVisitCity();

        city.accept(firstTimeVisitCity);
        city.accept(secondTimeVisitCity);
    }
}
