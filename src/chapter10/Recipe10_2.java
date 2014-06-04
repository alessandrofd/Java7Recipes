package chapter10;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * LocaleCreator.java in the original source code.
 */
public class Recipe10_2 {
    private static final long SAMPLE_NUMBER = 123456789L;
    private static final Date NOW = new Date();

    public static void main(String[] args) {
        Recipe10_2 recipe = new Recipe10_2();
        recipe.run();
    }

    private void run() {
        createFromBuilder();
        createFromLanguageTag();
        createFromConstructor();
        createFromStatics();
    }

    private void createFromBuilder() {
        System.out.printf("Creating from Builder...%n%n");
        String[][] langRegions = {{"fr", "FR"}, {"ja", "JP"}, {"en", "US"}};
        Locale.Builder builder = new Locale.Builder();
        Locale l = null;
        for (String[] lr : langRegions) {
            builder.clear();
            builder.setLanguage(lr[0]).setRegion(lr[1]);
            l = builder.build();
            displayLocalizedData(l, SAMPLE_NUMBER, NOW);
        }
    }

    private void createFromLanguageTag() {
        System.out.printf("Creating from BCP 47 language tags...%n%n");
        String[] bcp47LangTags = {"fr-FR", "ja-JP", "en-US"};
        Locale l = null;
        for (String langTag : bcp47LangTags) {
            l = Locale.forLanguageTag(langTag);
            displayLocalizedData(l, SAMPLE_NUMBER, NOW);
        }
    }

    private void createFromConstructor() {
        System.out.printf("Creating from Locale constructor...%n%n");
        String[][] langRegions = {{"fr", "FR"}, {"ja", "JP"}, {"en", "US"}};
        Locale l = null;
        for (String[] lr : langRegions) {
            // lr[0] is ISO 629 language code and lr[1] is ISO 3166 region
            l = new Locale(lr[0], lr[1]);
            displayLocalizedData(l, SAMPLE_NUMBER, NOW);
        }
    }

    private void createFromStatics() {
        System.out.printf("Creating from Locale static constants...%n%n");
        Locale[] locales = new Locale[]{Locale.FRANCE, Locale.JAPAN, Locale.US};
        for (Locale l : locales) {
            displayLocalizedData(l, SAMPLE_NUMBER, NOW);
        }
    }

    private void displayLocalizedData(Locale l, long number, Date date) {
        NumberFormat nf = NumberFormat.getInstance(l);
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, l);
        System.out.printf("Locale: %s\tNumber: %s\tDate: %s%n%n", l.getDisplayName(), nf.format(number), df.format(date));
    }
}
