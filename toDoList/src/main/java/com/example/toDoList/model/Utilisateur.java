package com.example.toDoList.model;

import com.example.toDoList.view.TacheView;
import com.example.toDoList.view.UtilisateurAvecTacheView;
import com.example.toDoList.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({TacheView.class, TacheView.class})
    Integer id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Le Pseudo ne peut pas être vide")
    @JsonView({TacheView.class})
    String pseudo;

    String password;
    Boolean administrateur;

    @ManyToOne
    @JsonView(TacheView.class)
    Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tache_utilisateur",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "tache_id")
    )
    @JsonView({UtilisateurAvecTacheView.class})
    @JsonIgnore
    List<Tache> taches;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tache_utilisateur",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "priorite_id")
    )
    @JsonView({UtilisateurAvecTacheView.class})
    List<Priorite> priorites;
}
