interface BusinessStrategy{
    //Strategy
    //each strategy has its operate
    public void operate();
}

class LowStrategy implements BusinessStrategy{
    //Strategy1
    public void operate(){
        System.out.println("Use 20% off strategy");
    }
}

class MidStrategy implements BusinessStrategy{
    //Strategy2
    public void operate(){
        System.out.println("Use 25% off strategy");
    }
}

class HighStrategy implements BusinessStrategy{
    //Strategy3
    public void operate(){
        System.out.println("Use 50% off strategy");
    }
}

class NikeShop{
    //context
    private BusinessStrategy strategy;

    public NikeShop(BusinessStrategy s){
        //set strategy
        this.strategy=s;
    }
    //execute strategy
    public void operate(){
        this.strategy.operate();
    }
}

public class Strategy{
    public static void main(String[] args) {
        //Have a shop
        NikeShop nikeshop;

        nikeshop = new NikeShop(new LowStrategy());
        nikeshop.operate();

        nikeshop = new NikeShop(new MidStrategy());
        nikeshop.operate();

        nikeshop = new NikeShop(new HighStrategy());
        nikeshop.operate();
    }
}
