import java.util.regex.Matcher;
import java.util.Scanner;

class Q33 {
    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);
        String test = scanIn.nextLine();
        scanIn.close();
        if(test.matches("(^(YY).*$)")){
            System.out.println("1-Stay");
        }else if(test.matches("(^(YN).*$)")){
            System.out.println("2-Quit");
        }else if(test.matches("(^(NYY).*$)")){
            System.out.println("3-Stay");
        }else if(test.matches("(^(NNY).*$)")){
            System.out.println("4-Quit");
        }else if(test.matches("(^(N(Y|N)NY).*$)")){
            System.out.println("5-Stay");
        }else if(test.matches("(^(N(Y|N)NN).*$)")){
            System.out.println("6-Quit");
        }else{
            System.out.println("Error");
        }
    }
}
