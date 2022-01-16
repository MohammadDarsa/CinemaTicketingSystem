package cinematicketingsystem.models.movie;

public class Standard implements MovieType{
    @Override
    public String name() {
        return "Standard";
    }

    @Override
    public String description() {
        return "They used the old technologies, but as time progressed, these theaters also developed their technologies.";
    }

    @Override
    public String cardColor() {
        return "white";
    }
}
