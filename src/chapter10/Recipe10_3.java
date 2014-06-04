package chapter10;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * DefaultLocale in the original source code
 */
public class Recipe10_3 {
    private static final Date NOW = new Date();

    public static void main(String[] args) {
        Recipe10_3 recipe = new Recipe10_3();
        recipe.run();
    }

    private void run() {
        // Set ALL Locales to "fr-FR"
        Locale.setDefault(Locale.FRANCE);
        demoDefaultLocaleSettings();

        // System default is still "fr-FR"
        // DISPLAY default is "es-MX"
        // FORMAT default is "en-US"
        Locale.setDefault(Locale.Category.DISPLAY, Locale.forLanguageTag("es-MX"));
        Locale.setDefault(Locale.Category.FORMAT, Locale.US);
        demoDefaultLocaleSettings();

        // System default is still "fr-FR"
        // DISPLAY default is "en-US"
        // FORMAT default is "es-MX"
        Locale.setDefault(Locale.Category.DISPLAY, Locale.US);
        Locale.setDefault(Locale.Category.FORMAT, Locale.forLanguageTag("es-MX"));
        demoDefaultLocaleSettings();

        // System default is "en-US"
        // Reset both DISPLAY and FORMAT defaults to "en-US" as well
        Locale.setDefault(Locale.US);
        demoDefaultLocaleSettings();
    }

    public void demoDefaultLocaleSettings() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        ResourceBundle resource =
                ResourceBundle.getBundle("chapter10.SimpleResources",
                        Locale.getDefault(Locale.Category.DISPLAY));
        String greeting = resource.getString("GOOD_MORNING");
        String date = df.format(NOW);
        System.out.printf("DEFAULT LOCALE: %s%n", Locale.getDefault());
        System.out.printf("DISPLAY LOCALE: %s%n", Locale.getDefault(Locale.Category.DISPLAY));
        System.out.printf("FORMAT LOCALE:  %s%n", Locale.getDefault(Locale.Category.FORMAT));
        System.out.printf("%s %s%n%n", greeting, date);
    }
}
