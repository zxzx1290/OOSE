class Pizza{
    private String dough="";
    private String sauce="";
    private String topping="";

    public void setDough(String dough){
        this.dough=dough;
    }
    public void setSauce(String sauce){
        this.sauce=sauce;
    }
    public void setTopping(String topping){
        this.topping=topping;
    }

    //printContent
    public void getContent(){
        System.out.println(dough+" "+sauce+" "+topping);
    }
}

abstract class PizzaBuilder{
    //Builder
    protected Pizza pizza;

    public Pizza getPizza(){
        return pizza;
    }
    public void createNewPizzaProduct(){
        pizza = new Pizza();
    }

    public abstract void buildDough();
    public abstract void buildSauce();
    public abstract void buildTopping();
}

class HawaiianPizzaBuilder extends PizzaBuilder{
    //Builder1
    public void buildDough(){
        pizza.setDough("cross");
    }
    public void buildSauce(){
        pizza.setSauce("mild");
    }
    public void buildTopping(){
        pizza.setTopping("ham+pineapple");
    }
}

class SpicyPizzaBuilder extends PizzaBuilder{
    //Builder2
    public void buildDough(){
        pizza.setDough("pan baked");
    }
    public void buildSauce(){
        pizza.setSauce("hot");
    }
    public void buildTopping(){
        pizza.setTopping("pepperoni+salami");
    }
}

class Waiter{
    //Supervisor
    private PizzaBuilder pizzabuilder;

    public void setPizzaBuilder(PizzaBuilder pb){
        pizzabuilder=pb;
    }
    public Pizza getPizza(){
        return pizzabuilder.getPizza();
    }
    public void constructPizza(){
        pizzabuilder.createNewPizzaProduct();
        pizzabuilder.buildDough();
        pizzabuilder.buildSauce();
        pizzabuilder.buildTopping();
    }
}

class Builder{
    //Client
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();
        PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();

        waiter.setPizzaBuilder(hawaiianPizzaBuilder);
        waiter.constructPizza();

        Pizza pizza = waiter.getPizza();

        //printContent
        pizza.getContent();
    }
}
