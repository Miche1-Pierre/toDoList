package com.example.toDoList.controller;

import com.example.toDoList.dao.TacheDao;
import com.example.toDoList.dto.StatistiqueTacheDto;
import com.example.toDoList.model.Tache;
import com.example.toDoList.view.TacheView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class TacheController {

    @Autowired
    private TacheDao tacheDao;

    // Méthode utilitaire pour vérifier si une tâche existe
    private Optional<Tache> verifyTacheExistence(Integer id) {
        return tacheDao.findById(id);
    }

    @GetMapping("/tache")
    @JsonView(TacheView.class)
    public List<Tache> getAll() {
        return tacheDao.findAll();
    }

    @GetMapping("/tache/{id}")
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> get(@PathVariable Integer id) {
        Optional<Tache> optionalTache = verifyTacheExistence(id);

        if (optionalTache.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);  // Ajout d'un retour structuré
        }

        return ResponseEntity.ok(optionalTache.get());
    }

    @PostMapping("/tache")
    public ResponseEntity<Map<String, String>> create(@RequestBody Tache tache) {
        // Forcer l'id à null pour éviter tout conflit
        tache.setId(null);
        tacheDao.save(tache);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Tâche créée avec succès"));
    }

    @PutMapping("/tache/{id}")
    public ResponseEntity<Map<String, String>> update(@RequestBody Tache tache, @PathVariable Integer id) {
        Optional<Tache> optionalTache = verifyTacheExistence(id);

        if (optionalTache.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Tâche non trouvée"));
        }

        // Mise à jour de l'id de la tâche avec l'id passé en paramètre
        tache.setId(id);
        tacheDao.save(tache);

        return ResponseEntity.ok(Map.of("message", "Tâche mise à jour avec succès"));
    }

    @DeleteMapping("/tache/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        Optional<Tache> optionalTache = verifyTacheExistence(id);

        if (optionalTache.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Tâche non trouvée"));
        }

        tacheDao.deleteById(id);

        return ResponseEntity.ok(Map.of("message", "Tâche supprimée avec succès"));
    }

    @GetMapping("/statistique-tache")
    public List<StatistiqueTacheDto> getStatTache() {
        return tacheDao.nombrePersonneParTache();
    }
}
