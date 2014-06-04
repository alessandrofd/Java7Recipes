package chapter10;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * CurrencyOverride in the original source code.
 */
public class Recipe10_5 {
    public static void main(String[] args) {
        Recipe10_5 recipe = new Recipe10_5();
        recipe.run();
    }

    private void run() {
        BigDecimal value = new BigDecimal(12345);

        Locale.setDefault(Locale.JAPAN);
        System.out.printf("Default Locale: %s%n", Locale.getDefault().getDisplayName());
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String formattedCurrency = nf.format(value);
        System.out.printf("%s%n", formattedCurrency);
        nf.setCurrency(Currency.getInstance(Locale.US));
        formattedCurrency = nf.format(value);
        System.out.printf("%s%n%n", formattedCurrency);

        Locale.setDefault(Locale.US);
        System.out.printf("Default Locale: %s%n", Locale.getDefault().getDisplayName());
        nf = NumberFormat.getCurrencyInstance();
        formattedCurrency = nf.format(value);
        System.out.printf("%s%n", formattedCurrency);
        nf.setCurrency(Currency.getInstance("JPY"));
        formattedCurrency = nf.format(value);
        System.out.printf("%s%n%n", formattedCurrency);

        Locale.setDefault(Locale.FRANCE);
        System.out.printf("Default Locale: %s%n", Locale.getDefault().getDisplayName());
        nf = NumberFormat.getCurrencyInstance();
        formattedCurrency = nf.format(value);
        System.out.printf("%s%n", formattedCurrency);
        nf.setCurrency(Currency.getInstance("USD"));
        formattedCurrency = nf.format(value);
        System.out.printf("%s%n%n", formattedCurrency);

    }

}
