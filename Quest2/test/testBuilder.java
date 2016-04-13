class Product{
    private String feature1="";
    private String feature2="";
    public void setFeature1(String f1){
        this.feature1=f1;
    }
    public void setFeature2(String f2){
        this.feature2=f2;
    }
    public void printS(){
        System.out.println(feature1+" + "+feature2);
    }
}
abstract class Builder{
    protected Product product;
    public Product getProduct(){
        return product;
    }
    public void newProduct(){
        product = new Product();
    }
    public abstract void buildFeature1();
    public abstract void buildFeature2();
}
class ProductA extends Builder{
    public void buildFeature1(){
        product.setFeature1("ProductAfeature1");
    }
    public void buildFeature2(){
        product.setFeature2("ProductAfeature2");
    }
}
class ProductB extends Builder{
    public void buildFeature1(){
        product.setFeature1("ProductBfeature1");
    }
    public void buildFeature2(){
        product.setFeature2("ProductBfeature2");
    }
}
class Director{
    private Builder builder;
    public void setBuilder(Builder b){
        this.builder=b;
    }
    public Product getProduct(){
        return this.builder.getProduct();
    }
    public void constructProduct(){
        builder.newProduct();
        builder.buildFeature1();
        builder.buildFeature2();
    }
}
public class testBuilder{
    public static void main(String[] args) {
        Builder pa = new ProductA();
        Director d = new Director();
        d.setBuilder(pa);
        d.constructProduct();
        Product p = d.getProduct();
        p.printS();
    }
}
