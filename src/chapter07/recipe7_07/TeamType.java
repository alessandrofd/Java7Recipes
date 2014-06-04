package chapter07.recipe7_07;

import chapter07.recipe7_04.Player;

import java.util.List;

public interface TeamType {
    void setPlayers(List<Player> players);
    void setName(String name);
    void setCity(String city);
    String getFullName();
}
