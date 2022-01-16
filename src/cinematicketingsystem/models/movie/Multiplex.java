package cinematicketingsystem.models.movie;

public class Multiplex implements MovieType{
    @Override
    public String name() {
        return "Multiplex";
    }

    @Override
    public String description() {
        return "A multiplex theater is a name for a single compound or building that houses more than five cinema auditoriums, each with their corresponding movie screens.";
    }

    @Override
    public String cardColor() {
        return "purple";
    }
}
