package com.example.toDoList.controller;

import com.example.toDoList.dao.TacheDao;
import com.example.toDoList.dao.UtilisateurDao;
import com.example.toDoList.dao.PrioriteDao;
import com.example.toDoList.dto.TacheUtilisateurDto;
import com.example.toDoList.model.Utilisateur;
import com.example.toDoList.model.Tache;
import com.example.toDoList.model.Priorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TacheUtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    private TacheDao tacheDao;

    @Autowired
    private PrioriteDao prioriteDao;

    // Obtenir toutes les associations tache_utilisateur avec prioriteId, tacheId, utilisateurId
    @GetMapping("/tache_utilisateur")
    public ResponseEntity<List<TacheUtilisateurDto>> getAllTacheUtilisateur() {
        List<TacheUtilisateurDto> tacheUtilisateurDtos = new ArrayList<>();

        // Récupère tous les utilisateurs
        List<Utilisateur> utilisateurs = utilisateurDao.findAll();

        for (Utilisateur utilisateur : utilisateurs) {
            for (Tache tache : utilisateur.getTaches()) {
                // Ici on suppose que la priorité est liée à la tâche, donc on récupère la priorité de la tâche
                Optional<Priorite> priorite = prioriteDao.findById(tache.getId());  // Si tu as un lien direct entre tache et priorite

                if (priorite.isPresent()) {
                    TacheUtilisateurDto dto = new TacheUtilisateurDto(
                            utilisateur.getId(),
                            tache.getId(),
                            priorite.get().getId()
                    );
                    tacheUtilisateurDtos.add(dto);
                }
            }
        }

        if (tacheUtilisateurDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tacheUtilisateurDtos, HttpStatus.OK);
    }
}
