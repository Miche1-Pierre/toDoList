package com.example.toDoList.model;

import com.example.toDoList.view.TacheView;
import com.example.toDoList.view.UtilisateurAvecTacheView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Priorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonView(UtilisateurAvecTacheView.class)
    String etiquette;
}
