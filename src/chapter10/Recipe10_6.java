package chapter10;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * CharacterEncodingConversion.java in the original source code
 */
public class Recipe10_6 {
    public static void main(String[] args) {
        Recipe10_6 recipe = new Recipe10_6();
        recipe.run();
    }

    public void run(){
        byte[] legacySJIS = {(byte)0x82,(byte)0xB1,(byte)0x82,(byte)0xF1,(byte)0x82,(byte)0xC9,(byte)0x82,(byte)0xBF,
                             (byte)0x82,(byte)0xCD,(byte)0x81,(byte)0x41,(byte)0x90,(byte)0xA2,(byte)0x8A,(byte)0x45,
                             (byte)0x81,(byte)0x49};

        // Convert a byte[] to a String
        Charset cs = Charset.forName("SJIS");
        String greeting = new String(legacySJIS, cs);
        System.out.println("Greeting: " + greeting);

        // Convert a String to a byte[]
        byte[] toSJIS = greeting.getBytes(cs);

        // Confirm that the original array and the newly converted array are equal.
        boolean same = true;
        if (legacySJIS.length == toSJIS.length) {
            for (int x = 0; x < legacySJIS.length; x++) {
                if (legacySJIS[x] != toSJIS[x]) {
                    same = false;
                    break;
                }
            }
        } else same = false;
        System.out.println("Same: " + same);

        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        Iterator<Charset> cc = charsets.values().iterator();
        int size = charsets.size();
        while (cc.hasNext())
            System.out.println("Charset: " + cc.next().displayName());
        System.out.println("Count: " + charsets.size());

    }
}
