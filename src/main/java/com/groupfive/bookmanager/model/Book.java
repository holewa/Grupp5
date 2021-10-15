package com.groupfive.bookmanager.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private String author;
    private String imgUrl;
    private Integer publishYear;
    private Integer rating;
    }
