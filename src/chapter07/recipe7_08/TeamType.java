package chapter07.recipe7_08;

import chapter07.recipe7_04.Player;

import java.util.List;

public interface TeamType {
    void setPlayers(List<Player> players);
    void setName(String teamName);
    void setCity(String city);
    String getFullName();
}
