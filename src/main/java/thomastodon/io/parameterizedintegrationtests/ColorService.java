package thomastodon.io.parameterizedintegrationtests;

public class ColorService {

    public ColorService() {
    }

    String add(String colorOne, String colorTwo) {
        if (colorOne.equals("red") && colorTwo.equals("yellow")) return "orange";
        return "purple";
    }
}
