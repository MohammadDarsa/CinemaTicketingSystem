package cinematicketingsystem.models.movie;
import java.awt.*;

public class Imax implements MovieType{

    @Override
    public String getName() {
        return "IMAX Movie";
    }

    @Override
    public String getDescription() {
        return "IMAX is a method of filming with higher image resolutions, and showing larger images on an IMAX movie theater screen.";
    }

    @Override
    public Color getCardColor() {
        return Color.orange;
    }
}
