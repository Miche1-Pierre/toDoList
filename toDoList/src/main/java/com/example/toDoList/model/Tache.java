package com.example.toDoList.model;

import com.example.toDoList.view.TacheView;
import com.example.toDoList.view.UtilisateurAvecTacheView;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(TacheView.class)
    Integer id;

    @JsonView({UtilisateurAvecTacheView.class, TacheView.class})
    String nom;

    @JsonView(TacheView.class)
    String description;

    @JsonView(TacheView.class)
    boolean valide;

    @ManyToMany(mappedBy = "taches")
    @JsonIgnore
    @JsonView(TacheView.class)
    List<Utilisateur> utilisateurs = new ArrayList<>();
}
