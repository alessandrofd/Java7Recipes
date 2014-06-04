package chapter05;

import java.util.Map;

/**
 * Created by Alessandro on 19/04/2014.
 */
public class Recipe5_5 {
    public static void main(String[] args) {
        System.out.println("user.dir: " + System.getProperty("user.dir"));
        for (Map.Entry<Object,Object> entry : System.getProperties().entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }

}
