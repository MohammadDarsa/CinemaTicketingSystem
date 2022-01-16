package cinematicketingsystem.models.movie;

import java.awt.*;

public class Standard implements MovieType{
    @Override
    public String getName() {
        return "Standard";
    }

    @Override
    public String getDescription() {
        return "They used the old technologies, but as time progressed, these theaters also developed their technologies.";
    }

    @Override
    public Color getCardColor() {
        return Color.WHITE;
    }
}
