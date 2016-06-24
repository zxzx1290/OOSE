class FacadeClass{
    private PrivateBay privateBay;
    private SimpleCD simpleCD;
    private PublicHD publicHD;
    private FileTube fileTube;
    FacadeClass(){
        privateBay = new PrivateBay();
        simpleCD = new SimpleCD();
        publicHD = new PublicHD();
        fileTube = new FileTube();
    }
    public void experiment(){
        privateBay.a();
        simpleCD.b();
        publicHD.c();
        fileTube.d();
    }
}

class PrivateBay{
    public void a(){
        System.out.println("get PrivateBay");
    }
}

class SimpleCD{
    public void b(){
        System.out.println("get SimpleCD");
    }
}

class PublicHD{
    public void c(){
        System.out.println("get PublicHD");
    }
}

class FileTube{
    public void d(){
        System.out.println("get FileTube");
    }
}

class Facade{
    public static void main(String[] args) {
        FacadeClass f = new FacadeClass();
        f.experiment();
    }
}
