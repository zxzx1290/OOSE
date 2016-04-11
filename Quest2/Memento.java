public class Memento{
    public static void main(String[] args) {
        // have a game character
        Character character = new Character();
        // know a checkpoint
        CheckPoint checkpoint = new CheckPoint();
        // set character's state and show character state
        character.setState("------ This Character's HP and MP are 100%. ------");
        System.out.println("character now state :"+character.getState());
        System.out.println("");
        // go to beat monster(need record state in checkpoint)
        System.out.println("------ Character restore state in checkpoint. ------");
        checkpoint.setMemento(character.createMemento());
        System.out.println("");
        System.out.println("------ Character go to beat monster. ------");
        // kill by monstor
        System.out.println("------ Character was kill by monster. ------");
        character.changeState();
        System.out.println("character now state :"+character.getState());
        System.out.println("");
        //reborn in checkpoint and show now state
        System.out.println("------ Character reborn in checkpoint. ------");
        character.reborn(checkpoint.getMemento());
        System.out.println("character now state :"+character.getState());
    }
}

class Character{
    //State Diagram
    private String state="";

    public void changeState(){
        this.state="------ This character is die. ------";
    }

    public String getState(){
        return this.state;
    }

    public void setState(String _state){
        this.state=_state;
    }

    public MementoClass createMemento(){
        return new MementoClass(this.state);
    }

    public void reborn(MementoClass _memento){
        this.state = _memento.getState();
    }
}

class CheckPoint{
    //Edit Controller
    private MementoClass memento;

    public MementoClass getMemento(){
        return this.memento;
    }
    
    public void setMemento(MementoClass _memento){
        this.memento = _memento;
    }
}

class MementoClass{
    //Memento
    private String state="";

    public MementoClass(String _state){
        this.state = _state;
    }

    public String getState(){
        return this.state;
    }

    public void setState(String _state){
        this.state = _state;
    }
}
