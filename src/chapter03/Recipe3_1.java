

/**
 * Created by Alessandro on 08/04/2014.
 */
public class Recipe3_1 {

    public static void main(String[] args) {
        System.out.println(roundFloatToInt(new Float("8.837")));
        System.out.println(roundDoubleToLong(new Double("9.9")));
    }

    public static int roundFloatToInt(float myFloat) { return Math.round(myFloat); }

    public static long roundDoubleToLong(double myDouble) { return Math.round(myDouble); }
}
