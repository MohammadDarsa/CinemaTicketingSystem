package cinematicketingsystem.models.user.admin;

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
@Table(name = "admin")
public class Admin {
    @ID
    @Col(name  = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    @Col(name  = "aname")
    private String name;
    @Col(name  = "address")
    private String address;
    @Col(name  = "phone")
    private Integer phone;
    @Col(name  = "email")
    private String email;
    @Col(name  = "password")
    private String password;
    @OneToMany(key = "admin_id")
    private List<Movie> movieList;
}
