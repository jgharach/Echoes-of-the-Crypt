package com.badlogic.echoesofthecrypt.view;

import com.badlogic.echoesofthecrypt.model.TiledMapModel;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Vue (View) pour afficher une carte Tiled.
 *
 * Cette classe fait partie de l'architecture MVC et correspond à la **View** pour les cartes.
 * Elle est responsable de :
 * <ul>
 *     <li>Configurer le renderer pour une carte Tiled donnée</li>
 *     <li>Rendre la carte à l'écran via une caméra</li>
 *     <li>Libérer les ressources associées au renderer et à la carte</li>
 * </ul>
 *
 * La vue ne contient aucune logique de jeu ni gestion des entités.
 * Elle se contente d'afficher la carte fournie par le modèle.
 */
public class TiledMapView {

    /** Renderer utilisé pour dessiner la carte Tiled */
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    /**
     * Configure le renderer pour une carte Tiled donnée.
     *
     * @param tiledMapModel le modèle de carte à afficher
     */
    public void setupRenderer(TiledMapModel tiledMapModel){
        tiledMapRenderer = new OrthogonalTiledMapRenderer(
            tiledMapModel.getTiledMap(),
            tiledMapModel.getUnitScale()
        );
    }

    /**
     * Rend la carte à l'écran à l'aide de la caméra fournie.
     *
     * @param camera la caméra orthographique utilisée pour le rendu
     */
    public void render(OrthographicCamera camera) {
        if (tiledMapRenderer == null) return;

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    /**
     * Libère les ressources utilisées par la carte Tiled et le renderer.
     *
     * @param tiledMapModel le modèle de carte dont les ressources doivent être disposées
     */
    public void dispose(TiledMapModel tiledMapModel){
        tiledMapModel.getTiledMap().dispose();
        tiledMapRenderer.dispose();
    }
}
