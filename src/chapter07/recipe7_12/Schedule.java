package chapter07.recipe7_12;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Schedule {
    public String scheduleYear;
    public String teamName;

    public List<Team> teams;

    public List homeGames;
    public List awayGames;

    Map gameMap;

    public Schedule() { }

    public Schedule(String teamName) { this.teamName = teamName; }

    public Map obtainSchedule() {
        if (gameMap == null)
            gameMap = new HashMap();
        return gameMap;
    }

    public void setGameDate(Team team, Date date) {
        obtainSchedule().put(team, date);
    }

    abstract void calculateDaysPlayed(int month);
}
