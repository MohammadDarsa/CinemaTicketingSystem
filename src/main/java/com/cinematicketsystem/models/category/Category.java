package com.cinematicketsystem.models.category;

import com.cinematicketsystem.annotations.Col;
import com.cinematicketsystem.annotations.ID;
import com.cinematicketsystem.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
