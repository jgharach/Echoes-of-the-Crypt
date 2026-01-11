package com.badlogic.echoesofthecrypt.controller;

import com.badlogic.echoesofthecrypt.model.TiledMapModel;
import com.badlogic.echoesofthecrypt.view.TiledMapView;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Contrôleur (Controller) pour les cartes Tiled.
 *
 * Cette classe fait partie de l'architecture MVC et correspond au **Controller** pour les cartes.
 * Elle est responsable de :
 * <ul>
 *     <li>Initialiser le modèle de carte et créer les entités correspondantes</li>
 *     <li>Configurer la vue pour le rendu de la carte</li>
 *     <li>Orchestrer le rendu et la libération des ressources</li>
 * </ul>
 *
 * Le Controller ne contient pas de logique de rendu ou de calcul :
 * il délègue ces responsabilités au Model et à la View.
 */
public class TiledMapController {

    /** Modèle représentant la carte Tiled (MVC) */
    private final TiledMapModel tiledMapModel;

    /** Vue chargée du rendu de la carte */
    private final TiledMapView tiledMapView;

    /**
     * Crée un contrôleur pour une carte Tiled donnée.
     *
     * @param tiledMapModel le modèle de la carte
     * @param tiledMapView la vue de la carte
     */
    public TiledMapController(TiledMapModel tiledMapModel, TiledMapView tiledMapView){
        this.tiledMapModel = tiledMapModel;
        this.tiledMapView = tiledMapView;
    }

    /**
     * Initialise le contrôleur :
     * <ol>
     *     <li>Initialise le modèle de carte et crée les entités via le ModelMasterFactory</li>
     *     <li>Configure le renderer de la vue si la carte est chargée correctement</li>
     * </ol>
     */
    public void initialize(){
        if (tiledMapModel == null) return;

        tiledMapModel.initialize();

        if (tiledMapModel.getTiledMap() == null) return;

        tiledMapView.setupRenderer(tiledMapModel);
    }

    /**
     * Rend la carte Tiled à l'écran en utilisant la caméra fournie.
     *
     * @param camera la caméra orthographique utilisée pour le rendu
     */
    public void render(OrthographicCamera camera){
        tiledMapView.render(camera);
    }

    /**
     * Libère les ressources associées à la carte Tiled.
     * Vérifie que le modèle existe avant d'appeler la vue.
     */
    public void dispose(){
        if (tiledMapModel == null) return;

        tiledMapView.dispose(tiledMapModel);
    }
}

