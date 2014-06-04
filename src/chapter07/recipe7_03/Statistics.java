package chapter07.recipe7_03;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    // Definition for the class instance
    private static final Statistics instance = new Statistics();

    private final List teams = new ArrayList();

    /**
     * Constructor has been made private so that outside classes do not have
     * access to instantiate more instances of Statistics.
     */
    private Statistics() { }

    /**
     * Accessor for the statistics class. Only allows for one instance of the
     * class to be created.
     */
    public static Statistics getInstance() {
        return instance;
    }

    public List getTeams() { return teams; }
}
