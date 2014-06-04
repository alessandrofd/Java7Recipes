package chapter07.recipe7_11;

public class HockeyStick {
    public int length;
    public boolean isCurved;
    public String material;

    public HockeyStick(int length, boolean isCurved, String material) {
        this.length = length;
        this.isCurved = isCurved;
        this.material = material;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isCurved() {
        return isCurved;
    }

    public void setCurved(boolean isCurved) {
        this.isCurved = isCurved;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
