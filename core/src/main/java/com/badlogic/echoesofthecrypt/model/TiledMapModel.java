package com.badlogic.echoesofthecrypt.model;
import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;
import com.badlogic.echoesofthecrypt.model.DTO.TiledMapDTO;
import com.badlogic.echoesofthecrypt.model.factory.ModelMasterFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Modèle (Model) représentant une carte Tiled dans le moteur de jeu.
 *
 * Cette classe fait partie de l'architecture MVC et correspond au **Model**.
 * Elle est responsable de :
 * <ul>
 *     <li>Charger une carte Tiled depuis un fichier .tmx</li>
 *     <li>Extraire les données des objets présents sur la carte</li>
 *     <li>Créer les modèles de jeu correspondants via le {@link ModelMasterFactory}</li>
 * </ul>
 *
 * Toutes les dimensions sont exprimées en nombre de tuiles sauf le unitScale,
 * qui convertit les unités en pixel en unités du moteur (donc en tuiles).
 */

public class TiledMapModel {

    /** Chemin vers le fichier Tiled (.tmx) */
    private final String tiledMapPath;

    /** Usine maître utilisée pour créer les modèles de jeu depuis les objets Tiled */
    private final ModelMasterFactory modelMasterFactory;

    /** Référence vers la carte Tiled chargée. Peut être null si le chargement a échoué */
    private TiledMap tiledMap;

    /** Largeur de la carte en nombre de tuiles */
    private int width;

    /** Hauteur de la carte en nombre de tuiles */
    private int height;

    /** Échelle pour convertir les unités en pixel en unités du moteur */
    private float unitScale;

    /**
     * Crée un modèle de carte Tiled avec le chemin spécifié et l'usine de modèles.
     *
     * @param tiledMapPath le chemin vers le fichier Tiled (.tmx)
     * @param modelMasterFactory l'usine de modèles utilisée pour créer les entités et objets
     */
    public TiledMapModel(String tiledMapPath, ModelMasterFactory modelMasterFactory){
        this.tiledMapPath = tiledMapPath;
        this.modelMasterFactory = modelMasterFactory;
    }

    /**
     * Charge la carte Tiled depuis le chemin spécifié.
     * <p>
     * Si le chemin est null, vide, ou que le fichier n'existe pas, une exception est affichée dans la console.
     */
    private void loadTiledMap() {
        try {
            if (tiledMapPath == null || tiledMapPath.isEmpty()) {
                throw new IllegalStateException("Tiled map path is null or empty");
            }

            if (!Gdx.files.internal(tiledMapPath).exists()) {
                throw new IllegalStateException("Tiled map not found: " + tiledMapPath);
            }

            tiledMap = new TmxMapLoader().load(tiledMapPath);

            // Extraction des informations principales de la carte
            TiledMapDTO data = new TiledMapDTO(tiledMap);
            this.width = data.getWidth();
            this.height = data.getHeight();
            this.unitScale = data.getUnitScale();

        } catch (IllegalStateException e) {
            System.err.println("Error loading map: " + e.getMessage());
        }
    }

    /**
     * Crée les modèles de jeu à partir des objets présents dans chaque calque de la carte.
     * <p>
     * Chaque {@link MapObject} est converti en {@link TiledDTO} puis transmis au {@link ModelMasterFactory}.
     * Si la carte n'est pas chargée, cette méthode ne fait rien.
     */
    private void createModels(){
        if (tiledMap == null) return;

        for (MapLayer layer : tiledMap.getLayers()) {
            for (MapObject object : layer.getObjects()) {
                TiledDTO data = new TiledDTO(object, unitScale);
                modelMasterFactory.create(data);
            }
        }
    }

    /**
     * Initialise le modèle de carte :
     * <ol>
     *     <li>Charge la carte Tiled</li>
     *     <li>Crée tous les modèles de jeu correspondants</li>
     * </ol>
     * <p>
     * Cette méthode doit être appelée avant d'accéder aux objets ou dimensions de la carte.
     */
    public void initialize(){
        loadTiledMap();
        createModels();
    }

    /**
     * Retourne la carte Tiled chargée.
     *
     * @return la {@link TiledMap} chargée, ou null si le chargement a échoué
     */
    public TiledMap getTiledMap() {
        return tiledMap;
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
     * Retourne l'échelle utilisée pour convertir les unités en pixel en unités du moteur.
     *
     * @return l'unitScale de la carte
     */
    public float getUnitScale() {
        return unitScale;
    }
}

