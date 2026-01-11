package com.badlogic.echoesofthecrypt.model.entity;

import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;

/**
 * Modèle représentant un ennemi.
 *
 * <p>
 * Hérite de {@link EntityModel}. Contient toutes les informations
 * relatives à la position, vitesse, texture et état de l'ennemi.
 * </p>
 */
public class EnemyModel extends EntityModel {

    public EnemyModel(TiledDTO data) {
        super(data);
    }
}
