package chapter10;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BufferConversion.java in the original source code.
 */
public class Recipe10_7_2 {
    private String unicodeString = "こんにちは、世界！";

    public static void main(String[] args) {
        Recipe10_7_2 recipe = new Recipe10_7_2();
        recipe.run();
    }

    private void run() {
        try {
            System.out.println("Original string: " + unicodeString);
            CharBuffer srcBuffer = CharBuffer.wrap(unicodeString);
            ByteBuffer targetBytes = encodeBuffer("UTF-8", srcBuffer);
            printBytes(targetBytes);
            CharBuffer roundtripBuffer = decodeBuffer("UTF-8", targetBytes);
            printCharBuffer(roundtripBuffer);
        } catch (CharacterCodingException e) {
            Logger.getLogger(Recipe10_7_1.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public ByteBuffer encodeBuffer(String charsetName, CharBuffer charBuffer) throws CharacterCodingException {
        Charset charset = Charset.forName(charsetName);
        CharsetEncoder encoder = charset.newEncoder();
        ByteBuffer targetBuffer = encoder.encode(charBuffer);
        return targetBuffer;
    }

    public CharBuffer decodeBuffer(String charsetName, ByteBuffer byteBuffer) throws CharacterCodingException {
        Charset charset = Charset.forName(charsetName);
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = decoder.decode(byteBuffer);
        return charBuffer;
    }

    public void printBytes(ByteBuffer bytes) {
        System.out.print("Bytes: ");
        byte[] byteArray = bytes.array();
        for (byte b : byteArray) {
            if (b == 0)
                break;
            System.out.printf("%2x ", b);
        }
        System.out.printf("%n");
    }

    public void printCharBuffer(CharBuffer charBuffer) {
        String s = charBuffer.toString();
        System.out.printf("Roundtrip string: %s%n", s);
    }
}
