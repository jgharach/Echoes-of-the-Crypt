package com.badlogic.echoesofthecrypt.model.platform.objects;

import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;


/**
 * Modèle représentant une plateforme solide du jeu.
 *
 * <p>
 * Cette classe est une spécialisation de {@link PlatformObjectModel}.
 * Elle permet d'identifier explicitement les plateformes considérées
 * comme solides dans le monde du jeu.
 * </p>
 *
 * <p>
 * Dans cette version du moteur, aucune logique de collision n'est
 * implémentée. Cette classe sert principalement à distinguer les types
 * de plateformes définies dans Tiled et à préparer d'éventuelles
 * extensions futures (collisions, interactions, etc.).
 * </p>
 */
public class SolidPlatformObjectModel extends PlatformObjectModel {

    /**
     * Construit une plateforme solide à partir des données issues de Tiled.
     *
     * @param data DTO contenant les informations extraites d'un objet Tiled
     */
    public SolidPlatformObjectModel(TiledDTO data) {
        super(data);
    }
}
