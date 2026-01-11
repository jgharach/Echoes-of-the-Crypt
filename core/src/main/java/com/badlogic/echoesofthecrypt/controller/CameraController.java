package com.badlogic.echoesofthecrypt.controller;

import com.badlogic.echoesofthecrypt.model.TiledMapModel;
import com.badlogic.echoesofthecrypt.model.entity.PlayerModel;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

/**
 * Contrôleur de la caméra.
 *
 * <p>
 * Permet à la caméra de suivre le joueur et de rester dans les limites de la carte.
 * </p>
 */
public class CameraController {
    private final OrthographicCamera camera;

    /**
     * Constructeur de la caméra.
     *
     * @param camera caméra utilisée pour le rendu
     */
    public CameraController(OrthographicCamera camera){
        this.camera = camera;
    }

    /**
     * Centre la caméra sur le joueur.
     *
     * @param playerModel modèle du joueur à suivre
     */
    public void followPlayer(PlayerModel playerModel){
        if (playerModel == null) return;

        camera.position.set(
            playerModel.getPosition().x + playerModel.getWidth() / 2f,
            playerModel.getPosition().y + playerModel.getHeight() / 2f,
            0
        );
    }

    /**
     * Limite la position de la caméra aux bords de la carte.
     *
     * @param tiledMapModel modèle de la carte pour obtenir les dimensions
     */
    public void clampToMapBounds(TiledMapModel tiledMapModel){
        if (tiledMapModel == null) return;

        float halfViewportWidth = camera.viewportWidth  / 2f;
        float halfViewportHeight = camera.viewportHeight  / 2f;

        int width = tiledMapModel.getWidth();
        int height = tiledMapModel.getHeight();

        camera.position.x = MathUtils.clamp(camera.position.x, halfViewportWidth, width - halfViewportWidth);
        camera.position.y = MathUtils.clamp(camera.position.y, halfViewportHeight, height - halfViewportHeight);
    }

    /** Met à jour la caméra après modification de sa position. */
    public void update() {
        camera.update();
    }
}
