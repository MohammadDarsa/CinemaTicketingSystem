package cinematicketingsystem.models.movie;

import cinematicketingsystem.models.category.Category;

public class HorrorMovie extends Movie{
    private Category category;
    private MovieType type;
    public HorrorMovie(MovieType type){
        this.category = new Category("Horror");
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    public MovieType getType() {
        return type;
    }
}
