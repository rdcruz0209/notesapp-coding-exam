package com.codingexam.notesapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//This will serve as the "entity"
@Getter
@Setter
@NoArgsConstructor
public class Notes {
    @JsonIgnore
    private int id;
    private String title;
    private String body;
}
