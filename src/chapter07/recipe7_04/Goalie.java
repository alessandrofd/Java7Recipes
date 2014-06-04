package chapter07.recipe7_04;

public class Goalie extends Player implements PlayerType {
    private int totalSaves;

    public int getTotalSaves() {
        return totalSaves;
    }

    public void setTotalSaves(int totalSaves) {
        this.totalSaves = totalSaves;
    }
}
