interface BeverageProducts{
    public void addMaterial();
    public void brew();
    public void pouredCup();
}

class BlackTea implements BeverageProducts{
    BlackTea(){
        System.out.println("I'm BlackTea");
    }
    public void addMaterial(){
        System.out.println("Add black tea leaves");
    }
    public void brew(){
        System.out.println("Tea brewing");
    }
    public void pouredCup(){
        System.out.println("Poured cup");
    }
}

class GreenTea implements BeverageProducts{
    GreenTea(){
        System.out.println("I'm GreenTea");
    }
    public void addMaterial(){
        System.out.println("Add green tea leaves");
    }
    public void brew(){
        System.out.println("Tea brewing");
    }
    public void pouredCup(){
        System.out.println("Poured cup");
    }
}

abstract class BeverageCreator{
    protected abstract BeverageProducts createBeverage();
    public void algorithmMethod(){
        BeverageProducts product = createBeverage();
        product.addMaterial();
        product.brew();
        product.pouredCup();
    }
}

class BlackTeaCreator extends BeverageCreator{
    BlackTeaCreator(){
        System.out.println("BlackTea factory");
    }
    protected BeverageProducts createBeverage(){
        return new BlackTea();
    }
}

class GreenTeaCreator extends BeverageCreator{
    GreenTeaCreator(){
        System.out.println("GreenTea factory");
    }
    protected BeverageProducts createBeverage(){
        return new GreenTea();
    }
}

class Factory{
    public static void main(String[] args) {
        BeverageCreator b1 = new BlackTeaCreator();
        b1.algorithmMethod();
        System.out.println();
        BeverageCreator b2 = new GreenTeaCreator();
        b2.algorithmMethod();
    }
}
