package com.badlogic.echoesofthecrypt.model.DTO;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Data Transfer Object pour un TiledMap.
 *
 * Cette classe sert à extraire les propriétés principales d'une carte Tiled
 * afin de les rendre accessibles dans le moteur de jeu.
 * Elle fournit les dimensions de la carte en nombre de tuiles et l'échelle
 * de conversion (unitScale) pour transformer les coordonnées de pixels en tuiles.
 *
 * Elle fait partie du modèle (MVC) et permet de séparer la logique de lecture
 * des fichiers TiledMap de l'utilisation des données dans le moteur de jeu.
 */
public class TiledMapDTO {

    /** Largeur de la carte en nombre de tuiles */
    private final int width;

    /** Hauteur de la carte en nombre de tuiles */
    private final int height;

    /** Échelle de conversion des coordonnées (1 / tileWidth) */
    private final float unitScale;

    /**
     * Constructeur à partir d'un objet TiledMap.
     *
     * @param tiledMap la carte Tiled chargée
     */
    public TiledMapDTO(TiledMap tiledMap) {
        MapProperties props = tiledMap.getProperties();
        int tileWidth = props.get("tilewidth", Integer.class);
        this.width = props.get("width", Integer.class);
        this.height = props.get("height", Integer.class);
        this.unitScale = 1f / tileWidth;
    }

    /**
     * Retourne la largeur de la carte en nombre de tuiles.
     *
     * @return largeur en tuiles
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur de la carte en nombre de tuiles.
     *
     * @return hauteur en tuiles
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retourne l'échelle de conversion pour transformer
     * les coordonnées de pixels en tuiles.
     *
     * @return unitScale
     */
    public float getUnitScale() {
        return unitScale;
    }
}
