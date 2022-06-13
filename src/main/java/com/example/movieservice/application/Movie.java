package com.example.movieservice.application;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "Movie")
@ApiModel("data of Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("unique id of Movie")
    private Integer id;
    @Column(name = "name")
    @ApiModelProperty("Name of Movie")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private MovieCategory category;
    @Column(name = "isAvailable")
    private boolean isAvailable;

    public Movie(String name, MovieCategory category) {
        this.name = name;
        this.category = category;
        this.isAvailable = false;
    }

    public boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public MovieCategory getCategory() {
        return category;
    }
    public void setCategory(MovieCategory category) {
        this.category = category;
    }
    public Movie(Integer id, String name, MovieCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
    public Movie( String name) {
        this.name = name;
    }
    public Movie() {

    }



}
