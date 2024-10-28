package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theater extends BaseModel{
    private String name;

    @OneToMany
    private List<Screen> screens;

    @ManyToOne
    private Region region;
}

/*

1              1
Theater ----- Region
 M             1



 1              M
Theater ----- Screen
 1              1

 */