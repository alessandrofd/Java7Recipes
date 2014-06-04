package chapter07.recipe7_03;

import java.util.ArrayList;
import java.util.List;

public enum StatisticsSingleton {
    INSTANCE;

    private final List teams = new ArrayList();

    public List getTeams() { return teams; }
}
