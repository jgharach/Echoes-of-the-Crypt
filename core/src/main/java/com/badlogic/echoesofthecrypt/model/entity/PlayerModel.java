package com.badlogic.echoesofthecrypt.model.entity;

import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;

/**
 * Modèle représentant le joueur.
 *
 * <p>
 * Hérite de {@link EntityModel}. Contient toutes les informations
 * relatives à la position, vitesse, texture et état du joueur.
 * </p>
 */
public class PlayerModel extends EntityModel {

    public PlayerModel(TiledDTO data) {
        super(data);
    }
}

