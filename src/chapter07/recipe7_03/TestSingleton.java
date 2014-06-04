package chapter07.recipe7_03;

import java.util.List;

/**
 * Created by Alessandro.Dantas on 29/04/2014.
 */
public class TestSingleton {

    public static void main(String[] args) {
        StatisticsSingleton stats = StatisticsSingleton.INSTANCE;

        System.out.println("Adding objects to the list using stats object");

        List myList = stats.getTeams();
        myList.add("One");
        myList.add("Two");

        System.out.println("Reading objects from the list using stats2 object");
        StatisticsSingleton stats2 = StatisticsSingleton.INSTANCE;
        List myList2 = stats2.getTeams();
        for (Object name: myList2)
            System.out.println(name.toString());
    }
}
