package cinematicketingsystem.models.movie;

import java.awt.*;

public class Multiplex implements MovieType{
    @Override
    public String getName() {
        return "Multiplex";
    }

    @Override
    public String getDescription() {
        return "A multiplex theater is a name for a single compound or building that houses more than five cinema auditoriums, each with their corresponding movie screens.";
    }

    @Override
    public Color getCardColor() {
        return Color.pink;
    }
}
