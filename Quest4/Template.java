abstract class Beverage{
    public final void templateMethod(){
        boilWater();
        brew();
        pourInCup();
        addCoundiments();
    }
    public abstract void addCoundiments();
    public abstract void brew();
    public final void boilWater(){
        System.out.println("boilWater");
    }
    public final void pourInCup(){
        System.out.println("pourInCup");
    }
}

class Coffee extends Beverage{
    public void addCoundiments(){
        System.out.println("add milk\n----------");
    }
    public void brew(){
        System.out.println("Steeping the coffee");
    }
}

class Tea extends Beverage{
    public void addCoundiments(){
        System.out.println("add Lemon");
    }
    public void brew(){
        System.out.println("Steeping the tea");
    }
}

class Template{
    public static void main(String[] args) {
        System.out.println("Make Coffee");
        Coffee coffee = new Coffee();
        coffee.templateMethod();

        System.out.println("Make Tea");
        Tea tea = new Tea();
        tea.templateMethod();
    }
}
