interface AbstractFactory{
    public aCategory genA();
    public bCategory genB();
}
class ConcreteFactory1 implements AbstractFactory{
    public aCategory genA(){
        return new Product1A();
    }
    public bCategory genB(){
        return new Product1B();
    }
}
class ConcreteFactory2 implements AbstractFactory{
    public aCategory genA(){
        return new Product2A();
    }
    public bCategory genB(){
        return new Product2B();
    }
}
interface aCategory{
    public void operatea();
}
class Product1A implements aCategory{
    public void operatea(){
        System.out.println("Product1A");
    }
}
class Product2A implements aCategory{
    public void operatea(){
        System.out.println("Product2A");
    }
}
interface bCategory{
    public void operateb();
}
class Product1B implements bCategory{
    public void operateb(){
        System.out.println("Product1B");
    }
}
class Product2B implements bCategory{
    public void operateb(){
        System.out.println("Product2B");
    }
}
public class testAbstractFactory{
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        aCategory factory1genA = (aCategory)factory1.genA();
        factory1genA.operatea();
        bCategory factory1genB = (bCategory)factory1.genB();
        factory1genB.operateb();

        AbstractFactory factory2 = new ConcreteFactory2();
        aCategory factory2genA = (aCategory)factory2.genA();
        factory2genA.operatea();
        bCategory factory2genB = (bCategory)factory2.genB();
        factory2genB.operateb();
    }
}
