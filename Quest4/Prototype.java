interface PrototypeInterface{
    public void getName();
    public PrototypeInterface doClone();
}

class DVD implements PrototypeInterface{
    private String name;
    DVD(String n){
        name=n;
    }
    public void getName(){
        System.out.println("It is a "+name+" DVD");
    }
    public PrototypeInterface doClone(){
        System.out.println("copying");
        return new DVD(name);
    }
}

class Prototype{
    public static void main(String[] args) {
        DVD paul = new DVD("paul");
        paul.getName();
        DVD paul_copy = (DVD)paul.doClone();
        paul_copy.getName();
    }
}
