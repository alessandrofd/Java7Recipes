package chapter07.recipe7_06;

import chapter07.recipe7_04.Player;

import java.util.List;

public class Team implements TeamType {
    private List<Player> players;
    private String name;
    private String city;

    public String getFullName() { return this.name + " - " + this.city; }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
