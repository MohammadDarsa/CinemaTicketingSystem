package cinematicketingsystem.models.category;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.OneToMany;
import cinematicketingsystem.annotations.Table;
import cinematicketingsystem.models.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {
    @ID
    @Col(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    @Col(name = "name")
    private String name;
}
