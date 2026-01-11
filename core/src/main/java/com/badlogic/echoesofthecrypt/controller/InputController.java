package com.badlogic.echoesofthecrypt.controller;

import com.badlogic.echoesofthecrypt.model.entity.PlayerModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Contrôleur des entrées clavier pour le joueur.
 *
 * <p>
 * Détecte les touches appuyées et met à jour le modèle {@link PlayerModel} en conséquence.
 * </p>
 */
public class InputController {

    /**
     * Met à jour le joueur en fonction des touches appuyées.
     *
     * @param player le modèle du joueur à contrôler
     */
    public void update(PlayerModel player) {
        player.stop();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.moveRight();
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.moveLeft();
        else player.stop();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) player.jump();
    }
}
