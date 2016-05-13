class Helper {
    Helper successor=null;
    String info=null;
    public void create(Helper suc, String info){
        this.successor=suc;
        this.info=info;
    }
    public void help(){
        if(successor!=null){
            successor.help();
        }else{
            showHelp();
        }
    }
    public boolean hasHelp(){
        return info!=null;
    }
    public void showHelp(){
        System.out.println("no help info");
    }
}

class EditorHelper extends Helper {
    public void help(){
        if(hasHelp()){
            showHelp();
        }else{
            super.help();
        }
    }
    public void showHelp(){
        System.out.println(this.info);
    }
}

class WidgetHelper extends Helper {

}

class DialogHelper extends WidgetHelper {
    public void help(){
        if(hasHelp()){
            showHelp();
        }else{
            super.help();
        }
    }
    public void showHelp(){
        System.out.println(this.info);
    }
}

class ConditionHelper extends WidgetHelper {
    public void help(){
        if(hasHelp()){
            showHelp();
        }else{
            super.help();
        }
    }
    public void showHelp(){
        System.out.println(this.info);
    }
}

class ButtonHelper extends WidgetHelper {
    public void help(){
        if(hasHelp()){
            showHelp();
        }else{
            super.help();
        }
    }
    public void showHelp(){
        System.out.println(this.info);
    }
}

class P419 {
    public static void main(String[] args) {
        Helper bh = new ButtonHelper();
        Helper ch = new ConditionHelper();
        Helper dh = new DialogHelper();
        bh.create(ch,null);
        ch.create(dh,"Condition Help");
        dh.create(null,"Dialog Help");
        bh.help();
    }
}
