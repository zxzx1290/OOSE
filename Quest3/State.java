interface GearState {
    public void press(Button button);
}

class Button {
    private GearState gearstate;
    public Button(GearState gs) {
        this.gearstate = gs;
    }
    public void setGearState(GearState gs) {
        this.gearstate = gs;
    }
    public void press() {
        this.gearstate.press(this);
    }
}

class NGear implements GearState {
    public void press(Button button) {
        System.out.println("Now in NGear");
        button.setGearState(new FirstGear());
    }
}

class FirstGear implements GearState {
    public void press(Button button) {
        System.out.println("Now in FirstGear");
        button.setGearState(new SecondGear());
    }
}

class SecondGear implements GearState {
    public void press(Button button) {
        System.out.println("Now in SecondGear");
        button.setGearState(new NGear());
    }
}

public class State {
    public static void main(String[] args) {
        Button button = new Button(new NGear());
        button.press();
        button.press();
        button.press();
        button.press();
    }
}
