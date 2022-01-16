package cinematicketingsystem.models.movie;

public class Rpx implements MovieType{
    @Override
    public String name() {
        return "RPX";
    }

    @Override
    public String description() {
        return "RPX is Regal's own large-format experience, offering bigger screens, newer projectors, and an updated sound system.";
    }

    @Override
    public String cardColor() {
        return "silver";
    }
}
