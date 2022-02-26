package com.cinematicketsystem.models.user.admin;

import com.cinematicketsystem.annotations.Col;
import com.cinematicketsystem.annotations.ID;
import com.cinematicketsystem.annotations.OneToMany;
import com.cinematicketsystem.annotations.Table;
import com.cinematicketsystem.models.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

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
