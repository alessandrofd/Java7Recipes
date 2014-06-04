package chapter07.recipe7_04;

public class PlayerFactory {

    public static PlayerType createPlayer(String playerType) {
        PlayerType returnType;
        switch (playerType) {
            case "GOALIE":
                returnType = new Goalie();
                break;
            case "LEFT":
                returnType = new LeftWing();
                break;
            case "RIGHT":
                returnType = new RightWing();
                break;
            case "CENTER":
                returnType = new Center();
            break;
            case "DEFENSE":
                returnType = new Defense();
                break;
            default:
                returnType = new AllPosition();
        }
        return returnType;
    }
}
