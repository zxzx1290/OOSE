import java.util.*;

abstract class Component{
    //Component
    public abstract void printStruct(String preStr);
    public abstract void addChild(Component child);
}

class CompositeClass extends Component{
    //Composite
    private ArrayList<Component> childComposites = null;
    private String name="";

    public CompositeClass(String name){
        this.name=name;
    }

    public void addChild(Component child){
        if(childComposites==null){
            childComposites=new ArrayList<Component>();
        }
        childComposites.add(child);
    }
    public void printStruct(String preStr){
        System.out.println(preStr+"+"+name);
        if(this.childComposites!=null){
            preStr+="  ";
            for(Component c : childComposites){
                c.printStruct(preStr);
            }
        }
    }
}

class Leaf extends Component{
    //Leaf
    private String name="";

    public Leaf(String name){
        this.name=name;
    }

    public void printStruct(String preStr){
        System.out.println(preStr+"-"+name);
    }
    public void addChild(Component child){
        System.out.println("不可加入Leaf");
    }
}

public class Composite{
    public static void main(String[] args) {
        Component root = new CompositeClass("服飾");
        Component c1 = new CompositeClass("男裝");
        Component c2 = new CompositeClass("女裝");

        Component leaf1 = new Leaf("襯衫");
        Component leaf2 = new Leaf("衣服");
        Component leaf3 = new Leaf("褲子");

        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(leaf3);
        c2.addChild(leaf1);
        c2.addChild(leaf2);

        root.printStruct("");
    }
}
