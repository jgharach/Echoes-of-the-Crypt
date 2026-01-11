package com.badlogic.echoesofthecrypt.model.platform.objects;

import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Modèle abstrait représentant un objet de plateforme du jeu.
 *
 * <p>
 * Cette classe fait partie de la couche <b>Model</b> de l'architecture MVC.
 * Elle représente l'objet de platforme du monde est et
 * défini et configuré à partir de l'éditeur de cartes Tiled.
 * </p>
 *
 * <p>
 * Les objets de type {@code PlatformObjectModel} sont instanciés via une
 * Factory à partir des données extraites du fichier {@code .tmx}.
 * Aucune logique de collision ou de gameplay n'est implémentée dans cette
 * version du moteur.
 * </p>
 *
 * <p>
 * Cette classe est abstraite afin de permettre l'extension vers des types
 * spécifiques de plateformes dans de futures versions du moteur.
 * </p>
 */
public abstract class PlatformObjectModel {

    /** Position X de l'objet dans le monde (en unités du jeu) */
    private final float x;

    /** Position Y de l'objet dans le monde (en unités du jeu) */
    private final float y;

    /** Largeur de l'objet */
    private final float width;

    /** Hauteur de l'objet */
    private final float height;

    /** Texture associée à l'objet */
    private final TextureRegion textureRegion;

    /**
     * Construit un objet de plateforme à partir des données issues de Tiled.
     *
     * @param data DTO contenant les informations extraites d'un objet Tiled
     */
    public PlatformObjectModel(TiledDTO data) {
        this.x = data.getX();
        this.y = data.getY();
        this.width = data.getWidth();
        this.height = data.getHeight();
        this.textureRegion = data.getTexture();
    }

    /** @return la position X de l'objet */
    public float getX() {
        return x;
    }

    /** @return la position Y de l'objet */
    public float getY() {
        return y;
    }

    /** @return la largeur de l'objet */
    public float getWidth() {
        return width;
    }

    /** @return la hauteur de l'objet */
    public float getHeight() {
        return height;
    }

    /** @return la texture associée à l'objet */
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}


