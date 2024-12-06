-- Insérer les données dans la table Status
INSERT INTO status (designation) VALUES
                                     ("disponible"),
                                     ("occupé"),
                                     ("absent");

-- Insérer les données dans la table Utilisateur
-- mot de passe = root (bcrypté)
INSERT INTO utilisateur (pseudo, password, administrateur, status_id) VALUES
                                                                         ("le_A", "$2a$10$31nhEmGLow2iIug.qqq6RuG3GXv1fo6wXfojXNswxqYqwR8kUJUEm", 1, 1),
                                                                         ("le_B", "$2a$10$31nhEmGLow2iIug.qqq6RuG3GXv1fo6wXfojXNswxqYqwR8kUJUEm", 0, 2);

-- Insérer les données dans la table Tâche
INSERT INTO tache (nom, description, valide) VALUES
                            ("Projet 1", "Description Projet 1", 1),
                            ("Projet 2", "Description Projet 2", 0),
                            ("Projet 3", "Description Projet 3", 1);

-- Insérer les données dans la table Priorité
INSERT INTO priorite (etiquette) VALUES
                                     ("urgent"),
                                     ("modéré"),
                                     ("à venir");

-- Insérer les relations dans la table de jointure Tache-Utilisateur
INSERT INTO tache_utilisateur (utilisateur_id, tache_id, priorite_id) VALUES
                                                                          (1, 2, 1),
                                                                          (1, 3, 2),
                                                                          (2, 1, 3),
                                                                          (2, 2, 2);