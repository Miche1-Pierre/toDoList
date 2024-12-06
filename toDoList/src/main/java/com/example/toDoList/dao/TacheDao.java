package com.example.toDoList.dao;

import com.example.toDoList.dto.StatistiqueTacheDto;
import com.example.toDoList.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TacheDao extends JpaRepository<Tache, Integer> {

    @Query("SELECT new com.example.toDoList.dto.StatistiqueTacheDto(c.nom, COUNT(u) ) " +
            "FROM Tache c " +
            "LEFT JOIN c.utilisateurs u " +
            "GROUP BY c.nom")
    List<StatistiqueTacheDto> nombrePersonneParTache();

    @Query("FROM Tache t JOIN t.utilisateurs u WHERE u.id = :userId")
    List<Tache> findTachesByUtilisateurId(@Param("userId") Integer userId);
}