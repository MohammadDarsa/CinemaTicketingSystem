package cinematicketingsystem.models.movie;

public class Imax implements MovieType{
    @Override
    public String name() {
        return "IMAX Movie";
    }

    @Override
    public String description() {
        return "IMAX is a method of filming with higher image resolutions, and showing larger images on an IMAX movie theater screen.";
    }

    @Override
    public String cardColor() {
        return "gold";
    }
}
