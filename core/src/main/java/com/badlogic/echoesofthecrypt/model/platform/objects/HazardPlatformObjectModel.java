package com.badlogic.echoesofthecrypt.model.platform.objects;

import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;

/**
 * Modèle représentant une plateforme dangereuse dans le jeu.
 *
 * <p>
 * Cette classe hérite de {@link PlatformObjectModel} et permet de distinguer
 * les plateformes “hazard” des autres types de plateformes définies dans Tiled.
 * Aucune logique de gameplay (collision, dégâts, etc.) n’est implémentée dans cette version.
 * </p>
 *
 * <p>
 * Elle est utilisée par la {@link com.badlogic.echoesofthecrypt.model.factory.PlatformObjectModelFactory}
 * pour instancier les plateformes depuis les données Tiled.
 * </p>
 */
public class HazardPlatformObjectModel extends PlatformObjectModel {

    /**
     * Crée une nouvelle plateforme dangereuse à partir des données du TiledDTO.
     *
     * @param data données provenant de l’objet Tiled, incluant position, taille et texture.
     */
    public HazardPlatformObjectModel(TiledDTO data) {
        super(data);
    }
}

