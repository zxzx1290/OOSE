interface TextComponent {
    public String getMessage();
}

class Text implements TextComponent {
    private String message;
    public Text(String m){
        this.message=m;
    }
    public String getMessage(){
        return message;
    }
}

abstract class TextDecorator implements TextComponent {
    protected TextComponent textComponent;
    public TextDecorator(TextComponent textComponent){
        System.out.println(textComponent);
        this.textComponent=textComponent;
    }
}

class ShadowText extends TextDecorator {
    public ShadowText(TextComponent textComponent){
        super(textComponent);
        System.out.println("ShadowText called");
    }
    public String getMessage(){
        return textComponent.getMessage()+" + shadow";
    }
}

class BorderText extends TextDecorator {
    public BorderText(TextComponent textComponent){
        super(textComponent);
        System.out.println("BorderText called");
    }
    public String getMessage(){
        return textComponent.getMessage()+" + border";
    }
}

public class Decorator{
    public static void main(String[] args) {
        TextComponent text = new Text("Hello");
        System.out.println(text.getMessage());
        text=new ShadowText(text);
        text=new BorderText(text);
        System.out.println(text.getMessage());
    }
}
