package chapter04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The example code for this recipe is the StockScreener class that allows you to screen a list of stocks or a single
 * stock based on a specific screen parameter (P/E, Yield, and Beta) and screen value. An example might be "Tell me
 * which of the stocks in this list has a P/E (price-to-earnings ratio) of 15 or less." Don't worry if you're not
 * familiar with these stock market terms. Whatever you do, don't use this class to make your stock investment
 * decisions!
 */


public class StockScreener {

    enum Screen { PE, YIELD, BETA };

    public static boolean screen(String stock, Screen screen, double threshold) {
        double screenVal = 0;
        boolean pass = false;
        switch (screen) {
            case PE:
                screenVal = Math.random() * 25;
                pass = screenVal <= threshold;
                break;
            case YIELD:
                screenVal = Math.random() * 10;
                pass = screenVal >= threshold;
                break;
            case BETA:
                screenVal = Math.random() * 2;
                pass = screenVal <= threshold;
                break;
        }
        System.out.println(stock + ": " + screen.toString() + " = " + screenVal);
        return pass;
    }

    public static void screen(List<String> stocks, Screen screen, double threshold) {
        Iterator<String> iter = stocks.iterator();
        while (iter.hasNext()) {
            String stock = iter.next();
            if (!screen(stock, screen, threshold)) {
                iter.remove();
            }
        }
    }

    public static void main(String[] args) {
        List<String> stocks = new ArrayList<>();
        stocks.add("ORCL");
        stocks.add("APPL");
        stocks.add("GOOG");
        stocks.add("IBM");
        stocks.add("MCD");
        System.out.println("Screening stocks: " + stocks);

        if (stocks.contains("GOOG") && !screen("GOOG", Screen.BETA, 1.1))
            stocks.remove("GOOG");
        System.out.println("First screen: " + stocks);

        StockScreener.screen(stocks, Screen.YIELD, 3.5);
        System.out.println("Second screen: " + stocks);

        StockScreener.screen(stocks, Screen.PE, 22);
        System.out.println("Third screen: " + stocks);

        System.out.println("Buy List: " + stocks);
    }
}
