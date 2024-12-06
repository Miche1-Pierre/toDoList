package com.example.toDoList.dao;

import com.example.toDoList.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    // Remplacer la méthode findByEmail par findByPseudo
    Optional<Utilisateur> findByPseudo(String pseudo);

    // Modifier la requête JPQL pour utiliser 'pseudo' au lieu de 'email'
    @Query("SELECT u FROM Utilisateur u JOIN FETCH u.taches WHERE u.pseudo = :pseudo")
    Optional<Utilisateur> trouveParPseudo(@Param("pseudo") String pseudo);
}
