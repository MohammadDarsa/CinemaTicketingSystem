package cinematicketingsystem.models.movie;

import cinematicketingsystem.annotations.*;
import cinematicketingsystem.models.user.admin.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {
    @ID
    @Col(name  = "mid")
    @EqualsAndHashCode.Include
    private Integer id;
    @Col(name = "mname")
    private String name;
    @Col(name = "description")
    private String description;
    @Col(name = "tor")
    private Timestamp screenPlayTime;
    @Col(name = "price")
    private Double price;
    @Col(name = "lang")
    private String language;
    @Col(name = "rating")
    private Double rating;
    @Col(name = "len")
    private Time length;
    @Col(name = "tickets_sold", updateIgnore = true, insertIgnore = true)
    private Integer ticketsSold;
    @Col(name = "image")
    private String imagePath;
    @ManyToOne(key = "admin_id")
    private Admin admin;
}
