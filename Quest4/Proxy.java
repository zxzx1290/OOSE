interface Image {
    public void displayImage();
}

class ProxyImage implements Image{
    private RealImage image = null;
    private String filename = null;
    ProxyImage(String filename){
        this.filename=filename;
    }
    public void displayImage(){
        if(image==null){
            image=new RealImage(filename);
        }
        image.displayImage();
    }
}

class RealImage implements Image{
    private String filename = null;
    RealImage(String filename){
        this.filename=filename;
        loadImageFromDisk();
    }
    private void loadImageFromDisk(){
        System.out.println("Loading "+filename);
    }
    public void displayImage(){
        System.out.println("Displaying "+filename);
    }
}

class Proxy{
    public static void main(String[] args) {
        Image image1 = new ProxyImage("HiRes_10MB_Photo1");
        Image image2 = new ProxyImage("HiRes_10MB_Photo2");
        image1.displayImage();
        image1.displayImage();
        image2.displayImage();
        image2.displayImage();
    }
}
