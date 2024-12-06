package com.example.toDoList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TacheUtilisateurDto {

    private Integer utilisateurId;
    private Integer tacheId;
    private Integer prioriteId;
}
