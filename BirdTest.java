abstract class FlyComponent {
	public abstract void fly();
}

class C_Fly extends FlyComponent {
	@Override
	public void fly() {
		System.out.println("I can fly");
	}

}

class D_Fly extends FlyComponent {
	@Override
	public void fly() {
		System.out.println("I can not fly");
	}
}

class Bird {
	private FlyComponent flycomponent;

	public void eat(){
		System.out.println("eat something...");
	}

	public void setFlyComponent(FlyComponent flycomponent){
		this.flycomponent = flycomponent;
	}

	public void FlyOperation(){
		this.flycomponent.fly();
	}
}


class Crow extends Bird {
	public Crow(){
		System.out.println("I'm crow");
	}
}


class Duck extends Bird {
	public Duck(){
		System.out.println("I'm duck");
	}
}


public class BirdTest {
	public static void main(String args[]){
		Bird crow = new Crow();
		crow.setFlyComponent(new C_Fly());
		crow.FlyOperation();
		Bird duck = new Duck();
		duck.setFlyComponent(new D_Fly());
		duck.FlyOperation();
	}
}
