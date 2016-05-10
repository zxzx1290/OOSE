import java.util.*;

interface AdapterInterface {
    public void printList(ArrayList<String> as);
}

class Adapter implements AdapterInterface {
    public void printList(ArrayList<String> as){
        String s="";
        for(String ss : as){
            s+=ss+"\n";
        }
        Adaptee at = new Adaptee();
        at.print(s);
    }
}

class Adaptee {
    public void print(String s){
        System.out.println(s);
    }
}

class testAdapter {
    public static void main(String[] args) {
        ArrayList<String> as = new ArrayList<String>();
        as.add("one");
        as.add("two");
        as.add("three");
        Adapter ap = new Adapter();
        ap.printList(as);
    }
}
