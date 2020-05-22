import org.mariuszgromada.math.mxparser.Function;

public class Test {

    public static void main(String[] args) {
        Function test = new Function("f", "x+y", "x", "y");
        System.out.println(test.calculate(2, 3));

    }

}