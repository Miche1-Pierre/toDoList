package com.example.toDoList.dao;

import com.example.toDoList.model.Priorite;
import com.example.toDoList.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioriteDao extends JpaRepository<Priorite, Integer> {
}
