import java.util.*;

interface PrintableList {
    public void printList(ArrayList<String> list);
}

class PrintableListAdapter implements PrintableList {
    public void printList(ArrayList<String> list){
        String ls="";
        for(String s : list){
            ls+=s+"\n";
        }
        PrintString ps = new PrintString();
        ps.print(ls);
    }
}

class PrintString {
    public void print(String s){
        System.out.println(s);
    }
}

public class Adapter {
    public static void main(String[] args) {
        ArrayList<String> l = new ArrayList<String>();
        l.add("one");
        l.add("two");
        l.add("three");
        PrintableList pl = new PrintableListAdapter();
        pl.printList(l);
    }
}
