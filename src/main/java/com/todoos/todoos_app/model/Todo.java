package com.todoos.todoos_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="todoos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int todoId;
    String headline;
    String description;
}
