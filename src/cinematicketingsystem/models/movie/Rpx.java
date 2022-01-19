package cinematicketingsystem.models.movie;

import java.awt.*;

public class Rpx implements MovieType{
    @Override
    public String getName() {
        return "RPX";
    }

    @Override
    public String getDescription() {
        return "RPX is Regal's own large-format experience, offering bigger screens, newer projectors, and an updated sound system.";
    }

    @Override
    public Color getCardColor() {
        return Color.GRAY;
    }
}
