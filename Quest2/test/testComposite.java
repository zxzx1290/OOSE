import java.util.*;
interface Component{
    public void addChild(Component c);
    public void printStruct(String preStr);
}
class Composite implements Component{
    private ArrayList<Component> array = null;
    private String str="";
    public Composite(String s){
        this.str=s;
    }
    public void addChild(Component c){
        if(array==null){
            array=new ArrayList<Component>();
        }
        array.add(c);
    }
    public void printStruct(String preStr){
        System.out.println(preStr+"+"+this.str);
        if(array!=null){
            for(Component c : array){
                c.printStruct(preStr+"  ");
            }
        }
    }
}
class Leaf implements Component{
    private String str;
    public Leaf(String s){
        this.str=s;
    }
    public void addChild(Component c){
        System.out.println("leaf can't add child");
    }
    public void printStruct(String preStr){
        System.out.println(preStr+"-"+this.str);
    }
}
public class testComposite{
    public static void main(String[] args) {
        Component root = new Composite("1");
        Component sub1 = new Composite("1-1");
        Component sub11 = new Composite("1-1-1");
        Component sub2 = new Composite("1-2");
        Component sub3 =new Composite("1-3");

        Component leaf1 = new Leaf("1-1-e");
        Component leaf2 = new Leaf("1-1-1-e");
        Component leaf3 = new Leaf("1-1-1-e2");
        Component leaf4 = new Leaf("1-2-e");
        Component leaf5 = new Leaf("1-2-e2");
        Component leaf6 = new Leaf("1-3-e");
        Component leaf7 = new Leaf("1-3-e2");

        root.addChild(sub1);
        root.addChild(sub2);
        root.addChild(sub3);

        sub1.addChild(leaf1);
        sub1.addChild(sub11);

        sub11.addChild(leaf2);
        sub11.addChild(leaf3);

        sub2.addChild(leaf4);
        sub2.addChild(leaf5);
        
        sub3.addChild(leaf6);
        sub3.addChild(leaf7);

        root.printStruct("");
    }
}
