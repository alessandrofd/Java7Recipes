package chapter02;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_10 {
    public static void main(String[] args) {
        findFileType("RegexExample.java");
    }

    public static void  findFileType(String fileName) {
        if (fileName.endsWith(".txt"))
            System.out.println("Text file");
        else if (fileName.endsWith(".doc"))
            System.out.println("Document file");
        else if (fileName.endsWith(".xls"))
            System.out.println("Excel file");
        else if (fileName.endsWith(".java"))
            System.out.println("Java source file");
        else
            System.out.println("Other type of file");
    }
}
