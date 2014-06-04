package chapter07.recipe7_01;

public class Player {
    private String firstName = null;
    private String lastName = null;
    private String position = null;
    private int status = -1;

    public Player() {

    }

    public Player(String position, int status) {
        this.position = position;
        this.status = status;
    }

    protected String playerStatus() {
        String returnValue = null;

        switch(getStatus()) {
            case 0:
                returnValue = "ACTIVE";
            case 1:
                returnValue = "INACTIVE";
            case 2:
                returnValue = "INJURY";
            default:
                returnValue = "ON_BENCH";
        }

        return returnValue;
    }

    public String playerString() { return getFirstName() + " " + getLastName() + " - " + getPosition(); }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
