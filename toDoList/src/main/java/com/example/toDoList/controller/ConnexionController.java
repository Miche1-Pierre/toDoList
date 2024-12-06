package com.example.toDoList.controller;

import com.example.toDoList.dao.UtilisateurDao;
import com.example.toDoList.model.Status;
import com.example.toDoList.model.Utilisateur;
import com.example.toDoList.security.AppUserDetails;
import com.example.toDoList.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class ConnexionController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    /**
     * Inscription d'un nouvel utilisateur.
     */
    @PostMapping("/inscription")
    public ResponseEntity<Map<String, String>> inscription(@RequestBody Utilisateur utilisateur) {
        // Vérifie si le pseudo est déjà utilisé
        Optional<Utilisateur> existingUser = utilisateurDao.findByPseudo(utilisateur.getPseudo());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "message", "Le pseudo est déjà utilisé."
            ));
        }

        // Cryptage du mot de passe et initialisation des champs nécessaires
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        utilisateur.setAdministrateur(false);
        Status disponible = new Status();
        disponible.setId(1); // ID correspondant à "disponible"
        utilisateur.setStatus(disponible);

        utilisateurDao.save(utilisateur);

        return ResponseEntity.ok(Map.of("message", "Enregistrement effectué avec succès."));
    }

    /**
     * Connexion d'un utilisateur.
     */
    @PostMapping("/connexion")
    public ResponseEntity<Map<String, String>> connexion(@RequestBody Utilisateur utilisateur) {
        try {
            // Authentification de l'utilisateur
            AppUserDetails appUserDetails = (AppUserDetails) authenticationProvider
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    utilisateur.getPseudo(),
                                    utilisateur.getPassword()))
                    .getPrincipal();

            // Génération et retour du token JWT
            String token = jwtUtils.generationToken(appUserDetails.getUsername());
            return ResponseEntity.ok(Map.of("token", token));

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                    "message", "Échec de l'authentification. Vérifiez vos identifiants."
            ));
        }
    }

    /**
     * Méthode de test pour la génération de token JWT.
     */
    @GetMapping("/test-jwt")
    public ResponseEntity<Map<String, String>> testJwt() {
        // Route pour tester la génération du token JWT
        String token = jwtUtils.generationToken("Le A");
        return ResponseEntity.ok(Map.of("token", token));
    }
}
