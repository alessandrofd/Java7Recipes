package chapter10;

import java.io.*;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * StreamConversion.java in the original source code.
 */
public class Recipe10_7_1 {
    public static void main(String[] args) throws IOException {
        Recipe10_7_1 recipe = new Recipe10_7_1();
        recipe.run();
    }

    private void run() {
        try {
            String input = readStream();
            System.out.println("Input stream: " + input);
            writeStream(input);
        } catch (IOException e) {
            Logger.getLogger(Recipe10_7_1.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String readStream() throws IOException {
        InputStream is = getClass().getResourceAsStream("helloworld.sjis.txt");
        InputStreamReader reader = null;
        StringBuilder sb = new StringBuilder();
        if (is != null) {
            reader = new InputStreamReader(is, Charset.forName("SJIS"));
            int ch = reader.read();
            while (ch != -1) {
                sb.append((char)ch);
                ch = reader.read();
            }
            reader.close();
        }
        return sb.toString();
    }

    public void writeStream(String text) throws IOException {
        OutputStreamWriter writer = null;
        FileOutputStream fos = new FileOutputStream("helloworld.utf8.txt");
        writer = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
        writer.write(text);
        writer.close();
    }
}
